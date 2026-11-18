import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

//To serialize the instances of a class, we should just implement the Serializable intergace
public class Item implements Serializable{
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Item(int id,String name,double price,int quantity){
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    public String toString(){
        return "Item("+id+",'"+name+"',"+price+","+quantity+")";
    }

    public static void main(String...args) throws Exception{
        //Code to store the state of an object
        Item item=new Item(5,"USB mouse",100,50);
        System.out.println(item);
        ObjectOutputStream ps=new ObjectOutputStream(new PrintStream("item.ser"));
        ps.writeObject(item);
        ps.close();

        //Code to load the state of an object
        ObjectInputStream is=new ObjectInputStream(new FileInputStream("item.ser"));
        Item item=(Item)is.readObject();
        System.out.println(item);
        is.close();
    }
}
