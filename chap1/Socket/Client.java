import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverHost = "localhost";//You have to replace this with the actual server @IP
        int serverPort = 5000;
        Scanner scan = new Scanner(System.in);
        try {
            //Try to connect to the server
            Socket socket = new Socket(serverHost, serverPort);

            //Get the input/output streams
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            byte[] buffer = new byte[1024];
            //The client is quite simple, it just reads data from the command line, send them to the server then displays the result
            while(true){
                System.out.print("Your message:");
                String message = scan.nextLine();

                // Write the exact bytes to the stream
                output.write(message.getBytes(StandardCharsets.UTF_8));
                output.flush(); // Force transmission over the network
                int bytesRead=input.read(buffer);
                String received = new String(buffer, 0, bytesRead, "UTF-8").trim();
                System.out.println("Server response: " + received);

                if (message.equalsIgnoreCase("end")) {
                    break;
                }
            }
            output.flush(); // Force transmission over the network
            scan.close();
            socket.close();
            in.close();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
