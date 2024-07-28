
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAll();
    }
    
    public void testUniqueIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("Number of unique IPs: " + uniqueIPs);
    }
    
    public void testStatusHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println("Entries with status higher than 400:");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Mar 17");
        System.out.println("Unique IP visits on Mar 17: " + uniqueIPs.size());
        for (String ip : uniqueIPs) {
            System.out.println(ip);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int uniqueIPs = la.countUniqueIPsInRange(300,399);
        System.out.println("Number of unique IPs in range 300,399: " + uniqueIPs);
    }
    
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testLogEntry();
        tester.testLogAnalyzer();
        tester.testUniqueIp();
        tester.testStatusHigherThanNum();
        tester.testUniqueIPVisitsOnDay();
        tester.testCountUniqueIPsInRange();
    }
}
