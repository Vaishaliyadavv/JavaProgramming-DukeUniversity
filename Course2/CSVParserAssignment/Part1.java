
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("-----------------------------------");
        String info = countryInfo(parser , "Nauru");
        System.out.println(info);
        System.out.println("-----------------------------------");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser , "cotton" , "flowers");
        System.out.println("-----------------------------------");
        parser = fr.getCSVParser();
        int exporters = numberOfExporters(parser, "cocoa");
        System.out.println("num of exporters : " + exporters);
        System.out.println("-----------------------------------");
        parser = fr.getCSVParser();
        bigExporters(parser , "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser , String country){
        for(CSVRecord rec : parser){
            String currCountry = rec.get("Country"); 
            if(currCountry.equals(country)){
                String export = rec.get("Exports");
                String value = rec.get("Value (dollars)");
                return currCountry + ": " + export + ": " + value; 
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser , String exportItem1,String exportItem2){
        for(CSVRecord rec : parser){
            String export = rec.get("Exports");
            if(export.contains(exportItem1) && 
                    export.contains(exportItem2)){
                String country  = rec.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser , String exportItem){
        int count = 0;
        for(CSVRecord rec : parser){
            String export = rec.get("Exports");
            if(export.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser ,String amount){
        int amountLen = amount.length();
        for(CSVRecord rec : parser){
            String value = rec.get("Value (dollars)");
            int valueLen = value.length();
            if(valueLen > amountLen){
                String country = rec.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
}
