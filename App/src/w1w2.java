import java.util.*;

public class w1w2 {

    static class TokenBucket{

        int tokens;
        int maxTokens;
        long lastRefillTime;

        TokenBucket(int max){

            maxTokens = max;
            tokens = max;
            lastRefillTime =
                    System.currentTimeMillis();
        }
    }

    static HashMap<String,TokenBucket> clients =
            new HashMap<>();

    static int LIMIT = 1000;
    static long INTERVAL = 3600000; // 1 hour

    static boolean checkRateLimit(String clientId){

        clients.putIfAbsent(clientId,
                new TokenBucket(LIMIT));

        TokenBucket bucket =
                clients.get(clientId);

        long currentTime =
                System.currentTimeMillis();

        // refill tokens every hour
        if(currentTime - bucket.lastRefillTime
                >= INTERVAL){

            bucket.tokens = LIMIT;
            bucket.lastRefillTime = currentTime;
        }

        if(bucket.tokens > 0){

            bucket.tokens--;

            System.out.println(
                    "Allowed ("+
                            bucket.tokens+
                            " requests remaining)");

            return true;
        }
        else{

            long retry =
                    (INTERVAL -
                            (currentTime-bucket.lastRefillTime))
                            /1000;

            System.out.println(
                    "Denied (0 requests remaining, retry after "
                            +retry+"s)");

            return false;
        }
    }

    static void getRateLimitStatus(
            String clientId){

        TokenBucket bucket =
                clients.get(clientId);

        int used =
                LIMIT - bucket.tokens;

        System.out.println(
                "Used: "+used+
                        " Limit: "+LIMIT);
    }

    public static void main(String[] args) {

        checkRateLimit("abc123");
        checkRateLimit("abc123");
        checkRateLimit("abc123");

        getRateLimitStatus("abc123");
    }
}