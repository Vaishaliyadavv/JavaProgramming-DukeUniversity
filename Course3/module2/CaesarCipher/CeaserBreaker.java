
/**
 * Write a description of CeaserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CeaserBreaker {
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int key = getKey(encrypted);
        return cc.encrypt(encrypted , (26 - key));
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k<vals.length;k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource();
        System.out.println(decrypt(fr.asString()));
    }
    
    public String halfOfString(String message ,int start){
        String output = "";
        for(int i = start; i<message.length();i=i+2){
            output = output + message.charAt(i);
        }
        return output;
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public void decryptTwoKeys(String encrypted){
        String first = halfOfString(encrypted, 0);
        String second  = halfOfString(encrypted, 1);
        
        int key1 = getKey(first);
        int key2 = getKey(second);
        
        System.out.println("Key 1 : " + key1 + ", key 2 : " + key2);
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encryptTwoKeys(encrypted, 26 - key1 , 26 - key2));
    }

    public void testHalfOfString() {
        System.out.println(halfOfString("Qbkm Zgis", 0)); //Qk gs
        System.out.println(halfOfString("Qbkm Zgis", 1)); //bmZi
        System.out.println(halfOfString("Hello World", 0)); //HloWrd
        System.out.println(halfOfString("Hello World", 1)); //el ol
        System.out.println(halfOfString("Hello World", 2)); //loWrd
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        decryptTwoKeys(message);
    }
}





