
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorsFilter implements Filter {
    private List<String> myDirectors;

    // Constructor that takes a comma-separated list of directors
    public DirectorsFilter(String directors) {
        myDirectors = Arrays.asList(directors.split(","));
    }

    // Method that checks if a movie has at least one of the specified directors
    @Override
    public boolean satisfies(String id) {
        String[] movieDirectors = MovieDatabase.getDirector(id).split(","); // Get the directors of the movie
        for (String director : myDirectors) {
            for (String movieDirector : movieDirectors) {
                if (movieDirector.trim().equalsIgnoreCase(director.trim())) {
                    return true; // Return true if there's a match
                }
            }
        }
        return false; // No matching director found
    }
}
