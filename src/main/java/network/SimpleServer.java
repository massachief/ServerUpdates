package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {

    public static void start() throws IOException {

        try (ServerSocket socket = new ServerSocket(8184)) {
            System.out.println("Сервер запущен и ожидает подключения...");
            Socket clientSocket = socket.accept();
            System.out.println("Клиент подключен");
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            String greeting = reader.readLine();
            if ("hello".equals(greeting)) {
                writer.println("hello client");
            } else {
                writer.println("unrecognised greeting");
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Получено:" + line);
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        SimpleServer server;
        start();
    }
}
