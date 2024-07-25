import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<>();
        usedWords = new ArrayList<>();
        usedCategories = new ArrayList<>();
        myRandom = new Random();
        initializeFromSource(dataSourceDirectory);
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<>();
        usedWords = new ArrayList<>();
        usedCategories = new ArrayList<>();
        myRandom = new Random();
        initializeFromSource(source);
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", 
            "name", "animal", "timeframe", "verb", "fruit"};
        for(String category : categories){
            ArrayList<String> list = readIt(source + "/" + category + ".txt");
            myMap.put(category, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            if (!usedCategories.contains(label)) {
                usedCategories.add(label);
            }
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")) {
            return "" + (myRandom.nextInt(50) + 5);
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        while (usedWords.contains(sub)){
            sub = getSubstitute(w.substring(first + 1, last));
        }
        usedWords.add(sub);
        return prefix + sub + suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for (String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        StringBuilder story = new StringBuilder();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()){
                story.append(processWord(word)).append(" ");
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()){
                story.append(processWord(word)).append(" ");
            }
        }
        return story.toString();
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()){
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int total = 0;
        for (ArrayList<String> list : myMap.values()) {
            total += list.size();
        }
        return total;
    }
    
    public int totalWordsConsidered() {
        int total = 0;
        for (String category : usedCategories) {
            total += myMap.get(category).size();
        }
        return total;
    }
    
    public void makeStory(){
        usedWords.clear();
        usedCategories.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal number of words possible to pick from: " + totalWordsInMap());
        System.out.println("Total number of words considered: " + totalWordsConsidered());
    }

    public static void main(String[] args) {
        GladLibMap gladLib = new GladLibMap();
        gladLib.makeStory();
    }
}
