package Week1_Assignments;

public class CaesarCipher1 {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher1(int key){
        this.mainKey = key;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
    }
    public String encrypt(String input){
        StringBuilder encryptMsg = new StringBuilder(input);
        for(int i=0;i<input.length();i++){
            int index = alphabet.indexOf(input.charAt(i));
            if(index != -1)
            encryptMsg.setCharAt(i,shiftedAlphabet.charAt(index));
        }
        return  encryptMsg.toString();
    }
    public String decrypt(String input){
        CaesarCipher1 cc = new CaesarCipher1(26-mainKey);
        String decryptMsg = cc.encrypt(input);
        return decryptMsg;
    }
}
