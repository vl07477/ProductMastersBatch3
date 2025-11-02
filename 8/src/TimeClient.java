import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TimeClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8888; // должен совпадать с сервером

        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Подключено к серверу на " + host + ":" + port);
            System.out.println("Текущая временная зона JVM: " + java.time.ZoneId.systemDefault());
            System.out.println("Введите команды: /time, /exit");

            String message;
            while (true) {
                System.out.print("Клиент: ");
                message = scanner.nextLine();

                out.println(message); // отправляем сообщение серверу
                String response = in.readLine(); // читаем ответ
                System.out.println(response);

                if ("/exit".equalsIgnoreCase(message)) {
                    break; // выходим из цикла
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}