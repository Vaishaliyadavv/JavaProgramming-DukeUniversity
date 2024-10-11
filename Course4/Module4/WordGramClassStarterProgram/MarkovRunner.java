
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() {
        FileResource fr = new FileResource(); // Update this with the correct path
        String st = fr.asString();
        st = st.replace('\n', ' ');
    
        EfficientMarkovWord markov = new EfficientMarkovWord(3); // Set order to 2
        markov.setRandom(371); // Set the random seed to 65
        markov.setTraining(st); // Set the training text
    
        // Generate and print some random text (optional)
        System.out.println(markov.getRandomText(100)); // Example to generate 100 words
     }
    
    public void testHashMap() {
        String trainingText = "this is a test yes this is really a test";
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        markov.setRandom(42);
        markov.setTraining(trainingText);
        
        // Generate random text to check the implementation
        System.out.println("Generated Text: " + markov.getRandomText(50));
    }

    public void compareMethods() {
        String trainingText = "hawthorne.txt"; // Adjust the text source accordingly

        MarkovWord markov1 = new MarkovWord(2);
        IMarkovModel markov2 = new EfficientMarkovWord(2);

        long startTime = System.nanoTime();
        markov1.setRandom(42);
        markov1.setTraining(trainingText);
        markov1.getRandomText(100); // Generate random text
        long endTime = System.nanoTime();
        System.out.println("MarkovWord Time: " + (endTime - startTime));

        startTime = System.nanoTime();
        markov2.setRandom(42);
        markov2.setTraining(trainingText);
        markov2.getRandomText(100); // Generate random text
        endTime = System.nanoTime();
        System.out.println("EfficientMarkovWord Time: " + (endTime - startTime));
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}