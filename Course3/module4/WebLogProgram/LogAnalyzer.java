
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for(LogEntry le : records){
             String ipAdr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAdr)){
                 uniqueIPs.add(ipAdr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status > num){
                 System.out.println(status);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        
        for(LogEntry le : records){
            Date accessTime = le.getAccessTime();
            //System.out.println(accessTime);
            String date = accessTime.toString();
            if(date.contains(someday)){
                String ipAdr = le.getIpAddress();
                if(!uniqueIPs.contains(ipAdr)){
                 uniqueIPs.add(ipAdr);
                }
            }
        }
        return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low , int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status >= low && status <= high){
                String ipAddr = le.getIpAddress();
                 if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
             }
         }
         return uniqueIPs.size();
     }
}
