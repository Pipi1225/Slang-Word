package Entity;

import Service.SlangDictionary;
import UI.MenuHandler;
import Util.FileHandler;

import javax.swing.*;

public class SlangWord {
    public static void init() {
        String path = "src/Data/slang.txt";
        SlangDictionary dictionary = new SlangDictionary(FileHandler.loadFromFile(path));

        SwingUtilities.invokeLater(() -> {
            new MenuHandler(dictionary).setVisible(true);
        });
    }
}
