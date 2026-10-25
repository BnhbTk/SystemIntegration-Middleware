import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;

public class RMIStockImpl extends UnicastRemoteObject implements RMIStock {
    private ArrayList<Item> items;
    public RMIStockImpl(String filename) throws RemoteException {
        items=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.csv"));
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

    private Item getElement(int id) {
        return items.stream().filter(item -> item.id == id).findFirst().orElse(null);
    }

    public String getName(int id) throws RemoteException {
        Item item = getElement(id);
        return item != null ? item.name : "Item not found";
    }

    public int getQuantity(int id) throws RemoteException{
        Item item = getElement(id);
        return item != null ? item.quantity : null;
    }

    public double getPrice(int id, int qty) throws RemoteException{
        Item item = getElement(id);
        return item != null ? item.price * qty : null;
    }

}

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
