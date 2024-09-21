
/**
 * Write a description of class TitleLastAndMagnitudeComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>
{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String[] words1 = q1.getInfo().split(" ");
        String[] words2 = q2.getInfo().split(" ");
        
        String lastWord1 = words1[words1.length -1];
        String lastWord2 = words2[words2.length -1];
        
        int lastWordCompare = lastWord1.compareTo(lastWord2);
        if(lastWordCompare !=0){
            return lastWordCompare;
        }
        return Double.compare(q1.getMagnitude(),q2.getMagnitude());
    }
}
