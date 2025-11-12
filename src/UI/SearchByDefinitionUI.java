package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchByDefinitionUI extends BaseFeatureUI {
    public SearchByDefinitionUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Enter definition: ");
        JTextField inputField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(label);
        topPanel.add(inputField);
        topPanel.add(searchButton);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        ActionListener searchAction = e -> {
            String keyword = inputField.getText().trim();

            if (keyword.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please enter a definition!");
            }

            List<String> slangs = dictionary.searchByKeyword(keyword);
            resultArea.setText("");

            if (slangs.isEmpty()) {
                resultArea.append("No slang's found fitting the definition: " + keyword);
            } else {
                for (String slang : slangs) {
                    resultArea.append("- " + slang.trim() + "\n");
                }
            }
        };

        searchButton.addActionListener(searchAction);
        inputField.addActionListener(searchAction);
    }
}
