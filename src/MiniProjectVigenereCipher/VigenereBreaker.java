package MiniProjectVigenereCipher;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    private String finalDecrypt;
    private String tempdecrypt;
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder result = new StringBuilder();
        for(int i=whichSlice ; i<message.length();i+=totalSlices){
            result.append(message.charAt(i));
        }
        return result.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }

    public void breakVigenere () {
        DirectoryResource dr = new DirectoryResource();
        HashMap<String , HashSet<String>> languages = new HashMap<>();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(fr);
            languages.put(f.getName(),dictionary);
        }
        /*int[] key = tryKeyLength(message,5,'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(message));*/
        FileResource file = new FileResource();
        String encrypted = file.asString();
        breakForAllLangs(encrypted,languages);
        System.out.println(finalDecrypt);
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hset = new HashSet<>();
        for(String line : fr.lines()){
            for(String word : line.split("\\W+")) {
                word = word.toLowerCase();
                hset.add(word);
            }
        }
        return hset;
    }
    public int countWords(String message, HashSet<String> dictionary){
        String words[] = message.split("\\W+");
        int count = 0;
        for(String word : words){
            word = word.toLowerCase();
            if(dictionary.contains(word))
                count++;
        }
        return count;
    }
    public int breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = -1;
        for(int i=1;i<=100;i++){
            char common = mostCommonCharIn(dictionary);
            int key[] = tryKeyLength(encrypted,i,common);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted,dictionary);
            if(max < count){
                max = count;
                tempdecrypt = decrypted;
            }
        }
        return  max;
    }
    public  char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> mostCommonChar = new HashMap<>();
        for(String word : dictionary){
            for(int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if(mostCommonChar.containsKey(ch))
                    mostCommonChar.put(ch,mostCommonChar.get(ch)+1);
                else
                    mostCommonChar.put(ch,1);
            }
        }
        int max = -1;
        char common = '#';
        for(char ch : mostCommonChar.keySet()){
            if(max < mostCommonChar.get(ch)) {
                max = mostCommonChar.get(ch);
                common = ch;
            }
        }
        return common;
    }
    public void breakForAllLangs(String encrypted , HashMap<String, HashSet<String>> languages){
        int max = -1;
        String language = "";
        for(String lang : languages.keySet()){
            int count = breakForLanguage(encrypted, languages.get(lang));
            if(max < count){
                max = count;
                language = lang;
                finalDecrypt = tempdecrypt;
            }
        }
        System.out.println("Language = "+language);
    }
    public void test(){
        breakVigenere();
    }
    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.test();
    }
}
