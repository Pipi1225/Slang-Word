package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowHistoryUI extends BaseFeatureUI {
    private JTextArea resultArea;

    public ShowHistoryUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Lịch sử search slang words");
        JButton clearButton = new JButton("Xóa lịch sử");

        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(label);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(clearButton);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        clearButton.addActionListener(e -> {
            dictionary.clearHistory();
            resultArea.setText("");
        });
    }

    public void reloadHistory() {
        resultArea.setText("");
        List<String> history = dictionary.getHistory();
        if (history.isEmpty()) {
            resultArea.setText("Chưa có từ nào được tìm.");
        } else {
            for (int i = history.size() - 1; i >= 0; i--) {
                resultArea.append("- " + history.get(i).trim() + "\n");
            }
        }
        resultArea.setCaretPosition(0);
    }
}
