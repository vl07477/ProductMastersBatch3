package kz.Vlada.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "61677127vs"; // замени на свой пароль

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String groupName = request.getParameter("groupName");
        String isAttendedParam = request.getParameter("isAttended");

        boolean isAttended = "on".equalsIgnoreCase(isAttendedParam) ||
                "true".equalsIgnoreCase(isAttendedParam);

        String sql = "INSERT INTO students (name, group_name, is_attended) VALUES (?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver"); // загружаем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("PostgreSQL Driver not found", e);
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, groupName);
            stmt.setBoolean(3, isAttended);
            stmt.executeUpdate();

            // Перенаправляем на страницу посещаемости
            response.sendRedirect(request.getContextPath() + "/attendance");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при сохранении данных.");
        }
    }

    // Обработка GET-запроса — просто выводим HTML-форму
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (var out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='ru'>");
            out.println("<head><meta charset='UTF-8'><title>Добавить студента</title></head>");
            out.println("<body>");
            out.println("<h1>Добавить студента</h1>");
            out.println("<form action='" + request.getContextPath() + "/addStudent' method='post'>");
            out.println("Имя: <input type='text' name='name' required><br>");
            out.println("Группа: <input type='text' name='groupName' required><br>");
            out.println("Присутствовал: <input type='checkbox' name='isAttended'><br>");
            out.println("<button type='submit'>Добавить</button>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }
}