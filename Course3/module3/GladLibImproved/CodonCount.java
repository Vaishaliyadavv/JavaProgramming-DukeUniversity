
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String, Integer> codonMap;
    
    public CodonCount(){
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        codonMap.clear();
        dna = dna.toUpperCase().trim();
        
        for(int i=start;i+3 <= dna.length();i += 3){
            String codon = dna.substring(i,i+3);
            if(!codonMap.containsKey(codon)){
                codonMap.put(codon,1);
            }
            else {
                codonMap.put(codon , codonMap.get(codon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        int maxCount = 0;
        String mostCommonCodon = "";
        for(String codon : codonMap.keySet()){
            int count  = codonMap.get(codon);
            if(count > maxCount){
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }
    
    public void printCodonCounts(int start , int end){
        for(String codon : codonMap.keySet()){
            int count = codonMap.get(codon);
            if (count >= start && count <= end) {
                System.out.println(codon + "\t" + count);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();

        for (int frame = 0; frame < 3; frame++) {
            buildCodonMap(frame, dna);
            System.out.println("Reading frame starting with " + frame + " results in " + codonMap.size() + " unique codons");

            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));

            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1, 5);
            System.out.println();
        }
    }
}
