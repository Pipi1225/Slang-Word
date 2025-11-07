package UI;

import javax.swing.*;
import Service.SlangDictionary;

import java.awt.*;

public abstract class BaseFeatureUI extends JPanel {
    protected SlangDictionary dictionary;
    protected JButton backButton;

    public BaseFeatureUI(SlangDictionary dictionary, Runnable onBack) {
        this.dictionary = dictionary;
        setLayout(new BorderLayout());

        backButton = new JButton("Back");
        backButton.addActionListener(e -> onBack.run());
    }
}
