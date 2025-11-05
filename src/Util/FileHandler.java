package Util;

import java.io.*;
import java.util.*;

public class FileHandler {
    public static Map<String, List<String>> loadFromFile(String path) {
        Map<String, List<String>> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`", 2);
                if (parts.length == 2) {
                    String slang = parts[0].trim();
                    String[] defs = parts[1].split("\\|");

                    List<String> definitions = new ArrayList<>();
                    for (String def : defs) {
                        def = def.trim();
                        if (!def.isEmpty()) definitions.add(def);
                    }

                    map.put(slang, definitions);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return map;
    }

    public static void saveToFile(String path, Map<String, List<String>> map) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<String, List<String>> e : map.entrySet()) {
                bw.write(e.getKey() + "`" + String.join("| ", e.getValue()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
