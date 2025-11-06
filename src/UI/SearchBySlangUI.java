package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchBySlangUI extends BaseFeatureUI {
    public SearchBySlangUI(SlangDictionary dictionary) {
        super(dictionary);

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Nhập slang word:");
        JTextField inputField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        topPanel.add(label);
        topPanel.add(inputField);
        topPanel.add(searchButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

       ActionListener searchAction = e -> {
            String slang = inputField.getText().trim();
            if (slang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập slang word!");
                return;
            }

            List<String> meanings = dictionary.searchBySlang(slang);
            resultArea.setText(""); // clear

            if (meanings == null) {
                resultArea.append("Không tìm thấy slang: " + slang);
            } else {
                resultArea.append("Nghĩa của '" + slang + "':\n");
                for (String meaning : meanings) {
                    resultArea.append("- " + meaning.trim() + "\n");
                }
            }
       };
       searchButton.addActionListener(searchAction);
       inputField.addActionListener(searchAction);
    }
}
