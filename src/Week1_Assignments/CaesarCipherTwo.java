package Week1_Assignments;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;

    public CaesarCipherTwo(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encrypt(String input) {
        StringBuilder encryptMsg = new StringBuilder(input);
        int index = -1;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (i % 2 == 0) {
                index = alphabet.indexOf(ch);
                if (index != -1)
                    encryptMsg.setCharAt(i, shiftedAlphabet1.charAt(index));
            } else {
                index = alphabet.indexOf(ch);
                if (index != -1)
                    encryptMsg.setCharAt(i, shiftedAlphabet2.charAt(index));
            }
        }
        return encryptMsg.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1,26-key2);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
