import java.util.*;

public class w1w2 {

    static HashMap<String, Set<String>> ngramMap =
            new HashMap<>();

    static int N = 5; // 5-gram

    // Generate n-grams
    static List<String> generateNgrams(String text){

        List<String> list = new ArrayList<>();

        String words[] = text.split(" ");

        for(int i=0;i<=words.length-N;i++){

            String gram = "";

            for(int j=0;j<N;j++)
                gram += words[i+j]+" ";

            list.add(gram.trim());
        }

        return list;
    }

    // Store document ngrams
    static void addDocument(String docId,
                            String text){

        List<String> grams =
                generateNgrams(text);

        for(String gram:grams){

            ngramMap.putIfAbsent(gram,
                    new HashSet<>());

            ngramMap.get(gram).add(docId);
        }

        System.out.println(docId+
                " → Extracted "+grams.size()
                +" n-grams");
    }

    // Analyze plagiarism
    static void analyzeDocument(String docId,
                                String text){

        List<String> grams =
                generateNgrams(text);

        HashMap<String,Integer> matchCount =
                new HashMap<>();

        for(String gram:grams){

            if(ngramMap.containsKey(gram)){

                for(String doc:
                        ngramMap.get(gram)){

                    matchCount.put(doc,
                            matchCount.getOrDefault(
                                    doc,0)+1);
                }
            }
        }

        for(String doc:matchCount.keySet()){

            int matches = matchCount.get(doc);

            double similarity =
                    ((double)matches/grams.size())*100;

            System.out.println(
                    "Found "+matches+
                            " matching n-grams with "+doc);

            System.out.println(
                    "Similarity: "+
                            similarity+"%");

            if(similarity>60)
                System.out.println(
                        "PLAGIARISM DETECTED");
        }
    }

    public static void main(String[] args) {

        String doc1 =
                "data structures and algorithms are important subjects in computer science";

        String doc2 =
                "data structures and algorithms are important topics in computer science";

        addDocument("essay_089",doc1);

        analyzeDocument("essay_123",doc2);
    }
}