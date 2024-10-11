
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String movieId, double ratingValue) {
        Rating rating = new Rating(movieId, ratingValue);
        myRatings.put(movieId, rating);
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
        
    }

    public double getRating(String item) {
        if (hasRating(item)) {
            return myRatings.get(item).getValue();
        }
        return -1; 
    }

    public int numRatings() {
        return myRatings.size();
        
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<>(myRatings.keySet());
    }
}
