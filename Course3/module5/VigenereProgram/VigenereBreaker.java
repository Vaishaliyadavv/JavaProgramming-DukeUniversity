import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char ch = message.charAt(i);
            slice.append(ch);
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cck = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            int sliceKey = cck.getKey(slice);
            key[i] = sliceKey;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0;
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxRealWords = 0;
        String bestDecryption = "";
        int bestKeyLength = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        int[] bestKey = null;
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String dec = vc.decrypt(encrypted);
            int realWords = countWords(dec, dictionary);
            if (realWords > maxRealWords) {
                maxRealWords = realWords;
                bestDecryption = dec;
                bestKeyLength = i;
                bestKey = key;
            }
        }
        if (bestKey != null) {
            System.out.print("Key: ");
            for (int k : bestKey) {
                System.out.print(k + " ");
            }
            System.out.println();
            System.out.println("Key Length: " + bestKeyLength);
        }
        return bestDecryption;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        String[] languageFiles = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        
        for(String language : languageFiles){
            FileResource dictResource = new FileResource("dictionaries/" + language);
            HashSet<String> dictionary = readDictionary(dictResource);
            languages.put(language,dictionary);
        }
        breakForAllLangs(encrypted, languages);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> count = new HashMap();
        
        for(String word : dictionary){
            for(char c : word.toCharArray()){
                if(Character.isLetter(c)){
                    c = Character.toLowerCase(c);
                    count.put(c ,count.getOrDefault(c,0) + 1);
                }
            }
        }
            
        char mostCommon = 'e';
        int maxCount = 0;
        for(char c : count.keySet()){
            if(count.get(c) > maxCount){
                maxCount = count.get(c);
                mostCommon = c;
            }
        }
        return mostCommon;
    }
    
    public String breakForAllLangs(String encrypted , HashMap<String, HashSet<String>> languages){
        int maxValidWords = 0;
        String decryptedMessage = "";
        String detectedLanguage = "";
        
        for(String lang : languages.keySet()){
            HashSet<String> dictionary = languages.get(lang);
            String currDecrypted = breakForLanguage(encrypted , dictionary);
            int validCount = countWords(currDecrypted , dictionary);
            
            if(validCount > maxValidWords){
                maxValidWords = validCount;
                decryptedMessage = currDecrypted;
                detectedLanguage = lang;
            }
        }
        System.out.println("Decrypted Message: " + decryptedMessage);
        System.out.println("Detected Language: " + detectedLanguage);
        return decryptedMessage;
    }
    
    
    
    
    

    public void tester() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, 4, 'e');
        System.out.println(Arrays.toString(key));
    }

    public void tester2() {
        breakVigenere();
    }

    public void testSecretMessage() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        FileResource dictResource = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictResource);

        
        String decryptedMessage = breakForLanguage(encrypted, dictionary);

        
        int validWords = countWords(decryptedMessage, dictionary);
        System.out.println("Number of valid words: " + validWords);
        System.out.println("-----------------------------------------------------------------------------------------------");

        
        String firstLine = decryptedMessage.split("\n")[0];
        System.out.println("First line of text: " + firstLine);
        System.out.println("-----------------------------------------------------------------------------------------------");

        
        int[] keyLength38 = tryKeyLength(encrypted, 38, 'e');
        VigenereCipher vc = new VigenereCipher(keyLength38);
        String decryptedWith38 = vc.decrypt(encrypted);
        int validWordsWith38 = countWords(decryptedWith38, dictionary);
        System.out.println("Number of valid words with key length 38: " + validWordsWith38);
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.testSecretMessage();
    }
}
