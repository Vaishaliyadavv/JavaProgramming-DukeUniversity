
/**
 * Write a description of class LargestQuakes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes
{
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom"; // The file with 1518 quakes
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("Total number of earthquakes: " + list.size());
        
        // Question 6: Get the top 20 largest earthquakes
        int topLargestForQuestion6 = 20;
        ArrayList<QuakeEntry> largestTwentyQuakes = getLargest(list, topLargestForQuestion6);
        
        System.out.println("\nTop " + topLargestForQuestion6 + " largest earthquakes:");
        for (QuakeEntry qe : largestTwentyQuakes) {
            System.out.println(qe);
        }
        
        // Get the magnitude of the 20th largest earthquake for question 6
        QuakeEntry twentiethLargestQuake = largestTwentyQuakes.get(topLargestForQuestion6 - 1); // 0-based index
        System.out.println("\nMagnitude of the 20th largest earthquake: " + twentiethLargestQuake.getMagnitude());
        
        // Question 7: Get the top 50 largest earthquakes
        int topLargestForQuestion7 = 50;
        ArrayList<QuakeEntry> largestFiftyQuakes = getLargest(list, topLargestForQuestion7);
        
        System.out.println("\nTop " + topLargestForQuestion7 + " largest earthquakes:");
        for (QuakeEntry qe : largestFiftyQuakes) {
            System.out.println(qe);
        }
        
        // Get the country/location of the 50th largest earthquake for question 7
        QuakeEntry fiftiethLargestQuake = largestFiftyQuakes.get(topLargestForQuestion7 - 1); // 0-based index
        String locationOfFiftiethLargest = fiftiethLargestQuake.getInfo();
        System.out.println("\nThe location of the 50th largest earthquake: " + locationOfFiftiethLargest);
    }

    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIndex = 0;
        for(int i = 0; i < data.size();i++){
            if(data.get(i).getMagnitude() > data.get(maxIndex).getMagnitude()){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        ArrayList<QuakeEntry> largestQuakes = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < howMany && copy.size() > 0; i++) {
            int largestIndex = indexOfLargest(copy);
            largestQuakes.add(copy.get(largestIndex));
            copy.remove(largestIndex); 
        }
        return largestQuakes;
    }
}
