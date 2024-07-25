
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;
import java.util.*;
public class WordsInFiles {
    private HashMap<String , ArrayList<String>> wordFileMap;
    
    public WordsInFiles(){
        wordFileMap = new HashMap<String , ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr  = new FileResource(f);
        String fileName = f.getName();
        for(String word : fr.words()){
            if(!wordFileMap.containsKey(word)){
                wordFileMap.put(word,new ArrayList<String>());
            }
            ArrayList<String> fileList = wordFileMap.get(word);
            if(!fileList.contains(fileName)){
                fileList.add(fileName);
            }
        }
    }
    
    public void buildWordFileMap(){
        wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(ArrayList<String> fileList : wordFileMap.values()){
            if(fileList.size() > max){
                max = fileList.size();
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for(String word : wordFileMap.keySet()){
            ArrayList<String> fileList = wordFileMap.get(word);
            if(fileList.size() == number){
                words.add(word);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> fileList = wordFileMap.get(word);
        if(fileList != null){
            for(String fileName : fileList){
                System.out.println(fileName);
            }
        }
        else {
            System.out.println("word not fount in any file");
        }
    }
    
     public void tester() {
        buildWordFileMap();
        int maxFiles = maxNumber();
        System.out.println("Maximum number of files any word is in: " + maxFiles);

        ArrayList<String> maxWords = wordsInNumFiles(maxFiles);
        System.out.println("Words that appear in the maximum number of files (" + maxFiles + "): " + maxWords);
        System.out.println("total words in max files " + maxWords.size());
        for (String word : maxWords) {
            System.out.println("The word '" + word + "' appears in the following files:");
            printFilesIn(word);
        }
    }
    
    public void tester2() {
        System.out.println("--------------------------------------------------------------");
        buildWordFileMap();
        ArrayList<String> wordsInFourFiles = wordsInNumFiles(4);
        System.out.println("Number of words that appear in exactly four of the seven files: " + wordsInFourFiles.size());
        
        String targetWordSea = "sea";
        ArrayList<String> fileListForSea = wordFileMap.get(targetWordSea);
        if (fileListForSea != null) {
            List<String> allFiles = new ArrayList<>(Arrays.asList("caesar.txt", "confucius.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"));
            allFiles.removeAll(fileListForSea);
            System.out.println("The word 'sea' does NOT appear in: " + allFiles);
        } else {
            System.out.println("The word 'sea' does not appear in any file.");
        }
        
        String targetWordTree = "tree";
        ArrayList<String> fileListForTree = wordFileMap.get(targetWordTree);
        if (fileListForTree != null) {
            System.out.println("The word 'tree' appears in: " + fileListForTree);
        } else {
            System.out.println("The word 'tree' does not appear in any file.");
        }
    }
}
