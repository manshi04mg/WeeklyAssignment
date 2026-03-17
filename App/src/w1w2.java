import java.util.*;

public class w1w2 {

    static HashMap<String,Integer> pageViews =
            new HashMap<>();

    static HashMap<String,Set<String>> uniqueVisitors =
            new HashMap<>();

    static HashMap<String,Integer> trafficSource =
            new HashMap<>();

    // Process event
    static void processEvent(String url,
                             String userId,
                             String source){

        // count page views
        pageViews.put(url,
                pageViews.getOrDefault(url,0)+1);

        // unique visitors
        uniqueVisitors.putIfAbsent(url,
                new HashSet<>());

        uniqueVisitors.get(url).add(userId);

        // traffic sources
        trafficSource.put(source,
                trafficSource.getOrDefault(source,0)+1);
    }

    // Top pages
    static void getTopPages(){

        List<Map.Entry<String,Integer>> list =
                new ArrayList<>(pageViews.entrySet());

        list.sort((a,b)->b.getValue()-a.getValue());

        System.out.println("Top Pages:");

        int count = 0;

        for(Map.Entry<String,Integer> e:list){

            String url = e.getKey();
            int views = e.getValue();

            int unique =
                    uniqueVisitors.get(url).size();

            System.out.println(url+
                    " - "+views+
                    " views ("+unique+
                    " unique)");

            count++;

            if(count==10)
                break;
        }
    }

    // Traffic stats
    static void getTrafficSources(){

        int total = 0;

        for(int v:trafficSource.values())
            total += v;

        System.out.println("\nTraffic Sources:");

        for(String s:trafficSource.keySet()){

            double percent =
                    ((double)trafficSource.get(s)/total)*100;

            System.out.println(
                    s+" : "+percent+"%");
        }
    }

    public static void main(String[] args) {

        processEvent("/article/news",
                "user1","Google");

        processEvent("/article/news",
                "user2","Facebook");

        processEvent("/sports",
                "user3","Direct");

        processEvent("/article/news",
                "user1","Google");

        getTopPages();

        getTrafficSources();
    }
}