import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private int myOrder;
    private Random myRandom;
    private HashMap<WordGram, ArrayList<String>> myMap;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<>();
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo(); // Call to print info after building the HashMap
    }

    // Build the HashMap of WordGrams to their possible follows
    private void buildMap() {
        myMap.clear();

        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            String nextWord = (i + myOrder < myText.length) ? myText[i + myOrder] : "";

            myMap.putIfAbsent(wg, new ArrayList<>());
            if (!nextWord.isEmpty()) {
                myMap.get(wg).add(nextWord);
            }
        }
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        return myMap.getOrDefault(kGram, new ArrayList<>());
    }

    @Override
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram key = new WordGram(myText, index, myOrder);

        for (int k = 0; k < numWords; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next).append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public void printHashMapInfo() {
        // Commented out detailed printing of the map
        // System.out.println("HashMap contents:"); 
        // for (Map.Entry<WordGram, ArrayList<String>> entry : myMap.entrySet()) {
        //     System.out.println(entry.getKey() + ": " + entry.getValue());
        // }

        System.out.println("Number of keys in HashMap: " + myMap.size());
        int largestSize = 0;

        for (ArrayList<String> value : myMap.values()) {
            largestSize = Math.max(largestSize, value.size());
        }

        System.out.println("Size of the largest follow ArrayList: " + largestSize);

        // Find keys with maximum size
        System.out.println("Keys with maximum size:");
        for (Map.Entry<WordGram, ArrayList<String>> entry : myMap.entrySet()) {
            if (entry.getValue().size() == largestSize) {
                System.out.println(entry.getKey() + " has follows: " + entry.getValue());
            }
        }
    }
}
