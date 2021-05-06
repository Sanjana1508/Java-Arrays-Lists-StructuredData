package Week1_Assignments;

import edu.duke.FileResource;

public class CaesarBreaker {

    public int[] countLetters(String encrypted){
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
    public int maxIndex(int freq[]){
        int index = 0;
        for(int i=1;i<freq.length;i++){
            if(freq[index] < freq[i])
                index = i;
        }
        return index;
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int dkey = getKey(encrypted);
        return cc.encrypt(encrypted, 26-dkey);
    }
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("Encrypted Message = "+encrypted);
        System.out.println("Decrypted Message = "+decrypt(encrypted));
    }
    public String halfOfString(String message, int start){
        String result = "";
        for(int i=start;i<message.length();i+=2){
            result+=message.charAt(i);
        }
        return result;
    }
    public void testHalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis",0));
        System.out.println(halfOfString("Qbkm Zgis",1));
    }
    public int getKey(String s){
        int freq[] = countLetters(s);
        int maxI = maxIndex(freq);
        int dkey = maxI - 4;
        if(maxI < 4)
            dkey = 26 - (4 - maxI);
        return dkey;
    }
    public String decryptTwoKeys(String encrypted){
        String firstString = halfOfString(encrypted, 0);
        String secondString = halfOfString(encrypted,1);
        int dkey1 = getKey(firstString);
        int dkey2 = getKey(secondString);
        System.out.println("The two keys are "+ dkey1+" and "+dkey2);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted,26-dkey1,26-dkey2);
        return decrypted;
    }
    public void testDecryptTwoKeys(){
        CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("Encrypted = "+encrypted);
        System.out.println("Decrypted = "+decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
    }
    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
        cb.testHalfOfString();
        cb.testDecryptTwoKeys();
    }
}
