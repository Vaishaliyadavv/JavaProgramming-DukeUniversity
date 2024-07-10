
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    int mainKey1 ;
    int mainKey2;
    public CaesarCipherTwo(int key1 , int key2){
      alphabet = "abcdefghijklmnopqrstuvwxyz";
      shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
      shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
      mainKey1 = key1;
      mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i=0;i<encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toLowerCase(currChar));
            char newChar;
            if(idx != -1){
                if(i % 2 == 0){
                    newChar = shiftedAlphabet1.charAt(idx);
                } else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }
                if(Character.isUpperCase(currChar)){
                    newChar = Character.toUpperCase(newChar);
                }
                encrypted.setCharAt(i,newChar);
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
    
    public static void main(String[] args) {
        CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }
}
