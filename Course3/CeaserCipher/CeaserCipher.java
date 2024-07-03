
/**
 * Write a description of CeaserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CeaserCipher {
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
        //System.out.println("encrypted message : " + encrypt("String builder is powerful", 6));
        System.out.println("decrypted message : Yzxotm haorjkx oy vuckxlar ->" + encrypt("Yzxotm haorjkx oy vuckxlar",20));
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
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
}
