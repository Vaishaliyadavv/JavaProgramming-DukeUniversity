
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            smallestSoFar = getSmallestOfTwo(currentRow , smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        String coldestTempFile = null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            
            if(coldestSoFar == null || 
                getSmallestOfTwo(currentRow , coldestSoFar) == currentRow){
                
                coldestSoFar = currentRow;
                coldestTempFile = f.getPath();
            }
        }
        return coldestTempFile;
    }
    public void testFileWithColdestTemperature(){
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileName);
        FileResource fr = new FileResource(fileName);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + 
                            coldest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        for(CSVRecord rec : fr.getCSVParser()){
            System.out.println(rec.get("DateUTC") + ": " + rec.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar =null;
        for(CSVRecord currentRow : parser){
            lowestSoFar = getLowestHumidityOfTwo(currentRow , lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") +
                           " at " + lowestHumidity.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestHumidityOfTwo(currentRow , lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") +
         " at " + lowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double currentTemp ;
        int tempCount = 0;
        double totalTemp = 0.0;
        for(CSVRecord currentRow : parser){
            currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(currentTemp != -9999){
                tempCount++;
                totalTemp = totalTemp + currentTemp;
            }
        }
        double averageTemp = (double)(totalTemp / tempCount);
        return averageTemp;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser , int value){
        double totalTemp = 0.0;
        double currentTemp;
        int tempCount = 0;
        for(CSVRecord currentRow : parser){
            Double humidity = Double.parseDouble(currentRow.get("Humidity"));
            if(humidity >= value){
                tempCount++;
                currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                totalTemp = totalTemp + currentTemp;
            }
        }
        if(tempCount == 0){
            return Double.NaN;
        }
        return (double)(totalTemp / tempCount);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(averageTemp)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + averageTemp);
        }
    }
    
    public CSVRecord getLowestHumidityOfTwo(CSVRecord currentRow , CSVRecord lowestSoFar){
        if(lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else {
                String humidityStr = currentRow.get("Humidity");
                if(!humidityStr.equals("N/A")){
                    double currentHum = Double.parseDouble(humidityStr);
                    double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                    if(currentHum < lowestHum){
                        lowestSoFar = currentRow;
                    }
                }
            }
        return lowestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow , CSVRecord smallestSoFar){
        if(smallestSoFar == null){
                smallestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if(currentTemp != -9999 && currentTemp < smallestTemp){
                    smallestSoFar = currentRow;
                }
            }
        return smallestSoFar;
    }
}
