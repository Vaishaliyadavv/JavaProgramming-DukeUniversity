
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
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le : records){
            String ipAdr = le.getIpAddress();
             if(!counts.containsKey(ipAdr)){
                 counts.put(ipAdr,1);
             }
             else { 
                 counts.put(ipAdr, counts.get(ipAdr) + 1);
            }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         
         for(int count : counts.values()){
             if(count > max){
                 max = count;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String , Integer> counts){
         ArrayList<String> mostVisitsIPs = new ArrayList<>();
         int maxVisits = mostNumberVisitsByIP(counts);
         for(String ip : counts.keySet()){
             if(counts.get(ip) == maxVisits){
                 mostVisitsIPs.add(ip);
             }
         }
         return mostVisitsIPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipForDays = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records){
            String day = le.getAccessTime().toString().substring(4,10);
            String ip = le.getIpAddress();
            if(!ipForDays.containsKey(day)){
                 ipForDays.put(day, new ArrayList<String>());
            }
             
            ipForDays.get(day).add(ip);
            
        }
        return ipForDays;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIPs){
         String dayWithMostVisits = "";
         int maxVisits = 0;
         for(String day :daysIPs.keySet()){
             int visits = daysIPs.get(day).size();
             if (visits > maxVisits) {
                maxVisits = visits;
                dayWithMostVisits = day;
            }
         }
         return dayWithMostVisits;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIPs,String day){
         ArrayList<String> ipsOnDay = daysIPs.get(day);
         HashMap<String , Integer> counts = new HashMap<>();
         for(String ip : ipsOnDay){
             counts.put(ip, counts.getOrDefault(ip, 0 ) + 1);
         }
         return iPsMostVisits(counts);
     }
}
