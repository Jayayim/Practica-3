import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Dirección del servidor
        int port = 5000; // Puerto del servidor

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // Mostrar mensaje de bienvenida del servidor
            System.out.println("Soy cliente: " + serverAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
