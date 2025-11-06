package Service;

import java.util.*;

public class SlangDictionary {
    private Map<String, List<String>> dictionary;

    public SlangDictionary(Map<String, List<String>> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> searchBySlang(String slang) {
        return dictionary.getOrDefault(slang, null);
    }
}
