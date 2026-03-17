import java.util.*;

public class w1w2 {

    static HashMap<String,Integer> usernameMap = new HashMap<>();
    static HashMap<String,Integer> attemptFrequency = new HashMap<>();

    static boolean checkAvailability(String username){

        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username,0)+1);

        return !usernameMap.containsKey(username);
    }

    static void register(String username,int id){

        if(!usernameMap.containsKey(username))
            usernameMap.put(username,id);
    }

    static List<String> suggestAlternatives(String username){

        List<String> list = new ArrayList<>();

        for(int i=1;i<=5;i++){

            String newName = username+i;

            if(!usernameMap.containsKey(newName))
                list.add(newName);
        }

        String alt = username.replace("_",".");

        if(!usernameMap.containsKey(alt))
            list.add(alt);

        return list;
    }

    static String getMostAttempted(){

        int max = 0;
        String result = "";

        for(String user : attemptFrequency.keySet()){

            if(attemptFrequency.get(user) > max){

                max = attemptFrequency.get(user);
                result = user;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        register("john_doe",101);
        register("admin",102);

        System.out.println(checkAvailability("john_doe"));
        System.out.println(checkAvailability("jane_smith"));

        System.out.println(suggestAlternatives("john_doe"));

        System.out.println(getMostAttempted());
    }
}