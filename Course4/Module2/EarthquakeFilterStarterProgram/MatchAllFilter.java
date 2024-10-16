
/**
 * Write a description of class MatchAllFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        StringBuilder names = new StringBuilder();
        for (Filter f : filters) {
            names.append(f.getName()).append(" ");
        }
        return names.toString().trim();  // Return all filter names
    }
}
