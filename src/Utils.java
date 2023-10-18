import java.util.*;

public class Utils {

    public static final String TRAINING_FILE_PATH = "D:\\Mobilité\\UQO\\IA distribuée\\Devoir1\\IADistribueeDevoir1\\src\\training2.txt";

    public static final String MOVIES_DATA_FILE_PATH = "D:\\Mobilité\\UQO\\IA distribuée\\Devoir1\\IADistribueeDevoir1\\src\\movies.csv";

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
