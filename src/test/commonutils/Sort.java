package commonutils;

import java.util.*;

public class Sort {

    public static void sortArray(String[] arr) {
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
    }

    public static void sortList(List<String> list) {
        list.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public static List<String> sorted(Collection<String> collection) {
        List<String> result = new ArrayList<>(collection);
        result.sort(String.CASE_INSENSITIVE_ORDER);
        return result;
    }
}