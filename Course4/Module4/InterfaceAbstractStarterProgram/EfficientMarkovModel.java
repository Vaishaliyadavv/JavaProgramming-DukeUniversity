
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int order;
    private HashMap<String, ArrayList<String>> followMap;

    public EfficientMarkovModel(int order) {
        this.order = order;
        myRandom = new Random();
        followMap = new HashMap<>();
    }

    @Override
    public void setTraining(String text) {
        myText = text;
        buildMap();
    }

    // Builds the HashMap to store the follow-up characters for each substring
    private void buildMap() {
        followMap.clear();
        for (int i = 0; i <= myText.length() - order; i++) {
            String key = myText.substring(i, i + order);
            String follow = (i + order < myText.length()) ? myText.substring(i + order, i + order + 1) : "";
            
            if (!followMap.containsKey(key)) {
                followMap.put(key, new ArrayList<>());
            }
            followMap.get(key).add(follow);
        }
        // Call the method to print HashMap info after it's built
        printHashMapInfo();
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);

        for (int k = 0; k < numChars - order; k++) {
            ArrayList<String> follows = followMap.get(key);
            if (follows == null || follows.size() == 0) {
                break;
            }
            String next = follows.get(myRandom.nextInt(follows.size()));
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }

    // Prints HashMap information after building the map
    protected void printHashMapInfo() {
        // Comment out the print statement that prints the whole map
        // System.out.println(followMap);  // Commented out as per the requirements

        System.out.println("Number of keys: " + followMap.size());

        int maxSize = 0;
        ArrayList<String> largestKeys = new ArrayList<>();

        for (String key : followMap.keySet()) {
            int size = followMap.get(key).size();
            if (size > maxSize) {
                maxSize = size;
                largestKeys.clear();
                largestKeys.add(key);
            } else if (size == maxSize) {
                largestKeys.add(key);
            }
        }

        System.out.println("Size of the largest value (ArrayList): " + maxSize);
        System.out.println("Keys with the largest size: " + largestKeys);
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel of order " + order;
    }
}
