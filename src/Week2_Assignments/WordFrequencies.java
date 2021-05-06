package Week2_Assignments;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }
    public void findUnique(){
        myFreqs.clear();
        myWords.clear();
        FileResource fr = new FileResource();
        int index = -1;
        for(String word : fr.words()){
            word = word.toLowerCase();
            index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int val = myFreqs.get(index);
                myFreqs.set(index,val+1);
            }
        }
    }
    public void tester(){
        findUnique();

       for(int i=0;i<myWords.size();i++){
            System.out.println(myWords.get(i)+": "+myFreqs.get(i));
         }
        int maxIndex = findIndexOfMax();
        System.out.println("Maximum Occurred word = "+myWords.get(maxIndex)+" with "+myFreqs.get(maxIndex)+" occurrences");
    }
    public int findIndexOfMax(){
        int maxIndex = 0;
        for(int i=1;i<myFreqs.size();i++){
            if(myFreqs.get(maxIndex) < myFreqs.get(i))
                maxIndex = i;
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
