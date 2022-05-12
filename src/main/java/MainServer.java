import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {
        int port = 8089;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("New connection accepted. Port: " + clientSocket.getPort());

            out.println("Write your name:");
            String name = in.readLine();
            System.out.println(name);

            out.println(String.format("Hi %s, your port is %d. Are you child? (yes/no)", name, clientSocket.getPort()));
            String answer = in.readLine();
            System.out.println(answer);
            if (answer.equals("No"))
                out.println(String.format("Welcome to the adult zone, %s! Have a good rest, " +
                        "or a good working day!", name));
            else if (answer.equals("Yes"))
                out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
        }
    }
}
