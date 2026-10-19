import java.net.*;
import java.util.ArrayList;
import java.io.*;

//The first part is the business logic
class Item {
    public int id;
    public String name;
    public double price;
    public int quantity;

    public Item(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return "Item(id=" + id + ", name=\"" + name + "\", price=" + price + ", quantity=" + quantity + ")";
    }
}

class Stock{
    private ArrayList<Item> items;
    public Stock(String fileName){
        items=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Process the values as needed
                Item item = new Item(
                        Integer.parseInt(values[0]),
                        values[1],
                        Double.parseDouble(values[2]),
                        Integer.parseInt(values[3]));
                items.add(item);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Item getElement(int id){
        return items.stream().filter(item -> item.id == id).findFirst().orElse(null);
    }

    public String getName(int id){
        Item item = getElement(id);
        return item != null ? item.name : "Item not found";
    }

    public Integer getQuantity(int id){
        Item item = getElement(id);
        return item != null ? item.quantity : null;
    }

    public Double getPrice(int id, int qty){
        Item item = getElement(id);
        return item != null ? item.price * qty : null;
    }
}

//Here comes the server
public class Server {
    private Stock stock;
    
    public Server(Stock stock) {
        this.stock=stock;
    }

    //serves on the given port
    public void serve(int port) {
        // Starts server and waits for a connection
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            //Waiting for a client
            Socket s = ss.accept();
            System.out.println("Client accepted");

            // Takes input from the client socket and then send the output
            InputStream in = s.getInputStream();
            PrintStream out = new PrintStream(s.getOutputStream());

            //Data exchange is made on the basis of bytes and not chars
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                // Process the bytes received
                String received = new String(buffer, 0, bytesRead, "UTF-8").trim();
                System.out.println("Command: " + received);
                
                //Process the command
                if (received.startsWith("name ")) {
                    try {
                        int id = Integer.parseInt(received.substring(5));
                        out.print(stock.getName(id));
                    } catch (Exception e) {
                        out.print("Invalid ID");
                    }
                } else if (received.startsWith("price ")) {
                    try {
                        String args[] = received.substring(6).split(" ");
                        int id = Integer.parseInt(args[0]);
                        int qty = Integer.parseInt(args[1]);
                        out.print(stock.getPrice(id, qty));
                    } catch (Exception e) {
                        out.print("Invalid ID");
                    }
                } else if (received.startsWith("quantity ")) {
                    try {
                        int id = Integer.parseInt(received.substring(9));
                        out.print(stock.getQuantity(id));
                    } catch (Exception e) {
                        out.print("Invalid ID");
                    }
                } else if (received.equals("end")) {
                    out.print(" ");
                    break;
                } else {
                    out.print("Unknown command");
                }
                //force the data to be sent to the client
                out.flush();
            }

            // Close connection
            s.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        Server s = new Server(new Stock("data.csv"));
        s.serve(5000);
    }
}
