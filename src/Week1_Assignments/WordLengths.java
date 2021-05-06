package Week1_Assignments;

import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int size = counts.length;
        for(String word : resource.words()){
            int len = word.length();
            if(!Character.isLetter(word.charAt(0)))
                len--;
            if(!Character.isLetter(word.charAt(word.length()-1)))
                len--;
            if(len >= size)
                counts[size]++;
            else
                counts[len]++;
        }
    }
    public int indexOfMax(int values[]){
        int index = 0;
        for(int i=1;i<values.length;i++) {
            if (values[index] < values[i])
                index = i;
        }
        return index;
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int counts[] =new int[31];
        countWordLengths(fr,counts);
        for(int i=0;i<31;i++)
            System.out.println("There are "+counts[i]+" words of length "+i);
       int index = indexOfMax(counts);
        System.out.println("The most common word length = "+index);
    }

    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.testCountWordLengths();
    }
}
