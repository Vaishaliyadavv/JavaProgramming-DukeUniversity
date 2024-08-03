
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
        la.readFile("weblog2_log");
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
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Unique IP visits on Sep 24: " + uniqueIPs.size());
        for (String ip : uniqueIPs) {
            System.out.println(ip);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPsInRange(400,499);
        System.out.println("Number of unique IPs in range 400,499: " + uniqueIPs);
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
    
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");

        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("Counts: " + counts);
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");

        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Most visits by an IP: " + maxVisits);
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");

        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> mostVisitsIPs = la.iPsMostVisits(counts);
        System.out.println("IPs with most visits: " + mostVisitsIPs);
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");

        HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
        System.out.println("IPs for days: " + daysIPs);
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");

        HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
        String dayWithMostVisits = la.dayWithMostIPVisits(daysIPs);
        System.out.println("Day with most IP visits: " + dayWithMostVisits);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");

        HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
        ArrayList<String> mostVisitsOnMar17 = la.iPsWithMostVisitsOnDay(daysIPs, "Sep 30");
        System.out.println("IPs with most visits on Sep 30: " + mostVisitsOnMar17);
    }
}
