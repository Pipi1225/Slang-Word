package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Util.QuizUtil.*;

public class SlangQuizUI extends BaseQuizUI {
    public SlangQuizUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(backButton);

        JPanel slangPanel = new JPanel();
        slangPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Definition of this slang is?");
        label.setFont(label.getFont().deriveFont(14f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        quizLabel.setFont(quizLabel.getFont().deriveFont(24f));
        quizLabel.setForeground(new Color(52, 52, 106));
        quizLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        refreshQuiz();
        quizArea.setMaximumSize(quizArea.getPreferredSize());

        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(quizLabel);
        slangPanel.add(contentPanel, BorderLayout.NORTH);
        slangPanel.add(quizArea, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
        add(slangPanel, BorderLayout.CENTER);
    }

    protected void refreshQuiz() {
        String slang = getRandomSlang(dictionary, -1);
        quizLabel.setText(slang);

        List<JButton> buttons = new ArrayList<>();
        List<String> definitions = getSlangQuiz(dictionary.getDictionary(), slang);
        for (int i = 0; i < definitions.size(); i++) {
            JButton button = getJButton(definitions, i);
            buttons.add(button);
        }

        quizArea.removeAll();
        Collections.shuffle(buttons);
        for (JButton button : buttons) {
            quizArea.add(button);
        }
    }
}
