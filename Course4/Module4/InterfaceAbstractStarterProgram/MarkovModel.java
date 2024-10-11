
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
public class MarkovModel extends AbstractMarkovModel{
    private int n;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        this.n = n;
    }
    
    public String getRandomText(int numChars) {
        if (myText == null || myText.length() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);
        for (int k = 0; k < numChars - n; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order " + n;
    }
}
