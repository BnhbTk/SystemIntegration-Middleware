# Brief RMI guideline
This is a brief description on how to design a small distributed application using **Java RMI**. In this guideline, we will implement
the same services as in the _Socket_ exercise. Therefore, we will reuse the business logic about the stock of items.

## First step: the remote object interface
The first step consists of writing the remote object interface: this is a common interface that should be distributed to server and the client
(as a .class file). A remote interface should inherits from the interface `java.rmi.Remote`. All methods should be declared as throwing
`RemoteException`.

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIStock extends Remote{
    public String getName(int id) throws RemoteException;
    public int getQuantity(int id) throws RemoteException;
    public double getPrice(int id,int qty) throws RemoteException;
}
```

## Second step: implement the business logic and the published services
This is done by implementing the previous interface. The new class should also inherit from the class `UnicastRemoteObject`. Here, we will just
give the main points in this class:

```java
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
...

public class RMIStockImpl extends UnicastRemoteObject implements RMIStock {
    private ArrayList<Item> items;
    public RMIStockImpl(String filename) throws RemoteException {
        ...
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
...
```

## Third step: create the server
The server is responsible of creating the server object and publishing its name on the _registry_. In this example, the server creates the
registry then bind a `RMIStockImpl` to the string `"myStock"`. The registry will be queried later by the client in order to have the reference 
of the server object. The registry should be declared on unemployed port.
```java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StockServer {
    public static void main(String...args) throws Exception{    
        RMIStockImpl stock=new RMIStockImpl("data.csv");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("myStock", stock);
    }
}
```

## Fourth step: create the client
The client (which have the class of the remote interface) starts by searching for the registry, then it fetches the reference associated with
the name `myStock`. Notice how the object is casted to the remote interface.
```java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StockClient {
    public static void main(String...args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        RMIStock stock=(RMIStock)registry.lookup("myStock");

        //we make some calls like if the object is hosted by the client machine
        System.out.println(stock.getName(40));
        System.out.println(stock.getPrice(40,4));
    }
}
```

> [!NOTE]
> This is a basic implementation of a distributed application using RMI. When combined with Springboot, it is possible to turn this code into
> a web service. The client can be a browser, a mobile app, or any client that can send http requests.

## Last step: launch the system
After compiling, the server should be run first, then the client. These are standard java applications, they don't require special initialization (just run them using `java` command).
