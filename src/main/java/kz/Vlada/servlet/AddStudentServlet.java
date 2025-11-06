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
    private static final String PASSWORD = "61677127vs";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String groupName = request.getParameter("groupName");
        String isAttendedParam = request.getParameter("isAttended");

        boolean isAttended = "on".equalsIgnoreCase(isAttendedParam) || "true".equalsIgnoreCase(isAttendedParam);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("PostgreSQL Driver not found", e);
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // 1️⃣ Проверяем — есть ли такая группа, если нет, создаем новую
            int groupId = -1;
            String checkGroupSql = "SELECT id FROM groups WHERE name = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkGroupSql)) {
                checkStmt.setString(1, groupName);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    groupId = rs.getInt("id");
                }
            }

            if (groupId == -1) {
                String insertGroupSql = "INSERT INTO groups (name) VALUES (?) RETURNING id";
                try (PreparedStatement insertGroupStmt = conn.prepareStatement(insertGroupSql)) {
                    insertGroupStmt.setString(1, groupName);
                    ResultSet rs = insertGroupStmt.executeQuery();
                    if (rs.next()) {
                        groupId = rs.getInt("id");
                    }
                }
            }

            // 2️⃣ Добавляем студента с найденным group_id
            String insertStudentSql = "INSERT INTO students (name, group_name_id, is_attended) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertStudentSql)) {
                stmt.setString(1, name);
                stmt.setInt(2, groupId);
                stmt.setBoolean(3, isAttended);
                stmt.executeUpdate();
            }

            // 3️⃣ Перенаправляем обратно на список
            response.sendRedirect(request.getContextPath() + "/attendance");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при сохранении данных: " + e.getMessage());
        }
    }

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