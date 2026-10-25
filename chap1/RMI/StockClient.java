import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StockClient {
    public static void main(String...args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        RMIStock stock=(RMIStock)registry.lookup("myStock");

        System.out.println(stock.getName(40));
        System.out.println(stock.getPrice(40,4));
    }
}
