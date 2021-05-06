package Week3_Assignments;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

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
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("src/Week3_Assignments/short-test_log");
        log.printAll();
        System.out.println("Unique IP = "+log.countUniqueIPs());
        System.out.println("Having status code > 200 : ");
        log.printAllHigherThanNum(200);
        ArrayList<String> ips = log.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Unique ip addresses on sep 30:");
        for(String ip : ips)
            System.out.println(ip);
        System.out.println("Status between low and high = "+log.countUniqueIPsInRange(200,299));
        HashMap<String,Integer> countsPerIP = log.countVisitsPerIP();
        for(String ip : countsPerIP.keySet())
            System.out.println(ip+" visited "+countsPerIP.get(ip)+" times");
        System.out.println("most visits = "+log.mostNumberVisitsByIP(countsPerIP));
        ArrayList<String> mostVisitsIps = log.iPsMostVisits(countsPerIP);
        System.out.println("Most Visited IPs : ");
        for(String ip : mostVisitsIps)
            System.out.println(ip);
        HashMap<String,ArrayList<String>> ipDays = log.iPsForDays();
        for(String date : ipDays.keySet()){
            System.out.println("Day "+date+" = ");
            for(String ip: ipDays.get(date))
                System.out.print(ip+" ");
        }
        System.out.println("Most Ip visits on "+log.dayWithMostIPVisits(ipDays));
        ArrayList<String> mostIps = log.iPsWithMostVisitsOnDay(ipDays,"Sep 30");
        System.out.println("Most visited Ips on Sep 30 : ");
        for(String ip: mostIps)
            System.out.print(ip+" ");
    }
    public static void main(String[] args) {
        Tester test = new Tester();
        test.testLogAnalyzer();

    }
}
