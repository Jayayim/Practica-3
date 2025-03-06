import java.io.*;
import java.net.*;

public class Server1 {
    public static void main(String[] args) {
        int port = 5000; // Puerto del servidor

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Aceptar conexiones
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Crear un hilo para manejar al cliente
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Clase para manejar clientes en hilos
class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("¡Bienvenido! Escribe un mensaje (o 'salir' para desconectarte).");

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);

                if (message.equalsIgnoreCase("salir")) {
                    out.println("¡Adiós!");
                    break;
                }

                // Enviar una respuesta al cliente
                out.println("Servidor: Recibí tu mensaje -> " + message);
            }

            socket.close();
            System.out.println("Cliente desconectado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
