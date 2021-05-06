package Week1_Assignments;

import edu.duke.FileResource;

public class TestCaesarCipher1 {
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
        CaesarCipher1 cc = new CaesarCipher1(18);
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted = "+encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted = "+decrypted);
        System.out.println(breakCaesarCipher(encrypted));
    }
    public String  breakCaesarCipher(String input){
        int freq[] = countLetters(input);
        int maxI = maxIndex(freq);
        int dkey = maxI - 4;
        if(maxI < 4)
            dkey = 26 - (4 - maxI);
        CaesarCipher1 cc = new CaesarCipher1(dkey);
       return cc.decrypt(input);
    }

    public static void main(String[] args) {
        TestCaesarCipher1 test = new TestCaesarCipher1();
        test.simpleTests();
    }
}
