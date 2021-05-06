package Week1_Assignments;

import edu.duke.FileResource;

public class CaesarCipher {
    public String encrypt(String input , int key){
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encryptMsg = new StringBuilder(input);
        String shift_alphabetUpper = alphabetUpper.substring(key)+alphabetUpper.substring(0,key);
        String shift_alphabetLower = alphabetLower.substring(key)+alphabetLower.substring(0,key);
        int index = -1;
        for(int i=0;i<input.length();i++){
            char ch = input.charAt(i);
            if(Character.isLowerCase(ch)) {
                index = alphabetLower.indexOf(ch);
                if(index != -1)
                    encryptMsg.setCharAt(i,shift_alphabetLower.charAt(index));
            }
            else if(Character.isUpperCase(ch)) {
                index = alphabetUpper.indexOf(ch);
                if(index != -1)
                    encryptMsg.setCharAt(i,shift_alphabetUpper.charAt(index));
            }
        }
        return encryptMsg.toString();
    }
    public void testEncrypt(){
        System.out.println("Encrypted String = "+encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"
                ,15));
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    public String encryptTwoKeys(String input, int key1,int key2){
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encryptMsg = new StringBuilder(input);
        String shift_alphabetUpperKey1 = alphabetUpper.substring(key1)+alphabetUpper.substring(0,key1);
        String shift_alphabetLowerKey1 = alphabetLower.substring(key1)+alphabetLower.substring(0,key1);
        String shift_alphabetUpperKey2 = alphabetUpper.substring(key2)+alphabetUpper.substring(0,key2);
        String shift_alphabetLowerKey2 = alphabetLower.substring(key2)+alphabetLower.substring(0,key2);
        for(int i=0;i<input.length();i++){
            char ch = input.charAt(i);
            if(i%2 == 0){
                if(Character.isLowerCase(ch)){
                    encryptMsg = setCharacter(alphabetLower,shift_alphabetLowerKey1,encryptMsg,ch,i);
                }
                else if(Character.isUpperCase(ch)){
                    encryptMsg = setCharacter(alphabetUpper,shift_alphabetUpperKey1,encryptMsg,ch,i);
                }
            }
            else{
                if(Character.isLowerCase(ch))
                    encryptMsg = setCharacter(alphabetLower,shift_alphabetLowerKey2,encryptMsg,ch,i);
                else if(Character.isUpperCase(ch))
                    encryptMsg = setCharacter(alphabetUpper,shift_alphabetUpperKey2,encryptMsg,ch,i);
            }
        }
        return encryptMsg.toString();
    }
    public StringBuilder setCharacter(String alphabet, String shift_alphabet,StringBuilder encryptMsg,char ch,int i){
        int index = -1;
        index = alphabet.indexOf(ch);
        if(index != -1)
            encryptMsg.setCharAt(i,shift_alphabet.charAt(index));
        return encryptMsg;
    }
    public void testEncryptTwoKeys(){
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21));
    }
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        cipher.testEncrypt();
        cipher.testEncryptTwoKeys();
    }
}

