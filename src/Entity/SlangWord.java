package Entity;

import Service.SlangDictionary;
import UI.MenuHandler;
import Util.FileHandler;

import javax.swing.*;

import static Util.Constant.dataPath;
import static Util.Constant.historyPath;

public class SlangWord {
    public static void init() {

        SlangDictionary dictionary = new SlangDictionary(FileHandler.loadFromFile(dataPath), FileHandler.loadHistory(historyPath));

        SwingUtilities.invokeLater(() -> new MenuHandler(dictionary).setVisible(true));
    }
}
