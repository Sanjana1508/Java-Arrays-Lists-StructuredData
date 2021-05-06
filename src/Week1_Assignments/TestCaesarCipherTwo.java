package Week1_Assignments;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start){
        String result = "";
        for(int i=start;i<message.length();i+=2){
            result+=message.charAt(i);
        }
        return result;
    }
    private int[] countLetters(String encrypted){
        int freq[] = new int[26];
        String  alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<encrypted.length();i++){
            char ch = Character.toLowerCase(encrypted.charAt(i));
            int ind = alpha.indexOf(ch);
            if(ind != -1)
                freq[ind]++;
        }
        return freq;
    }
    private int maxIndex(int freq[]){
        int index = 0;
        for(int i=1;i<freq.length;i++){
            if(freq[index] < freq[i])
                index = i;
        }
        return index;
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted = "+encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted = "+decrypted);
        System.out.println("Decrypted = "+breakCaesarCipher(encrypted));
    }
    private int getKey(String s){
        int freq[] = countLetters(s);
        int maxI = maxIndex(freq);
        int dkey = maxI - 4;
        if(maxI < 4)
            dkey = 26 - (4 - maxI);
        return dkey;
    }
    public String  breakCaesarCipher(String input){
        String firstString = halfOfString(input, 0);
        String secondString = halfOfString(input,1);
        int dkey1 = getKey(firstString);
        int dkey2 = getKey(secondString);
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1,dkey2);
        return cc.decrypt(input);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo test = new TestCaesarCipherTwo();
        test.simpleTests();
    }
}
