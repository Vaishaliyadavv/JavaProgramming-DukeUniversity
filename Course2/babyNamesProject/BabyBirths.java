/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlNames = 0;
        int boyNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                boyNames++;
                totalBoys += numBorn;
            }
            else {
                girlNames++;
                totalGirls += numBorn;
            }
        }
        System.out.println("Number of boys names : " + boyNames);
        System.out.println("Number of girls names : " + girlNames);
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }
    
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year , String name , String gender){
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        int rank = 0;
        boolean found = false;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            return -1;
        }
        return rank;
    }
    
    public void testGetRank(){
        int rank = getRank(1971, "Frank" , "M");
        System.out.println("Rank for name Frank is : " + rank);
    }
    
    public String getName(int year , int rank , String gender){
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        int currentRank = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                currentRank++;
                if(currentRank == rank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name , int year , int newYear ,
                                                    String gender){
        int rank = getRank(year , name , gender);
        if (rank == -1) {
        System.out.println(name + " does not have a rank in " + year);
        return;
        }
        String newName = getName(newYear , rank , gender);
        System.out.println(name + " born in " + year + " would be " 
                + newName + " if they were born in " + newYear + ".");       
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testGetName(){
        String name = getName(1982, 450 , "M");
        System.out.println("Name for rank 450 male " + name);
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = Integer.MAX_VALUE;
        int yearOfHighestRank = -1;
        for(File f : dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int rank = getRank(year, name, gender);
            
            if(rank != -1 && rank < highestRank){
                highestRank = rank;
                yearOfHighestRank = year;
            }
        }
        return yearOfHighestRank;
    }
    
    public void testYearOfHighestRank() {
        int year = yearOfHighestRank("Mich", "M");
        System.out.println("Year of highest rank for Mich : " + year);
    }
    
    public double getAverageRank(String name , String gender){
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0;
        int count = 0;
        for(File f : dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int rank = getRank(year, name, gender);
            
            if(rank!= -1){
                totalRank += rank;
                count++;
            }
        }
        if(count == 0){
            return -1.0;
        }
        return totalRank/count;
    }
    
    public void testGetAverageRank() {
        double averageRank = getAverageRank("Robert", "M");
        System.out.println("Average rank: " + averageRank);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        int totalBirths = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    break;
                }
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int totalBirths = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Total births ranked higher: " + totalBirths);
    }
}
