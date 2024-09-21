import java.util.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);         
        System.out.println("read data for " + list.size() + " quakes");
    
        Location denver = new Location(39.7392, -104.9903);
        Filter distanceFilter = new DistanceFilter( denver,1000000.0); // 1,000 km = 1,000,000 meters
        ArrayList<QuakeEntry> distanceFiltered = filter(list, distanceFilter);
    
        Filter phraseFilter = new PhraseFilter("end", "a"); // Ending with 'a'
        ArrayList<QuakeEntry> finalFiltered = filter(distanceFiltered, phraseFilter);
    
        for (QuakeEntry qe : finalFiltered) { 
            System.out.println(qe);
        }
    
        System.out.println("Total " + finalFiltered.size() + " earthquakes");

    }

        public void quakesWithFilter2() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);         
        System.out.println("read data for " + list.size() + " quakes");
    
        Filter magFilter = new MagnitudeFilter(3.5, 4.5);
        ArrayList<QuakeEntry> magFiltered = filter(list, magFilter);
    
        Filter depthFilter = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> finalFiltered = filter(magFiltered, depthFilter);
    
        for (QuakeEntry qe : finalFiltered) {
            System.out.println(qe);
        }
    
        System.out.println("Total " + finalFiltered.size() + " earthquakes");
    }


    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    
    
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

        public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);         
        System.out.println("read data for " + list.size() + " quakes");
    
        MatchAllFilter maf = new MatchAllFilter();
        
        Filter magFilter = new MagnitudeFilter(1.0, 4.0);
        maf.addFilter(magFilter);
    
        Filter depthFilter = new DepthFilter(-180000.0, -30000.0);
        maf.addFilter(depthFilter);
    
        Filter phraseFilter = new PhraseFilter("any", "o"); // Title containing 'o'
        maf.addFilter(phraseFilter);
    
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        
        System.out.println("Total " + filteredList.size() + " earthquakes");
    }

    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("Read data for " + list.size() + " quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
    
        Filter magFilter = new MagnitudeFilter(0.0, 5.0);
        maf.addFilter(magFilter);
    
        Location billund = new Location(55.7308, 9.1153);
        Filter distanceFilter = new DistanceFilter(billund,3000000.0); // 3,000 km = 3,000,000 meters
        maf.addFilter(distanceFilter);
    
        Filter phraseFilter = new PhraseFilter("any", "e"); // Title containing 'e'
        maf.addFilter(phraseFilter);
    
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
    
        System.out.println("Total " + filteredList.size() + " earthquakes");
    }


}
