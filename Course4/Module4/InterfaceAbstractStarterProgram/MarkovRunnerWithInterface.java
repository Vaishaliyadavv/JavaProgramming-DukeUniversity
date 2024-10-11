import edu.duke.FileResource;

public class MarkovRunnerWithInterface {
    
    // Runs the provided Markov model with the specified text, size, and seed
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("Running with " + markov);
        for (int k = 0; k < 3; k++) {
            String randomText = markov.getRandomText(size);
            printOut(randomText);
        }
    }

    // New method to test EfficientMarkovModel with order 5 and seed 615
    public void testEfficientMarkovModelOrder6() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        int size = 200;
        int seed = 531;

        EfficientMarkovModel efficientMarkov = new EfficientMarkovModel(5);
        runModel(efficientMarkov, st, size, seed);
        
        // The number of keys and HashMap information will be printed within the EfficientMarkovModel
    }

    // Utility function to print text output in fixed-size chunks
    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (String word : words) {
            System.out.print(word + " ");
            psize += word.length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    // You can include other methods (compareMethods, etc.) from previous versions
}
