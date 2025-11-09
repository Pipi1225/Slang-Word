package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

import java.util.List;

import static Util.Utility.filterEmpty;

public class EditSlangUI extends AddEditSlangUI {
    public EditSlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Nhập slang word:");

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

        inputField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                listMeaning = dictionary.searchBySlang(inputField.getText().trim(), false);
                refreshPanel();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                listMeaning = dictionary.searchBySlang(inputField.getText().trim(), false);
                refreshPanel();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                listMeaning = dictionary.searchBySlang(inputField.getText().trim(), false);
                refreshPanel();
            }
        });

        addNewMeaning.addActionListener(e -> {
            listMeaning.add("");
            refreshPanel();
        });

        resetButton.addActionListener(e -> resetForm());

        confirmButton.addActionListener(e -> {
            String slang = inputField.getText().trim();
            if (slang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập slang word!");
                return;
            }

            List<String> definitions = filterEmpty(listMeaning);
            if (definitions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập nghĩa cho slang word!");
                return;
            }

            dictionary.overwriteSlang(slang, definitions);
            JOptionPane.showMessageDialog(this, String.format("Cập nhập nghĩa của '%s' thành công!", slang));
        });
    }
}