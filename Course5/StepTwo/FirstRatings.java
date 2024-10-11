
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        
        for(CSVRecord rec : parser){
            String id = rec.get(0);
            String title = rec.get(1);
            String year = rec.get(2);
            String country = rec.get(3);
            String genres = rec.get(4);
            String director = rec.get(5);
            int minutes = Integer.parseInt(rec.get(6));
            String poster = rec.get(7);
            
            Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
            movies.add(movie);
        }
        return movies;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        /*System.out.println("Number of movies: " + movies.size());
        for (Movie movie : movies) {
            System.out.println(movie);
        }*/
        
        int comedyCount = 0;
        for (Movie movie : movies) {
            if (movie.getGenres().toLowerCase().contains("comedy")) {
                comedyCount++;
            }
        }
        System.out.println("Number of movies in Comedy genre: " + comedyCount);
        
        /////////////////////////////////////////////
        
        
        int longMoviesCount = 0;
        for (Movie movie : movies) {
            if (movie.getMinutes() > 150) {
                longMoviesCount++;
            }
        }
        System.out.println("Number of movies longer than 150 minutes: " + longMoviesCount);
        
        
        /////////////////////////////////////////////////
        
        
        HashMap<String, Integer> directorCount = new HashMap<>();
        for (Movie movie : movies) {
            String[] directors = movie.getDirector().split(",");
            for (String director : directors) {
                director = director.trim();
                directorCount.put(director, directorCount.getOrDefault(director, 0) + 1);
            }
        }

        int maxCount = 0;
        ArrayList<String> maxDirectors = new ArrayList<>();
        for (String director : directorCount.keySet()) {
            int count = directorCount.get(director);
            if (count > maxCount) {
                maxCount = count;
                maxDirectors.clear();
                maxDirectors.add(director);
            } else if (count == maxCount) {
                maxDirectors.add(director);
            }
        }
        
        System.out.println("Maximum number of movies by any director: " + maxCount);
        System.out.println("Directors with this maximum: " + maxDirectors);
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        HashMap<String, Rater> raterMap = new HashMap<>();
        
        for (CSVRecord rec : parser) {
            String raterId = rec.get(0);
            String movieId = rec.get(1);
            double ratingValue = Double.parseDouble(rec.get(2));
            
            if (!raterMap.containsKey(raterId)) {
                raterMap.put(raterId, new Rater(raterId));
            }
            Rater rater = raterMap.get(raterId);
            rater.addRating(movieId, ratingValue);
        }
        
        raters.addAll(raterMap.values());
        return raters;
    }
    
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Total number of raters: " + raters.size());
        
        /*for (Rater rater : raters) {
            System.out.println("Rater ID: " + rater.getID() + ", Number of ratings: " + rater.numRatings());
            for (String movieId : rater.getItemsRated()) {
                System.out.println("   " + movieId + " rated " + rater.getRating(movieId));
            }
        }*/
        
        ///////////////////////////////////////////////
        
        
        String specificRaterId = "193"; // Change this to test different raters
        for (Rater rater : raters) {
            if (rater.getID().equals(specificRaterId)) {
                System.out.println("Number of ratings for rater " + specificRaterId + ": " + rater.numRatings());
            }
        }
        
        
        ///////////////////////////////////////////////////
        
        HashMap<String, Integer> ratingsCount = new HashMap<>();
        for (Rater rater : raters) {
            int count = rater.numRatings();
            ratingsCount.put(rater.getID(), count);
        }
        int maxRatings = Collections.max(ratingsCount.values());
        ArrayList<String> ratersWithMaxRatings = new ArrayList<>();
        for (String raterId : ratingsCount.keySet()) {
            if (ratingsCount.get(raterId) == maxRatings) {
                ratersWithMaxRatings.add(raterId);
            }
        }
        System.out.println("Maximum number of ratings by any rater: " + maxRatings);
        System.out.println("Raters with this maximum: " + ratersWithMaxRatings);

        
        ///////////////////////////////////////////////////////
        
        String specificMovieId = "1798709"; // Change this to test different movies
        int movieRatingCount = 0;
        for (Rater rater : raters) {
            if (rater.hasRating(specificMovieId)) {
                movieRatingCount++;
            }
        }
        System.out.println("Number of ratings for movie " + specificMovieId + ": " + movieRatingCount);

        /////////////////////////////////////////////////////
        
        HashSet<String> ratedMovies = new HashSet<>();
        for (Rater rater : raters) {
            for (String movieId : rater.getItemsRated()) {
                ratedMovies.add(movieId);
            }
        }
        System.out.println("Total number of different movies rated: " + ratedMovies.size());
    
    }
    
    public void test(){
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Available rater IDs:");
        for (Rater rater : raters) {
            if(rater.getID().equals("193")){
               System.out.println("193 exists");
        }
        }
    }
}
