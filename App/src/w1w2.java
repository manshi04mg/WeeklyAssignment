import java.util.*;

public class w1w2 {

    // L1 cache (LRU using LinkedHashMap)
    static LinkedHashMap<String,String> L1 =
            new LinkedHashMap<>(10000,0.75f,true){

                protected boolean removeEldestEntry(
                        Map.Entry<String,String> e){

                    return size()>3; // small size for demo
                }
            };

    // L2 cache
    static HashMap<String,String> L2 =
            new HashMap<>();

    // L3 database
    static HashMap<String,String> L3 =
            new HashMap<>();

    static HashMap<String,Integer> accessCount =
            new HashMap<>();

    static int L1hits=0;
    static int L2hits=0;
    static int L3hits=0;

    // Get video
    static void getVideo(String id){

        if(L1.containsKey(id)){

            L1hits++;

            System.out.println(
                    "L1 Cache HIT");

            return;
        }

        if(L2.containsKey(id)){

            L2hits++;

            System.out.println(
                    "L2 Cache HIT → Promote to L1");

            L1.put(id,L2.get(id));

            return;
        }

        if(L3.containsKey(id)){

            L3hits++;

            System.out.println(
                    "L3 Database HIT → Added to L2");

            L2.put(id,L3.get(id));

            accessCount.put(id,
                    accessCount.getOrDefault(id,0)+1);

            return;
        }

        System.out.println("Video not found");
    }

    // Statistics
    static void getStatistics(){

        int total =
                L1hits+L2hits+L3hits;

        if(total==0)
            return;

        System.out.println(
                "L1 Hit Rate "+
                        (L1hits*100.0/total)+"%");

        System.out.println(
                "L2 Hit Rate "+
                        (L2hits*100.0/total)+"%");

        System.out.println(
                "L3 Hit Rate "+
                        (L3hits*100.0/total)+"%");
    }

    public static void main(String[] args) {

        // database data
        L3.put("video123","data");
        L3.put("video999","data");

        getVideo("video123");

        getVideo("video123");

        getVideo("video999");

        getStatistics();
    }
}