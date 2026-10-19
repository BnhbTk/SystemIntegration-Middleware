import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Stock{
    ArrayList<Item> items;
  
    public Stock(){
        items=new ArrayList<>();
    }

    public Item getItem(int id){
        for(Item item:items)
            if (item.id==id) return item;
        return null;
    }

    public String getName(int id){
        Item item=getItem(id);
        if (item!=null) return item.name;
        else return null;
    }
    public int getQuantity(int id){
        Item item=getItem(id);
        if (item!=null) return item.quantity;
        else return -1;
    }
    
    public double getPrice(int id,int qty){
        Item item=getItem(id);
        if (item!=null) return qty*item.price;
        else return -1;
    }

    public void loadFromCSV(String fileName) throws Exception{
        BufferedReader buf=new BufferedReader(new FileReader(fileName));
        buf.readLine();
        String line;
        while ((line=buf.readLine())!=null){
            String parts[]=line.split(",");
            Item item=new Item(Integer.parseInt(parts[0]),parts[1],Double.parseDouble(parts[2]),Integer.parseInt(parts[3]));
            items.add(item);
        }
    }

    public static void main(String...args) throws Exception{
        Stock stock=new Stock();
        stock.loadFromCSV("data.csv");

        for (Item item : stock.items)
            System.out.println(item);
    }
}

class Item{
    int id;
    String name;
    double price;
    int quantity;

    public Item(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    @Override 
    public String toString(){
        return String.format("Item(%d,%s,%f,%d)", id,name,price,quantity);
    }

}
