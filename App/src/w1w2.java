import java.util.*;

public class w1w2 {

    static class Transaction{

        int id;
        int amount;
        String merchant;
        int time; // minutes
        String account;

        Transaction(int id,int amount,
                    String merchant,
                    int time,String account){

            this.id=id;
            this.amount=amount;
            this.merchant=merchant;
            this.time=time;
            this.account=account;
        }
    }

    static List<Transaction> transactions =
            new ArrayList<>();

    // Classic Two Sum
    static void findTwoSum(int target){

        HashMap<Integer,Transaction> map =
                new HashMap<>();

        for(Transaction t:transactions){

            int complement =
                    target - t.amount;

            if(map.containsKey(complement)){

                System.out.println(
                        "Pair: "+
                                map.get(complement).id+
                                " , "+t.id);
            }

            map.put(t.amount,t);
        }
    }

    // Two sum with time window (60 min)
    static void twoSumTime(int target){

        for(int i=0;i<transactions.size();i++){

            for(int j=i+1;
                j<transactions.size();j++){

                if(transactions.get(i).amount +
                        transactions.get(j).amount
                        == target &&

                        Math.abs(
                                transactions.get(i).time -
                                        transactions.get(j).time)
                                <=60){

                    System.out.println(
                            "Time pair: "+
                                    transactions.get(i).id+
                                    " , "+
                                    transactions.get(j).id);
                }
            }
        }
    }

    // Duplicate detection
    static void detectDuplicates(){

        HashMap<String,
                List<Transaction>> map =
                new HashMap<>();

        for(Transaction t:transactions){

            String key =
                    t.amount+"-"+t.merchant;

            map.putIfAbsent(key,
                    new ArrayList<>());

            map.get(key).add(t);
        }

        for(String key:map.keySet()){

            if(map.get(key).size()>1){

                System.out.println(
                        "Duplicate: "+key);
            }
        }
    }

    public static void main(String[] args) {

        transactions.add(
                new Transaction(1,500,
                        "StoreA",600,"acc1"));

        transactions.add(
                new Transaction(2,300,
                        "StoreB",615,"acc2"));

        transactions.add(
                new Transaction(3,200,
                        "StoreC",630,"acc3"));

        transactions.add(
                new Transaction(4,500,
                        "StoreA",640,"acc4"));

        findTwoSum(500);

        twoSumTime(500);

        detectDuplicates();
    }
}