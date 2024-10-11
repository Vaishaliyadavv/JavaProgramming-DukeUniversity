
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        myRaters = FirstRatings.loadRaters(ratingsfile);
    }
   
    public int getRaterSize() {
        return myRaters.size();
    }
    
     public ArrayList<Rater> getRaters() {
        return myRaters; 
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double totalRating = 0.0;
        int count = 0;

        for (Rater rater : myRaters) {
            if (rater.hasRating(id)) {
                totalRating += rater.getRating(id);
                count++;
            }
        }

        if (count >= minimalRaters) {
            return totalRating / count;
        } else {
            return 0.0;
        }
    }
    
        public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter()); // Get all movie IDs
        for (String movieId : movieIDs) {
            double averageRating = getAverageRating(movieId, minimalRaters);
            if (averageRating != -1) {
                averageRatings.add(new Rating(movieId, averageRating)); // Add movie with average rating
            }
        }
        return averageRatings; // Return all average ratings
    }
    
    private double getAverageRating(String movieId, int minimalRaters) {
        double totalRating = 0.0;
        int count = 0;
        for (Rater rater : myRaters) { // Assuming myRaters is an ArrayList<Rater>
            if (rater.hasRating(movieId)) {
                totalRating += rater.getRating(movieId);
                count++;
            }
        }
        if (count >= minimalRaters) {
            return totalRating / count; // Calculate average if we have enough ratings
        }
        return -1; // Not enough ratings
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatings = new ArrayList<>();
        ArrayList<String> filteredMovieIDs = MovieDatabase.filterBy(filterCriteria);
        
        for (String movieID : filteredMovieIDs) {
            double averageRating = getAverageRating(movieID, minimalRaters);
            if (averageRating != -1) {
                averageRatings.add(new Rating(movieID, averageRating));
            }
        }
        return averageRatings;
        
    }
}
