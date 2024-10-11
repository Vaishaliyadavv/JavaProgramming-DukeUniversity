
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
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

        for (Movie movie : myMovies) {
            String id = movie.getID();
            double average = getAverageByID(id, minimalRaters);
            if (average > 0.0) {
                averageRatings.add(new Rating(id, average));
            }
        }

        return averageRatings;
    }
    
     public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID not found.";
    }
    
    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
