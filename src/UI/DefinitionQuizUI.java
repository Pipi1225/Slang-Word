package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Util.QuizUtil.*;

public class DefinitionQuizUI extends BaseQuizUI {
    public DefinitionQuizUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(backButton);

        JPanel defPanel = new JPanel();
        defPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Slang word represents this definition is?");
        label.setFont(label.getFont().deriveFont(14f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        quizLabel.setFont(quizLabel.getFont().deriveFont(17f));
        quizLabel.setForeground(new Color(52, 52, 106));
        quizLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        refreshQuiz();
        quizArea.setMaximumSize(quizArea.getPreferredSize());

        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(quizLabel);
        defPanel.add(contentPanel, BorderLayout.NORTH);
        defPanel.add(quizArea, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
        add(defPanel, BorderLayout.CENTER);
    }

    public void refreshQuiz() {
        String def = getRandomDefinition(dictionary, getRandomSlang(dictionary, -1));
        quizLabel.setText(def);

        List<JButton> buttons = new ArrayList<>();
        List<String> slangs = getDefinitionQuiz(dictionary.getDictionary(), def);
        for (int i = 0; i < slangs.size(); i++) {
            JButton button = getJButton(slangs, i);
            buttons.add(button);
        }

        quizArea.removeAll();
        Collections.shuffle(buttons);
        for (JButton button : buttons) {
            quizArea.add(button);
        }
    }
}
