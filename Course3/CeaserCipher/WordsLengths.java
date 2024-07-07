
/**
 * Write a description of WordsLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class WordsLengths {
    public void countWordLength(FileResource resource, int[] counts){
        for(String word : resource.words()){
            if(!word.isEmpty()){
                int wordLength = word.length();
                
                if(!Character.isLetter(word.charAt(0))){
                    wordLength--;
                }
                if(wordLength >1 && !Character.isLetter(word.charAt(wordLength -1))){
                    wordLength--;
                }
                
                if(wordLength > 0){
                    counts[wordLength]++;
                    
                }
                else if(wordLength >= counts.length){
                    counts[counts.length -1]++;
                }
            }
        }
    }
    
    public void testCountWordLength(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLength(resource, counts);
        for(int k=1;k<counts.length;k++){
            if(counts[k] != 0){
                System.out.println(counts[k] + " words of length " + k + " : " + k);
            }
        }
        int maxIndex = indexOfMax(counts);
        System.out.println("maximum index is " + maxIndex + " : " + counts[maxIndex]);
    }
    
    public int indexOfMax(int[] values){
        int maxIndex = 0;
        
        for(int k=0;k<values.length;k++){
            if(values[k]>values[maxIndex]){
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
}
