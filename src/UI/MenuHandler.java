package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

public class MenuHandler extends JFrame {
    private final SlangDictionary dictionary;

    public MenuHandler(SlangDictionary dictionary) {
        this.dictionary = dictionary;
        setTitle("Slang Dictionary - Search by Slang");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(new SearchBySlangUI(dictionary), BorderLayout.CENTER);
    }
}
