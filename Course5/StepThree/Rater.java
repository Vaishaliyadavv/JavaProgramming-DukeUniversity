
/**
 * Write a description of Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public interface Rater {
    String getID(); 
    
    int numRatings();
    
    void addRating(String item, double rating);
    
    boolean hasRating(String id);
    
    double getRating(String id);
    
    ArrayList<String> getItemsRated();
    
}
