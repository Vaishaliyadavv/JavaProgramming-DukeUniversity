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
        int[] bestKey = null;
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, 'e');
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

        FileResource dictResource = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictResource);

        String decryptedMessage = breakForLanguage(encrypted, dictionary);
        System.out.println(decryptedMessage);
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

        // Determine best decryption and key length
        String decryptedMessage = breakForLanguage(encrypted, dictionary);

        // Count valid words in decrypted message and print
        int validWords = countWords(decryptedMessage, dictionary);
        System.out.println("Number of valid words: " + validWords);
        System.out.println("-----------------------------------------------------------------------------------------------");

        // Print first line of decrypted message
        String firstLine = decryptedMessage.split("\n")[0];
        System.out.println("First line of text: " + firstLine);
        System.out.println("-----------------------------------------------------------------------------------------------");

        // Use tryKeyLength with key length of 38 and print number of valid words
        int[] keyLength38 = tryKeyLength(encrypted, 38, 'e');
        VigenereCipher vc = new VigenereCipher(keyLength38);
        String decryptedWith38 = vc.decrypt(encrypted);
        int validWordsWith38 = countWords(decryptedWith38, dictionary);
        System.out.println("Number of valid words with key length 38: " + validWordsWith38);
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.testSecretMessage();  // Directly test the specific file and answer quiz questions
    }
}
