package Week1_Assignments;

public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiouAEIOU";
        int index = vowels.indexOf(ch);
        return index == -1 ? false : true;
    }
    public void testIsVowel(){
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            if(isVowel(phrase.charAt(i))){
                sb.setCharAt(i,ch);
            }
        }
        return sb.toString();
    }
    public void testReplaceVowels(){
        System.out.println(replaceVowels("Hello World",'*'));
    }
    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            if(phrase.charAt(i) == ch){
                if(i%2 == 0)
                    sb.setCharAt(i,'*');
                else
                    sb.setCharAt(i,'+');
            }
        }
        return sb.toString();
    }
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }
    public static void main(String[] args) {
        WordPlay wordPlay = new WordPlay();
        wordPlay.testIsVowel();
        wordPlay.testReplaceVowels();
        wordPlay.testEmphasize();
    }
}
