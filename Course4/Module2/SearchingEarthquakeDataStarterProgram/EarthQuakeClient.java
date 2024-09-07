import java.util.*;


public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double dist = from.distanceTo(qe.getLocation());
            double distMaxMeters = distMax*1000;
            if(dist < distMaxMeters){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for(QuakeEntry qe : list){
            if(qe.getMagnitude() > 5.0){
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeQuakes = this.filterByDistanceFrom(list,1000,city);
        for(QuakeEntry qe : closeQuakes){
            Location to = qe.getLocation();
            double distInKm = city.distanceTo(to)/1000.0;
            System.out.println(distInKm + " km " + qe.getInfo());
        }
        
        System.out.println("Found " + closeQuakes.size() + " quakes that match the criteria.");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,double maxDepth){
        ArrayList<QuakeEntry> depthsInRange = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe  : quakeData){
            double depth = qe.getDepth();
            if(depth > minDepth && depth < maxDepth){
                depthsInRange.add(qe);
            }
        }
        return depthsInRange;
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for " + list.size()+ " quakes");
        ArrayList<QuakeEntry> quakesOfDepth = filterByDepth(list,-10000.0, -5000.0);
        
        for (QuakeEntry qe : quakesOfDepth) {
            System.out.println(qe);
        }
        System.out.println("Found " + quakesOfDepth.size() + " quakes with depth between -10000.0 and -5000.0");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where , String phrase){
        ArrayList<QuakeEntry> titlesWithPhrase = new ArrayList<QuakeEntry>();
        phrase = phrase.toLowerCase();
        for(QuakeEntry qe  : quakeData){
            String title = qe.getInfo().toLowerCase();
            if(where.equals("start") && title.startsWith(phrase)){
                titlesWithPhrase.add(qe);
            }else if(where.equals("end") && title.endsWith(phrase)){
                titlesWithPhrase.add(qe);
            }else if(where.equals("any") && title.contains(phrase)){
                titlesWithPhrase.add(qe);
            }
        }
        return titlesWithPhrase;
    }
    
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for " + list.size()+ " quakes");
        ArrayList<QuakeEntry> quakesByPhrase = filterByPhrase(list,"end","california");
        
        for (QuakeEntry qe : quakesByPhrase) {
            System.out.println(qe);
        }
        System.out.println("Found " + quakesByPhrase.size() + " quakes within the criteria.");
    }
}
