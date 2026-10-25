import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StockServer {
    public static void main(String...args) throws Exception{
        
        
        RMIStockImpl stock=new RMIStockImpl("data.csv");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("myStock", stock);
    }
}
