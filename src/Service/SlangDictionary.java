package Service;

import java.util.*;

import static Util.Utility.containKeyword;

public class SlangDictionary {
    private final Map<String, List<String>> dictionary;
    private final List<String> history = new ArrayList<>();

    public SlangDictionary(Map<String, List<String>> dictionary) {
        this.dictionary = dictionary;
    }

    public void overwriteSlang(String key, List<String> value) {
        dictionary.put(key, new ArrayList<>(value));
    }

    public void updateSlang(String key, List<String> value, int addMore) {
        List<String> exist = dictionary.computeIfAbsent(key, k -> new ArrayList<>());

        if (addMore == 1) {
            HashSet<String> set = new HashSet<>();
            for (String s : exist) {
                set.add(s.toLowerCase());
            }

            for (String val : value) {
                if (!set.contains(val.toLowerCase())) {
                    exist.add(val);
                    set.add(val.toLowerCase());
                }
            }
        }
        else {
            exist.addAll(value);
        }
    }

    public List<String> searchBySlang(String slang) {
        List<String> meanings = dictionary.get(slang);
        addHistory(slang);

        return meanings;
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

    public boolean containsSlang(String slang) {
        return dictionary.containsKey(slang);
    }

    private void addHistory(String query) {
        history.add(query);
        if (history.size() > 30) {
            history.removeFirst();
        }
    }

    public void clearHistory() {
        history.clear();
    }

    public List<String> getHistory() {
        return history;
    }
}
