package Week2_Assignments;

import edu.duke.*;
import java.util.*;

public class GladLibMap{
    private HashMap<String, ArrayList<String>> replaceMap;
    private ArrayList<String> uniqueWords;
    private ArrayList<String> categories;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "src/Week2_Assignments/data";

    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        uniqueWords = new ArrayList<>();
        replaceMap = new HashMap<>();
        categories = new ArrayList<>();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] category = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for(String filename: category){
            replaceMap.put(filename,readIt(source+"/"+filename+".txt"));
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(replaceMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        if(!categories.contains(label))
            categories.add(label);
        String sub = getSubstitute(label);
        while(uniqueWords.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        uniqueWords.add(sub);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        System.out.println("\n");
        uniqueWords.clear();
        String story = fromTemplate("src/Week2_Assignments/data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println("The total number of words replaced = "+uniqueWords.size());
        System.out.println("Total word to pick from = "+totalWordsInMap());
        System.out.println("Total words in considered labels = "+totalWordsConsidered());
    }
    public int totalWordsInMap(){
        int count = 0;
        for(String label : replaceMap.keySet())
            count+=replaceMap.get(label).size();
        return count;
    }
    public int totalWordsConsidered(){
        int count =0;
        for(String label : categories){
            count+=replaceMap.get(label).size();
        }
        return count;
    }
    public static void main(String[] args) {
        GladLibMap gl = new GladLibMap();
        gl.makeStory();
    }
}

