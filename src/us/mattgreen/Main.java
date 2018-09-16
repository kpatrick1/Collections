package us.mattgreen;

import java.util.*;

/**
 * Application to output the word count for a book
 * @author kpatr
 * @version 1.0.0
 */
public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<String, Integer>();
    private static Integer s = 0;

    /**
     * starting point of the program
     * @param args initial arguments
     */
    public static void main(String[] args) {
        String line;
        String[] words;
        Object wordFound;
     //   String[] fields;

        while ((line = indata.fileReadLine()) != null) {
            line=line.replace(",","").replace(".","")
                    .replace(";","").replace(":","")
                    .replace("'","").replace("\"","")
                    .replace("-","").replace("!","")
                    .replace("#","").replace("(","")
                    .replace(")","").replace("?","")
                    .replace("_"," ").replace("?","")
                    .toLowerCase().trim();
            words = line.split(" ");
            for (String s:words) {
                wordFound = map.get(s);
                if (wordFound == null) {
                    map.put(s, new Integer(1));
                }
                else {
                    map.put(s, map.get(s) + 1);
                }

            }


            //for (Map.Entry<String, Integer> entry : map.entrySet()) {
             //   System.out.println(entry.getKey() + " " + entry.getValue());
            //}
        }

        //Map<String, Integer> treeMap = new TreeMap<String, Integer>(map);
        //printMap(treeMap);

        Map<String, Integer> sortedMap = sortByValue(map);
        printMap(sortedMap);

    }

    /**
     *  This will sort the map that was passed to it
     * @param unsortMap Pass in the unsorted map
     * @return sorted map by the value
     */
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }

    /**
     * This outputs the values of the map to the screen
     * @param map map object passed to the method
     * @param <K> Key of map object
     * @param <V> Value of map object
     */
    public static <K, V> void printMap(Map<K, V> map) {
        Integer i = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (i<100) {
                System.out.println((i+1) + ") " + entry.getKey() + " : " + entry.getValue());
            }
            i++;
            if (entry.getValue().equals(1)) {
                s++;
            }


        }

        System.out.println("Words only once: " + s);
    }
}