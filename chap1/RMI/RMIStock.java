import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIStock extends Remote{
    public String getName(int id) throws RemoteException;
    public int getQuantity(int id) throws RemoteException;
    public double getPrice(int id,int qty) throws RemoteException;
}
