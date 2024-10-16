
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int min;
    private int max;

    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean satisfies(String id) {
        int runningTime = MovieDatabase.getMinutes(id);
        return (runningTime >= min && runningTime <= max);
    }
}

