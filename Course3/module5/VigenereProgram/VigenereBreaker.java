import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = "";
        for(int i = whichSlice;i<message.length();i = i + totalSlices){
            char ch = message.charAt(i);
            slice += ch ;
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cck = new CaesarCracker(mostCommon);
        for(int i = 0; i< klength ; i++){
            String slice = sliceString(encrypted, i , klength);
            int sliceKey = cck.getKey(slice);
            key[i] = sliceKey;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        int[] key = tryKeyLength(encrypted, 4, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public void tester(){
        //System.out.println(sliceString("abcdefghijklm", 0, 3));
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, 4, 'e');
        System.out.println(Arrays.toString(key));
    }
    
    public void tester2(){
        breakVigenere();
    }
}
