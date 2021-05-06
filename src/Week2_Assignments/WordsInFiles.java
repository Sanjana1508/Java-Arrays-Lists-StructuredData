package Week2_Assignments;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    HashMap<String, ArrayList<String>> wordsToFiles;
    public WordsInFiles(){
        wordsToFiles = new HashMap<>();
    }
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if(wordsToFiles.containsKey(word))
                wordsToFiles.get(word).add(f.getName());
            else{
                ArrayList<String> fileList = new ArrayList<>();
                fileList.add(f.getName());
                wordsToFiles.put(word,fileList);
            }
        }
    }
    public void buildWordFileMap(){
        wordsToFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
            addWordsFromFile(f);
    }
    public int maxNumber(){
        int max = -1;
        for(String word : wordsToFiles.keySet()){
            if(max < wordsToFiles.get(word).size())
                max = wordsToFiles.get(word).size();
        }
        return max;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> result = new ArrayList<>();
        for(String word : wordsToFiles.keySet()){
            if(wordsToFiles.get(word).size() == number)
                result.add(word);
        }
        return result;
    }
    public void printFilesIn(String word){
        for(String word_: wordsToFiles.keySet()){
            if(word_.equals(word)) {
                for(String file : wordsToFiles.get(word))
                System.out.println(file);
            }
        }
    }
    public void tester(){
        buildWordFileMap();
        System.out.println("Maximum number of times any word occurs in "+maxNumber());
        ArrayList<String> words = wordsInNumFiles(2);
        System.out.print("Words occurred 2 times :");
        for(String word : words)
            System.out.print(word +" ");
        System.out.println();
        System.out.print("Cats appeared in :");
        printFilesIn("cats");
        System.out.println("HashMap :");
        for(String word : wordsToFiles.keySet()){
            System.out.println(word+" = ");
            for(String file : wordsToFiles.get(word))
                System.out.print(file+" ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WordsInFiles wordFiles = new WordsInFiles();
        wordFiles.tester();
    }
}
