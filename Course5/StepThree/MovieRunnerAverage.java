
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {

    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of raters: " + sr.getRaterSize());
        
        int minimalRaters = 3; // Change this to test with different minimal raters
        ArrayList<Rating> averageRatings = sr.getAverageRatings(minimalRaters);
        
        // Sort average ratings
        Collections.sort(averageRatings);
        
        // Print the average ratings along with movie titles
        for (Rating rating : averageRatings) {
            String movieTitle = sr.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + movieTitle);
        }
    }
    
     public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        String movieTitle = "The Godfather"; // Example movie title

        String movieID = sr.getID(movieTitle);
        if (!movieID.equals("NO SUCH TITLE.")) {
            double averageRating = sr.getAverageByID(movieID, 1); // Use 1 as minimal rater for simplicity
            System.out.println("Average rating for " + movieTitle + ": " + averageRating);
        } else {
            System.out.println(movieID);
        }
    }
}
