package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchByDefinitionUI extends BaseFeatureUI {
    private JTextField inputField;
    private JTextArea resultArea;
    private JButton searchButton;

    public SearchByDefinitionUI(SlangDictionary dictionary) {
        super(dictionary);
    }

    private void initUI() {

    }
}
