package Week3_Assignments;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines())
            records.add(WebLogParser.parseEntry(line));
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIps = new ArrayList<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIps.contains(ip))
                uniqueIps.add(ip);
        }
        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num)
                System.out.println(le);
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIpsDay = new ArrayList<>();
        for (LogEntry le : records) {
            Date d = le.getAccessTime();
            String date = d.toString().substring(4, 10);
            String ip = le.getIpAddress();
            if (date.equals(someday)) {
                if (!uniqueIpsDay.contains(ip))
                    uniqueIpsDay.add(ip);
            }
        }
        return uniqueIpsDay;
    }

    public int countUniqueIPsInRange(int low, int high) {
        int count = 0;
        for (LogEntry le : records) {
            int status = le.getStatusCode();
            if (status >= low && status <= high)
                count++;
        }
        return count;
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> countsPerIp = new HashMap<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (countsPerIp.containsKey(ip))
                countsPerIp.put(ip, countsPerIp.get(ip) + 1);
            else
                countsPerIp.put(ip, 1);
        }
        return countsPerIp;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> countsPerIp) {
        int max = -1;
        for (String ip : countsPerIp.keySet()) {
            if (max < countsPerIp.get(ip))
                max = countsPerIp.get(ip);
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> countsPerIp) {
        ArrayList<String> mostVisitsIps = new ArrayList<>();
        LogAnalyzer log = new LogAnalyzer();
        int max = log.mostNumberVisitsByIP(countsPerIp);
        for (String ip : countsPerIp.keySet()) {
            if (countsPerIp.get(ip) == max)
                mostVisitsIps.add(ip);
        }
        return mostVisitsIps;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipDays = new HashMap<>();
        for (LogEntry log : records) {
            String ip = log.getIpAddress();
            Date d = log.getAccessTime();
            String date = d.toString().substring(4, 10);
            if (ipDays.containsKey(date))
                ipDays.get(date).add(ip);
            else {
                ArrayList<String> ips = new ArrayList<>();
                ips.add(ip);
                ipDays.put(date, ips);
            }
        }
        return ipDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipDays) {
        int max = -1;
        String date = "";
        for (String d : ipDays.keySet()) {
            if (max < ipDays.get(d).size()) {
                max = ipDays.get(d).size();
                date = d;
            }
        }
        return date;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipDays,String date){
        ArrayList<String> maxIps = new ArrayList<>();
        HashMap<String,Integer> ipCounts = new HashMap<>();
        for(String d : ipDays.keySet()){
            if(d.equals(date)){
                for(String ip : ipDays.get(d)){
                    if(ipCounts.containsKey(ip))
                        ipCounts.put(ip,ipCounts.get(ip)+1);
                    else
                        ipCounts.put(ip,1);
                }
            }
        }
       return iPsMostVisits(ipCounts);
    }
}
