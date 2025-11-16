package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Util.QuizUtil.getSlangOfTheDay;

public class OnTodaySlangUI extends BaseFeatureUI {
    public OnTodaySlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(backButton);

        JPanel slangPanel = new JPanel();
        slangPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Today slang word is");
        label.setFont(label.getFont().deriveFont(14f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        String slang = getSlangOfTheDay(dictionary);
        JLabel slangLabel = new JLabel(slang);
        slangLabel.setFont(label.getFont().deriveFont(24f));
        slangLabel.setForeground(new Color(52, 52, 106));
        slangLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setText("");
        JScrollPane scrollPane = new JScrollPane(resultArea);
        List<String> meanings = dictionary.searchBySlang(slang, false);
        for (String meaning : meanings) {
            resultArea.append("- " + meaning.trim() + "\n");
        }

        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(slangLabel);
        slangPanel.add(contentPanel, BorderLayout.NORTH);
        slangPanel.add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
        add(slangPanel, BorderLayout.CENTER);
    }
}
