import java.util.*;

public class w1w2 {

    static class DNSEntry{

        String ip;
        long expiryTime;

        DNSEntry(String ip,int ttl){

            this.ip = ip;
            this.expiryTime =
                    System.currentTimeMillis() + ttl*1000;
        }
    }

    static HashMap<String,DNSEntry> cache =
            new HashMap<>();

    static int hits = 0;
    static int misses = 0;

    // Resolve domain
    static String resolve(String domain){

        long currentTime =
                System.currentTimeMillis();

        if(cache.containsKey(domain)){

            DNSEntry entry = cache.get(domain);

            if(currentTime < entry.expiryTime){

                hits++;
                System.out.println(
                        "Cache HIT");

                return entry.ip;
            }
            else{

                System.out.println(
                        "Cache EXPIRED");

                cache.remove(domain);
            }
        }

        misses++;

        System.out.println(
                "Cache MISS → Query upstream");

        // simulate DNS lookup
        String newIP =
                "172.217."+new Random().nextInt(50)
                        +"."+new Random().nextInt(255);

        cache.put(domain,
                new DNSEntry(newIP,5));

        return newIP;
    }

    // Cache statistics
    static void getCacheStats(){

        int total = hits + misses;

        double hitRate =
                (total==0)?0:((double)hits/total)*100;

        System.out.println(
                "Hit Rate: "+hitRate+"%");
    }

    public static void main(String[] args)
            throws Exception{

        System.out.println(
                resolve("google.com"));

        System.out.println(
                resolve("google.com"));

        Thread.sleep(6000);

        System.out.println(
                resolve("google.com"));

        getCacheStats();
    }
}