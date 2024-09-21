
/**
 * Write a description of class DistanceFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    private Location location;
    private double maxDistance;
    
    public DistanceFilter(Location loc , double maxDist){
        location = loc;
        maxDistance = maxDist;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(location) <= maxDistance;
    }
    
     public String getName() {
        return "Distance Filter";
    }
}
