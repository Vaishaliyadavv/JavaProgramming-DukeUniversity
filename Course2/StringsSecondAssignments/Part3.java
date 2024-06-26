
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon , startIndex + 3);
        while(currIndex != -1){
            if((currIndex - startIndex ) % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon , currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna , startIndex , "TAA");
        int tagIndex = findStopCodon(dna , startIndex , "TAG");
        int tgaIndex = findStopCodon(dna , startIndex , "TGA");
        
        int minIndex = Math.min(taaIndex , Math.min(tagIndex , tgaIndex));
        
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex , minIndex + 3);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        
        while(true){
            int startCodonIndex = dna.indexOf("ATG", startIndex);
            if(startCodonIndex == -1){
                break;
            }
            String currentGene = findGene(dna.substring(startCodonIndex));
            if(currentGene.isEmpty()){
                startIndex = startCodonIndex + 3;  
            }
            else{
                System.out.println(currentGene);
            
                startIndex = dna.indexOf(currentGene, startCodonIndex) + 
                            currentGene.length();
            }
        }
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        
        while(true){
            int startCodonIndex = dna.indexOf("ATG", startIndex);
            if(startCodonIndex == -1){
                break;
            }
            String currentGene = findGene(dna.substring(startCodonIndex));
            if(currentGene.isEmpty()){
                startIndex = startCodonIndex + 3;
            }
            else { 
                count++;
                startIndex = startCodonIndex + currentGene.length();
            }
        }
        return count;
    }
    
    public void testCountGenes() {
        String dna1 = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA strand: " + dna1 + " Number of genes: " + countGenes(dna1)); // Expected: 2

        String dna2 = "ATGxxxTAAATGyyyTAGATGzzzTGA";
        System.out.println("DNA strand: " + dna2 + " Number of genes: " + countGenes(dna2)); // Expected: 3

        String dna3 = "ATGATGATGATG";
        System.out.println("DNA strand: " + dna3 + " Number of genes: " + countGenes(dna3)); // Expected: 0

        String dna4 = "ATGTAATAGTGA";
        System.out.println("DNA strand: " + dna4 + " Number of genes: " + countGenes(dna4)); // Expected: 1

        String dna5 = "ATGATGTAATAG";
        System.out.println("DNA strand: " + dna5 + " Number of genes: " + countGenes(dna5)); // Expected: 2

        String dna6 = "CCCCCC";
        System.out.println("DNA strand: " + dna6 + " Number of genes: " + countGenes(dna6)); // Expected: 0
    }
    
    public static void main(String[] args) {
        Part3 p = new Part3();
        p.testCountGenes();
    }
}
