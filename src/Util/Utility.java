package Util;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static boolean containKeyword(String keyword, String value) {
        String lowValue = value.toLowerCase();
        String lowKeyword = keyword.toLowerCase();

        return lowValue.contains(lowKeyword);
    }

    public static List<String> filterEmpty(List<String> value) {
        List<String> definitions = new ArrayList<>();
        for (String val : value) {
            val = val.trim();
            if (!val.isEmpty()) {
                definitions.add(val);
            }
        }

        return definitions;
    }
}