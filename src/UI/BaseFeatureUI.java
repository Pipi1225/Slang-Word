package UI;

import javax.swing.*;
import Service.SlangDictionary;

public abstract class BaseFeatureUI extends JPanel {
    protected SlangDictionary dictionary;

    public BaseFeatureUI(SlangDictionary dictionary) {
        this.dictionary = dictionary;
    }
}
