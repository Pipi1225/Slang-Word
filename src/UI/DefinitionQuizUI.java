package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Util.QuizUtil.*;

public class DefinitionQuizUI extends BaseFeatureUI {
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

        String def = getRandomDefinition(dictionary, getRandomSlang(dictionary, -1));
        JLabel defLabel = new JLabel(def);
        defLabel.setFont(label.getFont().deriveFont(17f));
        defLabel.setForeground(new Color(52, 52, 106));
        defLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<JButton> buttons = new ArrayList<>();
        JPanel quizArea = new JPanel(new GridLayout(2, 2, 15, 15));
        List<String> slangs = getDefinitionQuiz(dictionary.getDictionary(), def);
        for (int i = 0; i < slangs.size(); i++) {
            JButton button = getJButton(onBack, slangs, i);
            buttons.add(button);
        }

        Collections.shuffle(buttons);
        for (JButton button : buttons) {
            quizArea.add(button);
        }
        quizArea.setMaximumSize(quizArea.getPreferredSize());

        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(defLabel);
        defPanel.add(contentPanel, BorderLayout.NORTH);
        defPanel.add(quizArea, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
        add(defPanel, BorderLayout.CENTER);
    }

    private JButton getJButton(Runnable onBack, List<String> slangs, int i) {
        JButton button = new JButton();
        button.setText("<html><center>" + slangs.get(i) + "</center></html>");

        button.setMaximumSize(new Dimension(200, 30));
        if (i == 0) {
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Victory! You won!");
                removeAll();
                add(new DefinitionQuizUI(dictionary, onBack));
                revalidate();
                repaint();
            });
        } else {
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Oh no! You lose!");
                removeAll();
                add(new DefinitionQuizUI(dictionary, onBack));
                revalidate();
                repaint();
            });
        }
        return button;
    }
}
