
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainkey;
    
    public CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        mainkey = key;
    }
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0;i<sb.length();i++){
            char ch = sb.charAt(i);
            char lowerCh = Character.toLowerCase(ch);
            int idx = alphabet.indexOf(lowerCh);
            if(idx != -1){
                char newCh = shiftedAlphabet.charAt(idx);
                if(Character.isUpperCase(ch)){
                    sb.setCharAt(i,Character.toUpperCase(newCh));
                }
                else {
                    sb.setCharAt(i,newCh);
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainkey);
        return cc.encrypt(input);
    }
}
