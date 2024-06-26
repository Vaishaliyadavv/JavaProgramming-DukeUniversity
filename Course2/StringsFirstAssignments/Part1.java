//import edu.duke.*;
public class Part1 {

    public String findSimpleGene(String dna){
        int atgIn = dna.indexOf("ATG");
        if(atgIn == -1){
            return "";
        }
        int taaIn = dna.indexOf("TAA" , atgIn + 3);
        if(taaIn == -1){
            return "";
        }
        String result = dna.substring(atgIn , taaIn + 3);
        if(result.length() % 3 == 0){
            return result + "(Valid gene)";
        }
        return result + "(Not a valid Gene)";
    }
    
    public void testSimpleGene(){
        String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
        String dna2 = "AATGCCGTTAAGAA";
        String dna3 = "TTATGTTATAG";
        String dna4 = "AATGCCGTTTAGG";
        String dna5 = "CCCATGCCCTAA";
        
        String[] dnas = {dna1, dna2 , dna3 , dna4 , dna5};
        for(String dna : dnas){
            String gene = findSimpleGene(dna);
            System.out.println("Gene found -" + gene);
        }
        
    }
    
    public static void main(String[] args){
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}
