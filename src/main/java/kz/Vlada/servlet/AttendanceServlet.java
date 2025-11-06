package kz.Vlada.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "61677127vs";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        String query = """
                SELECT s.id, s.name, g.name AS group_name, s.is_attended
                FROM students s
                LEFT JOIN groups g ON s.group_name_id = g.id
                ORDER BY s.id
                """;

        try (PrintWriter out = resp.getWriter();
             Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang='ru'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Список студентов</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 30px; }");
            out.println("table { border-collapse: collapse; width: 70%; margin-top: 20px; }");
            out.println("th, td { border: 1px solid #000; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Список студентов</h1>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Имя</th><th>Группа</th><th>Присутствовал</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String groupName = rs.getString("group_name");
                boolean isAttended = rs.getBoolean("is_attended");

                System.out.println("Студент: id=" + id + ", name=" + name + ", group=" + groupName + ", attended=" + isAttended);

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + (groupName != null ? groupName : "-") + "</td>");
                out.println("<td>" + (isAttended ? "Да" : "Нет") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("<p>Ошибка при получении данных из базы.</p>");
        }
    }
}