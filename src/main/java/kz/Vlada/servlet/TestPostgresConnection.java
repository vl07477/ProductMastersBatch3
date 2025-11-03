import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestPostgresConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "your_password"; // замени на свой пароль

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Проверка: выводим все записи из таблицы students
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("group_name") + " | " +
                                rs.getBoolean("is_attended")
                );
            }

            System.out.println("Подключение к PostgreSQL успешно!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}