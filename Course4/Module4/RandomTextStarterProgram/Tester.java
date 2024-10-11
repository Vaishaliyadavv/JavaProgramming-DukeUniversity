
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Tester {
    public void testGetFollows(){
        MarkovFour m1 = new MarkovFour();
        m1.setTraining("this is a test yes this is a test.");
        System.out.println(m1.getFollows("t"));
        System.out.println(m1.getFollows("hi"));
        System.out.println(m1.getFollows("st"));
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println(markov.getFollows("he").size());
    }
}
