
/**
 * Write a description of class LargestQuakes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes
{
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("Total number of eathquakes: " + list.size());
        
        /*for(QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        
        //largest index
        int largestIndex = indexOfLargest(list);
        System.out.println("Index of the largest magnitude earthquake: " + largestIndex);
        System.out.println("Largest Earthquake: " + list.get(largestIndex));
        
        int howMany = 5;
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, howMany);
        
        System.out.println("Top " + howMany + " largest earthquakes:");
        for (QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }
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
