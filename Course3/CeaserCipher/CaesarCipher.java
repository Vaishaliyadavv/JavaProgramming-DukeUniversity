
/**
 * Write a description of CeaserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        
        String alUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alLower = "abcdefghijklmnopqrstuvwxyz";
        
        String shiftedAlUpper = alUpper.substring(key) + alUpper.substring(0,key);
        String shiftedAlLower = alLower.substring(key) + alLower.substring(0,key);
        
        for(int i = 0; i < encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            
            int idxUpper = alUpper.indexOf(currChar);
            int idxLower = alLower.indexOf(currChar);
            
            if(idxUpper != -1){
                char newChar = shiftedAlUpper.charAt(idxUpper);
                encrypted.setCharAt(i,newChar);
            } 
            else if(idxLower != -1){
                char newChar = shiftedAlLower.charAt(idxLower);
                encrypted.setCharAt(i,newChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testCesor(){
         FileResource fr = new FileResource();
         String message = fr.asString();
         int key = 12;
         String encrypted = encrypt(message, key);
         System.out.println("Key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input , int key1 , int key2){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0 ;i<input.length();i++){
            char currChar = input.charAt(i);
            if(i%2 == 0){
                encrypted.setCharAt(i, encrypt(Character.toString(currChar),key1).charAt(0));
            }
            else{
                encrypted.setCharAt(i, encrypt(Character.toString(currChar),key2).charAt(0));
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys(){
        int key1 = 2;
        int key2 = 20;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Top ncmy qkff vi vguv vbg ycpx";
        String encrypted = encryptTwoKeys(message, 26 - key1, 26 - key2);
        System.out.println("keys are " + key1 + " and " + key2 + "\n" + encrypted);
    }
}
