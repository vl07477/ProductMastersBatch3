import java.io.*;
import java.net.*;
import java.time.LocalTime;

public class Main { // вместо TimeServer
    public static void main(String[] args) {
        int port = 8888; // можно любой свободный порт
        System.out.println("Сервер запущен на порту " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) { // сервер принимает клиентов в бесконечном цикле
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getInetAddress());

                try (
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String message;
                    while ((message = in.readLine()) != null) { // читаем сообщения клиента
                        System.out.println("Клиент: " + message);

                        if ("/time".equalsIgnoreCase(message)) {
                            out.println("Сервер: " + LocalTime.now().withNano(0));
                        } else if ("/exit".equalsIgnoreCase(message)) {
                            out.println("Сервер: До свидания!");
                            break;
                        } else {
                            out.println("Сервер: неизвестная команда");
                        }
                    }
                }

                clientSocket.close();
                System.out.println("Клиент отключился.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}