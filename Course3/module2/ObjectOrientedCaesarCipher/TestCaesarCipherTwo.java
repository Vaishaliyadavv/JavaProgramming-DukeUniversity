
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    private int[] countLetters(String message){
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
    
    public String halfOfString(String message ,int start){
        String output = "";
        for(int i = start; i<message.length();i=i+2){
            output = output + message.charAt(i);
        }
        return output;
    }
    
    private int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k<vals.length;k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    private String breakCaesarCipher(String input) {
        String first = halfOfString(input,0);
        String second = halfOfString(input,1);
        
        int[] freqs1 = countLetters(first);
        int[] freqs2 = countLetters(second);
        int maxDex1 = maxIndex(freqs1);
        int maxDex2 = maxIndex(freqs2);
        
        int key1 = maxDex1 - 4;
        int key2 = maxDex2 - 4;
        if(maxDex1 < 4){
            key1 = 26 - (4-maxDex1);
        }
        if(maxDex2 < 4){
            key2 = 26 - (4-maxDex2);
        }
        System.out.println("Key1: " + key1 + " Key2: " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        return cc.decrypt(input);
    }
    
    public void simpleTests(){
        CaesarCipherTwo cc = new CaesarCipherTwo(1, 24);
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        //String encrypted = cc.encrypt(message);
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String decrypted = cc.decrypt(encrypted);
        
        //System.out.println(message);
        System.out.println(encrypted);
        //System.out.println("ENCRYPTED : " + encrypted);
        System.out.println("DECRYPTED : " + decrypted);
        System.out.println("DECRYPTED USING BREAK : " + breakCaesarCipher(encrypted));
    }
}
