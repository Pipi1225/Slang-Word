package Service;

import Util.FileHandler;

import java.util.*;

import static Util.Utility.containKeyword;

public class SlangDictionary {
    private final Map<String, List<String>> list;
    private final List<String> history;

    public SlangDictionary(Map<String, List<String>> list, List<String> history) {
        this.list = list;
        this.history = history;
    }

    public void reloadFromFile(String path) {
        list.clear();
        list.putAll(FileHandler.loadFromFile(path));
    }

    public boolean removeSlang(String key) {
        return list.remove(key) != null;
    }

    public void overwriteSlang(String key, List<String> value) {
        list.put(key, new ArrayList<>(value));
    }

    public void updateSlang(String key, List<String> value, int addMore) {
        List<String> exist = list.computeIfAbsent(key, k -> new ArrayList<>());

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

    public List<String> searchBySlang(String slang, boolean addToHistory) {
        List<String> meanings = list.get(slang);

        if (meanings == null) {
            meanings = new ArrayList<>();
        }

        if (addToHistory) {
            addHistory(slang);
        }

        return meanings;
    }

    public List<String> searchByKeyword(String keyword) {
        List<String> result = new ArrayList<>();
        for (String key : list.keySet()) {
            List<String> defs = list.get(key);
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
        return list.containsKey(slang);
    }

    private void addHistory(String query) {
        history.add(query);
        if (history.size() > 200) {
            history.removeFirst();
        }
    }

    public void clearHistory() {
        history.clear();
    }

    public Map<String, List<String>> getDictionary() {
        return list;
    }

    public List<String> getHistory() {
        return history;
    }
}
