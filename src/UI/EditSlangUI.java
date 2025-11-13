package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import static Util.Utility.filterEmpty;

public class EditSlangUI extends AddEditSlangUI {
    private List<String> originalList = new ArrayList<>();
    public EditSlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

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

        actionPanel.add(resetButton);
        actionPanel.add(confirmButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        refreshPanel();

        Timer debounceTimer = new Timer(250, e -> updateOriginal());
        debounceTimer.setRepeats(false);
        inputField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void restartTimer() {
                debounceTimer.restart();
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                restartTimer();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                restartTimer();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                restartTimer();
            }
        });

        addNewMeaning.addActionListener(e -> {
            listMeaning.add("");
            refreshPanel();
        });

        resetButton.addActionListener(e -> {
            listMeaning = new ArrayList<>(originalList);
            refreshPanel();
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

            dictionary.overwriteSlang(slang, definitions);
            JOptionPane.showMessageDialog(this, String.format("Successfully updated the meaning of '%s'!", slang));
            resetForm(false);
        });
    }

    private void updateOriginal() {
        listMeaning = new ArrayList<>(dictionary.searchBySlang(inputField.getText().trim(), false));
        originalList = new ArrayList<>(listMeaning);
        refreshPanel();
    }
}