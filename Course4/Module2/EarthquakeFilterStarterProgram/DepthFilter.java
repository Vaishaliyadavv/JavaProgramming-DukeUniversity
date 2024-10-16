
/**
 * Write a description of class DepthFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    private double minDepth; 
    private double maxDepth; 
    public DepthFilter(double min,double max) { 
        minDepth = min;
        maxDepth = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth; 
    }
    
    public String getName() {
        return "Depth Filter";
    }
    
}
