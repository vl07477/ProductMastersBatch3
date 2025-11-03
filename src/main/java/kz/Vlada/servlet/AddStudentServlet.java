package kz.Vlada.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "your_password"; // замените на свой пароль

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String groupName = request.getParameter("groupName");
        String isAttendedParam = request.getParameter("isAttended");

        boolean isAttended = "on".equalsIgnoreCase(isAttendedParam) ||
                "true".equalsIgnoreCase(isAttendedParam);

        String sql = "INSERT INTO students (name, group_name, is_attended) VALUES (?, ?, ?)";

        // Загружаем драйвер PostgreSQL
        try {
            Class.forName("org.postgresql.Driver");
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

            // Перенаправляем на страницу посещаемости после успешного добавления
            response.sendRedirect(request.getContextPath() + "/attendance");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при сохранении данных.");
        }
    }
}