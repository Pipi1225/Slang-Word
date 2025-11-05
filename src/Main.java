import Util.FileHandler;

import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filePath = "src/Data/slang.txt";
        Map<String, List<String>> stringListMap = FileHandler.loadFromFile(filePath);

        for (Map.Entry<String, List<String>> entry : stringListMap.entrySet()) {
            String slang = entry.getKey();
            List<String> defs = entry.getValue();

            System.out.println(slang + " (" + defs.size() + " meanings): " + defs);
        }

        System.out.println("Total slang words: " + stringListMap.size());
    }
}