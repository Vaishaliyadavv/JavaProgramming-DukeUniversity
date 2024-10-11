import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings(int minimalRaters) {
        // Create a ThirdRatings object, pass the ratings file
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        // Initialize the MovieDatabase
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        // Get average ratings for movies with the specified minimal number of raters
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatings(minimalRaters);
        System.out.println("Number of movies with ratings: " + avgRatings.size());

        // Sort the list of average ratings
        Collections.sort(avgRatings);

        // Print each movie's rating and title
        for (Rating rating : avgRatings) {
            System.out.printf("%.2f %s\n", rating.getValue(), MovieDatabase.getTitle(rating.getItem()));
        }
        
         System.out.println("Total rated movies returned: " + avgRatings.size());
    }
    
     public void printAverageRatingsByYear(int minimalRaters, int year) {
        // Create ThirdRatings object
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        // Initialize the MovieDatabase
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        // Create a YearAfterFilter for movies released after the specified year
        Filter yearFilter = new YearAfterFilter(year);

        // Get the list of average ratings with the year filter applied
        ArrayList<Rating> avgRatingsByYear = thirdRatings.getAverageRatingsByFilter(minimalRaters, yearFilter);
        System.out.println("Number of movies found: " + avgRatingsByYear.size());

        // Sort the ratings by their values
        Collections.sort(avgRatingsByYear);

        // Print each movie's rating, year, and title
        for (Rating rating : avgRatingsByYear) {
            String movieID = rating.getItem();
            String title = MovieDatabase.getTitle(movieID);
            int movieYear = MovieDatabase.getYear(movieID);
            double movieRating = rating.getValue();
            System.out.printf("%.2f %d %s\n", movieRating, movieYear, title);
        }
        
    }
    
    public void printAverageRatingsByGenre(int minimalRaters, String genre) {
        
        // Create ThirdRatings object
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        // Initialize the MovieDatabase
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        // Create a GenreFilter for the specified genre
        Filter genreFilter = new GenreFilter(genre);

        // Get the list of average ratings with the genre filter applied
        ArrayList<Rating> avgRatingsByGenre = thirdRatings.getAverageRatingsByFilter(minimalRaters, genreFilter);
        System.out.println("Number of movies found: " + avgRatingsByGenre.size());

        // Sort the ratings by their values
        Collections.sort(avgRatingsByGenre);

        // Print each movie's rating, title, and genres
        for (Rating rating : avgRatingsByGenre) {
            String movieID = rating.getItem();
            String title = MovieDatabase.getTitle(movieID);
            String genres = MovieDatabase.getGenres(movieID);
            double movieRating = rating.getValue();

            System.out.printf("%.2f %s\n    %s\n", movieRating, title, genres);
        }
    }
    
    public void printAverageRatingsByMinutes(int minimalRaters, int minMinutes, int maxMinutes) {
        // Create ThirdRatings object
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("-----------------------------------------------");

        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        // Initialize the MovieDatabase
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        // Create a MinutesFilter for the specified running time range
        Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);

        // Get the list of average ratings with the minutes filter applied
        ArrayList<Rating> avgRatingsByMinutes = thirdRatings.getAverageRatingsByFilter(minimalRaters, minutesFilter);
        System.out.println("Number of movies found: " + avgRatingsByMinutes.size());

        System.out.println("-----------------------------------------------");
        // Sort the ratings by their values
        Collections.sort(avgRatingsByMinutes);

        // Print each movie's rating, title, and running time
        for (Rating rating : avgRatingsByMinutes) {
            String movieID = rating.getItem();
            String title = MovieDatabase.getTitle(movieID);
            int runningTime = MovieDatabase.getMinutes(movieID);
            double movieRating = rating.getValue();

            System.out.printf("%.2f Time: %d %s\n", movieRating, runningTime, title);
        }
    }
    
    public void printAverageRatingsByDirectors(int minimalRaters, String directors) {
    
        // Create ThirdRatings object
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        // Initialize the MovieDatabase
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        // Create a DirectorsFilter for the specified directors
        Filter directorsFilter = new DirectorsFilter(directors);

        // Get the list of average ratings with the directors filter applied
        ArrayList<Rating> avgRatingsByDirectors = thirdRatings.getAverageRatingsByFilter(minimalRaters, directorsFilter);
        System.out.println("Number of movies found: " + avgRatingsByDirectors.size());

        // Sort the ratings by their values
        Collections.sort(avgRatingsByDirectors);

        // Print each movie's rating, title, and directors
        for (Rating rating : avgRatingsByDirectors) {
            String movieID = rating.getItem();
            String title = MovieDatabase.getTitle(movieID);
            double movieRating = rating.getValue();
            String movieDirectors = MovieDatabase.getDirector(movieID); // Get the directors of the movie

            System.out.printf("%.2f %s\n", movieRating, title);
            System.out.println("\t" + movieDirectors); // Print the directors on a new line
        }
    }
    
     public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        // Create ThirdRatings object
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        // Initialize the MovieDatabase
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        // Create filters for the specified year and genre
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        GenreFilter genreFilter = new GenreFilter(genre);
        
        // Create an AllFilters object and add the year and genre filters
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);

        // Get the list of average ratings with the combined filters applied
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("Number of movies matched: " + avgRatings.size());

        // Sort the ratings by their values
        Collections.sort(avgRatings);

        // Print each movie's rating, year, title, and genres
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            String title = MovieDatabase.getTitle(movieID);
            double movieRating = rating.getValue();
            String movieGenres = MovieDatabase.getGenres(movieID); // Assuming getGenres method exists

            System.out.printf("%.2f %d %s\n", movieRating, MovieDatabase.getYear(movieID), title);
            System.out.println("\t" + movieGenres); // Print the genres on a new line
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(int minimalRaters, int minMinutes, int maxMinutes, String directors) {
        // Create a ThirdRatings object
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings.csv");
        System.out.println("Number of raters: " + thirdRatings.getRaterSize());

        // Initialize the MovieDatabase
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Number of movies in the database: " + MovieDatabase.size());

        // Create filters for specified directors and minutes
        MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        
        // Create an AllFilters object and add the minutes and directors filters
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorsFilter);

        // Get the list of average ratings with the combined filters applied
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("Number of movies matched: " + avgRatings.size());

        // Sort the ratings by their values
        Collections.sort(avgRatings);

        // Print each movie's rating, time length, title, and directors
        for (Rating rating : avgRatings) {
            String movieID = rating.getItem();
            String title = MovieDatabase.getTitle(movieID);
            double movieRating = rating.getValue();
            int movieMinutes = MovieDatabase.getMinutes(movieID);
            String movieDirectors = MovieDatabase.getDirector(movieID); // Assuming getDirector method exists

            System.out.printf("%.2f Time: %d %s\n", movieRating, movieMinutes, title);
            System.out.println("\t" + movieDirectors); // Print the directors on a new line
        }
    }
    
    public void test() {
        //printAverageRatingsByDirectors(4, "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        //printAverageRatingsByYearAfterAndGenre(8, 1990, "Drama");
        printAverageRatingsByDirectorsAndMinutes(3, 90, 180, "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        //printAverageRatings(35);
        //printAverageRatingsByYear(20,2000);
        //printAverageRatingsByGenre(20,"Comedy");
        //printAverageRatingsByMinutes(5,105,135);
    }

}
