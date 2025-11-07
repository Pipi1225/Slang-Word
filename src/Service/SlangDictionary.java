package Service;

import java.util.*;

import static Util.Utility.containKeyword;

public class SlangDictionary {
    private final Map<String, List<String>> dictionary;
    private final List<String> history = new ArrayList<>();

    public SlangDictionary(Map<String, List<String>> dictionary) {
        this.dictionary = dictionary;
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
