
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//import edu.duke.*;
public class Part2 {

    public String findSimpleGene(String dna , String startC , String stopC){
        int startIn = dna.indexOf(startC);
        if(startIn == -1){
            return "No start Codon";
        }
        int stopIn = dna.indexOf(stopC , startIn + 3);
        if(stopIn == -1){
            return "No Stop Codon";
        }
        String result = dna.substring(startIn , stopIn + 3);
        if(result.length() % 3 == 0){
            return result + "(Valid gene)";
        }
        return result + "(Not a valid Gene)";
    }
    
    public void testSimpleGene(){
        String dna1 = "AAGGTTGGG";
        String dna2 = "AATGCCGTTAAGAA";
        String dna3 = "TTATGTTATAG";
        String dna4 = "AATGCCGTTTAGG";
        String dna5 = "CCCATGCCCTAA";
        
        String[] dnas = {dna1, dna2 , dna3 , dna4 , dna5};
        for(String dna : dnas){
            String startC = "ATG";
            String stopC = "TAA";
            System.out.println("Testing DNA string: " + dna);
            String gene = findSimpleGene(dna,startC , stopC);
            System.out.println("Gene found : " + gene);
        }
        
    }
    
    public static void main(String[] args){
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}
