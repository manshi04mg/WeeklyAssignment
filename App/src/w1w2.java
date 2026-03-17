import java.util.*;

public class w1w2 {

    static HashMap<String,Integer> stockMap = new HashMap<>();
    static LinkedHashMap<Integer,String> waitingList =
            new LinkedHashMap<>();

    static int waitPosition = 1;

    // Check stock
    static void checkStock(String product){

        if(stockMap.containsKey(product))
            System.out.println(stockMap.get(product)
                    +" units available");
        else
            System.out.println("Product not found");
    }

    // Purchase item
    static void purchaseItem(String product,int userId){

        if(stockMap.get(product) > 0){

            stockMap.put(product,
                    stockMap.get(product)-1);

            System.out.println("Success, "
                    +stockMap.get(product)
                    +" units remaining");
        }
        else{

            waitingList.put(userId,product);

            System.out.println(
                    "Added to waiting list, position #"
                            +waitPosition);

            waitPosition++;
        }
    }

    public static void main(String[] args) {

        stockMap.put("IPHONE15_256GB",100);

        checkStock("IPHONE15_256GB");

        purchaseItem("IPHONE15_256GB",12345);
        purchaseItem("IPHONE15_256GB",67890);

        // simulate stock ending
        stockMap.put("IPHONE15_256GB",0);

        purchaseItem("IPHONE15_256GB",99999);
    }
}