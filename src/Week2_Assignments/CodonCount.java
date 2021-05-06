package Week2_Assignments;

import edu.duke.FileResource;

import java.util.HashMap;


public class CodonCount {
    private HashMap<String,Integer> myMap;
    public CodonCount(){
        myMap = new HashMap<>();
    }
    public void buildCodonMap(int start, String dna){
        myMap.clear();
        String codon = "";
        for(int i=start;i<dna.length();i+=3){
            if((i+3) < dna.length()) {
                codon = dna.substring(i, i + 3);
                if(myMap.containsKey(codon))
                    myMap.put(codon,myMap.get(codon)+1);
                else
                    myMap.put(codon,1);
            }
        }
    }
    public String getMostCommonCodon(){
        String maxCodon = "";
        int max = -1;
        for(String key : myMap.keySet()){
            if(myMap.get(key) > max){
                max = myMap.get(key);
                maxCodon = key;
            }
        }
        return maxCodon;
    }
    public void printCodonCounts(int start, int end){
        for(String key : myMap.keySet()) {
            if(myMap.get(key) >= start && myMap.get(key) <= end)
                System.out.println(key +" = "+myMap.get(key));
        }
    }
    public void test(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase().trim();
        for(int i=0;i<3;i++) {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with "+i+" results in "+myMap.size()+" unique codons");
            for(String key :myMap.keySet())
                System.out.println(key +" = "+myMap.get(key));
            String common = getMostCommonCodon();
            System.out.println("Most Common Codon = "+common+" with count "+myMap.get(common));
            printCodonCounts(1,5);
        }
    }

    public static void main(String[] args) {
        CodonCount codon = new CodonCount();
        codon.test();
    }
}
