package Util;

import java.io.*;
import java.util.*;

import static Util.Utility.filterEmpty;

public class FileHandler {
    public static Map<String, List<String>> loadFromFile(String path) {
        Map<String, List<String>> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`", 2);
                if (parts.length == 2) {
                    String slang = parts[0].trim();
                    String[] defs = parts[1].split("\\|");

                    List<String> definitions = filterEmpty(Arrays.asList(defs));

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
            bw.write("Slag`Meaning");
            bw.newLine();
            for (Map.Entry<String, List<String>> e : map.entrySet()) {
                bw.write(e.getKey() + "`" + String.join("| ", e.getValue()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> loadHistory(String path) {
        List<String> history = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    history.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return history;
    }

    public static void saveHistory(String path, List<String> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String i : list) {
                bw.write(i);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
