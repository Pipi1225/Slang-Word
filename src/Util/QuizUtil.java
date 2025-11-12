package Util;

import java.time.LocalDate;
import java.util.*;

public class QuizUtil {
    public static String getSlangOfTheDay(Map<String, List<String>> list) {
        if (list.isEmpty()) {
            return "";
        }

        LocalDate today = LocalDate.now();
        int seed = today.getYear() * 10000 + today.getMonthValue() * 100 + today.getDayOfMonth();

        return getRandomSlang(list, seed);
    }

    public static String getRandomSlang(Map<String, List<String>> list, int seed) {
        List<String> keys = new ArrayList<>(list.keySet());

        Random random;
        if (seed > 0) {
            random = new Random(seed);
        } else {
            random = new Random();
        }
        int index = random.nextInt(keys.size());

        return keys.get(index);
    }

    public static List<String> getSlangQuiz(Map<String, List<String>> list, String slang) {
        List<String> quiz = new ArrayList<>();
        if (list.isEmpty()) {
            return quiz;
        }

        Random random = new Random();
        String definition;
        List<String> definitions = list.get(slang);
        if (definitions != null && !definitions.isEmpty()) {
            definition = definitions.get(random.nextInt(definitions.size()));
            quiz.add(definition);
        }

        Set<String> usedDefs = new HashSet<>();

        while (quiz.size() < 4) {
            String key = quiz.get(random.nextInt(quiz.size()));
            List<String> defs = list.get(key);
            if (defs == null || defs.isEmpty()) continue;

            String def = defs.get(random.nextInt(defs.size()));
            if (usedDefs.contains(def)) continue;

            quiz.add(def);
            usedDefs.add(def);
        }

        Collections.shuffle(quiz);

        return quiz;
    }

    public static List<String> getDefinitionQuiz(Map<String, List<String>> list, String definition) {
        List<String> quiz = new ArrayList<>();
        if (list.isEmpty()) {
            return quiz;
        }

        Random random = new Random();

        String correctSlang = null;
        for (Map.Entry<String, List<String>> entry : list.entrySet()) {
            if (entry.getValue().contains(definition)) {
                correctSlang = entry.getKey();
                break;
            }
        }
        if (correctSlang == null) {
            return quiz;
        }

        quiz.add(correctSlang);

        Set<String> usedSlangs = new HashSet<>();
        usedSlangs.add(correctSlang);

        List<String> keys = new ArrayList<>(list.keySet());
        while (quiz.size() < 4 && usedSlangs.size() < keys.size()) {
            String slang = keys.get(random.nextInt(keys.size()));
            if (usedSlangs.contains(slang)) continue;

            quiz.add(slang);
            usedSlangs.add(slang);
        }

        Collections.shuffle(quiz);

        return quiz;
    }
}
