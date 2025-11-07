package Service;

import java.util.*;

import static Util.Utility.containKeyword;

public class SlangDictionary {
    private Map<String, List<String>> dictionary;

    public SlangDictionary(Map<String, List<String>> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> searchBySlang(String slang) {
        return dictionary.getOrDefault(slang, null);
    }
    public List<String> searchByKeyword(String keyword) {
        List<String> result = new ArrayList<>();
        for (String key : dictionary.keySet()) {
            List<String> defs = dictionary.get(key);
            for (String def : defs) {
                if (containKeyword(keyword, def)) {
                    result.add(key);
                    break;
                }
            }
        }

        return result;
    }
}
