import java.util.*;

public class Utils {

    public static LinkedHashMap<String, Double> sortByComparatorDouble(
            final Map<String, Double> map) {
        List<Map.Entry<String, Double>> map_arr = new LinkedList<>(
                map.entrySet());

        Collections.sort(map_arr, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> v1,
                               Map.Entry<String, Double> v2) {
                return v1.getValue().compareTo(v2.getValue());
            }
        });

        LinkedHashMap<String, Double> sortedByComparator = new LinkedHashMap<>();
        for (Map.Entry<String, Double> e : map_arr) {
            sortedByComparator.put(e.getKey(), e.getValue());
        }
        return sortedByComparator;
    }

}
