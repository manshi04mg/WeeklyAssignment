import java.util.*;

public class w1w2 {

    // Trie Node
    static class TrieNode{

        HashMap<Character,TrieNode> children =
                new HashMap<>();

        boolean isEnd = false;
        String word = "";
    }

    static TrieNode root = new TrieNode();

    static HashMap<String,Integer> frequency =
            new HashMap<>();

    // Insert query
    static void insert(String query){

        TrieNode node = root;

        for(char c:query.toCharArray()){

            node.children.putIfAbsent(c,
                    new TrieNode());

            node = node.children.get(c);
        }

        node.isEnd = true;
        node.word = query;

        frequency.put(query,
                frequency.getOrDefault(query,0)+1);
    }

    // DFS to collect suggestions
    static void getWords(TrieNode node,
                         List<String> list){

        if(node.isEnd)
            list.add(node.word);

        for(char c:node.children.keySet())
            getWords(node.children.get(c),
                    list);
    }

    // Search prefix
    static void search(String prefix){

        TrieNode node = root;

        for(char c:prefix.toCharArray()){

            if(!node.children.containsKey(c)){

                System.out.println(
                        "No suggestions");
                return;
            }

            node = node.children.get(c);
        }

        List<String> list =
                new ArrayList<>();

        getWords(node,list);

        // sort by frequency
        list.sort((a,b)->
                frequency.get(b)-
                        frequency.get(a));

        System.out.println(
                "Top suggestions:");

        int count=0;

        for(String s:list){

            System.out.println(
                    s+" ("+
                            frequency.get(s)+")");

            count++;

            if(count==10)
                break;
        }
    }

    public static void main(String[] args) {

        insert("java tutorial");
        insert("javascript");
        insert("java download");
        insert("java tutorial");

        search("jav");

        insert("java 21 features");

        insert("java 21 features");

        System.out.println(
                "Updated frequency: "+
                        frequency.get("java 21 features"));
    }
}