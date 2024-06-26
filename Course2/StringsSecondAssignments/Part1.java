
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    public void testFindStopCodon(){
        String dna  = "xxxyyyzzzTAAxxxyyyzzzTAGxxxyyyzzz";
        System.out.println(findStopCodon(dna, 0, "TAA"));
        System.out.println(findStopCodon(dna, 0, "TAG"));
        System.out.println(findStopCodon(dna, 0, "TGA"));
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
    
    public void testFindGene(){
        String dna1 = "AATGCTAACTAGCTGACTAAT";
        String dna2 = "ATGxxxTAG";
        String dna3 = "ATGxxxTGA";
        String dna4 = "ATGxxxTAAyyyTAGzzzTGA";
        String dna5 = "xxxATGxxxTAG";
        
        System.out.println("DNA strand: " + dna1 + " Gene: " + findGene(dna1));
        System.out.println("DNA strand: " + dna2 + " Gene: " + findGene(dna2)); 
        System.out.println("DNA strand: " + dna3 + " Gene: " + findGene(dna3)); 
        System.out.println("DNA strand: " + dna4 + " Gene: " + findGene(dna4)); 
        System.out.println("DNA strand: " + dna5 + " Gene: " + findGene(dna5));
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
    public static void main(String[] args) {
        Part1 p = new Part1();
        p.testFindStopCodon();
        p.testFindGene();
        p.printAllGenes("ATGxxxTAAyyyATGzzzTAGuuuATGvvvTGA");
    }
}
