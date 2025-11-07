package Util;

public class Utility {
    public static boolean containKeyword(String keyword, String x) {
        String lowX = x.toLowerCase();
        String lowKeyword = keyword.toLowerCase();

        return lowX.contains(lowKeyword);
    }
}