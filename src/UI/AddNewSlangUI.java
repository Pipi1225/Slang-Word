package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import static Util.Utility.filterEmpty;

public class AddNewSlangUI extends AddEditSlangUI {
    private final JButton overwriteButton = new JButton("Overwrite");
    private final JButton duplicateButton = new JButton("Duplicate");
    int flag = 0;
    // -1 - overwrite
    // 0  - current slang word unique
    // 1  - duplicate (add more meaning)

    public AddNewSlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        listMeaning = new ArrayList<>();

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Enter slang word:");

        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(label);
        topPanel.add(inputField);

        meaningsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        meaningsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JScrollPane scrollPane = new JScrollPane(meaningsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        JButton resetButton = new JButton("Reset");
        JButton confirmButton = new JButton("Confirm");

        overwriteButton.setEnabled(false);
        duplicateButton.setEnabled(false);

        actionPanel.add(overwriteButton);
        actionPanel.add(duplicateButton);
        actionPanel.add(Box.createHorizontalStrut(80));
        actionPanel.add(resetButton);
        actionPanel.add(confirmButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        listMeaning.add("");
        refreshPanel();

        addNewMeaning.addActionListener(e -> {
            listMeaning.add("");
            refreshPanel();
        });

        overwriteButton.addActionListener(e -> {
            overwriteButton.setEnabled(false);
            duplicateButton.setEnabled(true);
            flag = -1;
        });

        duplicateButton.addActionListener(e -> {
            overwriteButton.setEnabled(true);
            duplicateButton.setEnabled(false);
            flag = 1;
        });

        resetButton.addActionListener(e -> {
            listMeaning.add("");
            resetList();
        });

        confirmButton.addActionListener(e -> {
            String slang = inputField.getText().trim();
            if (slang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a slang word!");
                return;
            }

            List<String> definitions = filterEmpty(listMeaning);
            if (definitions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a meaning for the slang word!");
                return;
            }

            if (dictionary.containsSlang(slang) && flag == 0) {
                JOptionPane.showMessageDialog(this, "This slang word already exists. Automatically switching to add-meaning mode (Duplicate).");
                overwriteButton.setEnabled(true);
                flag = 1;
                return;
            }

            if (flag == -1) {
                dictionary.overwriteSlang(slang, definitions);
                JOptionPane.showMessageDialog(this, String.format("Successfully overwrote the meaning of '%s'!", slang));
            } else if (flag == 0 || flag == 1) {
                dictionary.updateSlang(slang, definitions, flag);
                JOptionPane.showMessageDialog(this, String.format("Successfully added meaning(s) for '%s'!", slang));
            }

            flag = 0;
            overwriteButton.setEnabled(false);
            duplicateButton.setEnabled(false);
            resetForm();
        });
    }
}