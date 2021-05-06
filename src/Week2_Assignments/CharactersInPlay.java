package Week2_Assignments;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    ArrayList<String> names;
    ArrayList<Integer> freq;
    public CharactersInPlay(){
        names = new ArrayList<>();
        freq = new ArrayList<>();
    }
    public void update(String person){
        int index = names.indexOf(person);
        if(index == -1) {
            names.add(person);
            freq.add(1);
        }
        else{
            freq.set(index,freq.get(index)+1);
        }
    }
    public void  findAllCharacters(){
        FileResource fr = new FileResource();
        names.clear();
        freq.clear();
        for(String line : fr.lines()) {
            int index = line.indexOf('.');
            if(index != -1) {
                String person = line.substring(0, index);
                update(person);
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int i=0;i<names.size();i++){
            if(freq.get(i)>1)
            System.out.println(names.get(i)+" = "+freq.get(i));
        }
        charactersWithNumParts(10,15);
    }
    public void charactersWithNumParts(int num1,int num2){
        for(int i=0;i<freq.size();i++){
            int f = freq.get(i);
            if(f >= num1 && f <= num2)
                System.out.println(names.get(i));
        }
    }
    public static void main(String[] args) {
        CharactersInPlay charInPlay = new CharactersInPlay();
        charInPlay.tester();
    }
}
