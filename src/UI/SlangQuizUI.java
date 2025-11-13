package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Util.QuizUtil.*;

public class SlangQuizUI extends BaseFeatureUI {
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

        String slang = getRandomSlang(dictionary, -1);
        JLabel slangLabel = new JLabel(slang);
        slangLabel.setFont(label.getFont().deriveFont(24f));
        slangLabel.setForeground(new Color(52, 52, 106));
        slangLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<JButton> buttons = new ArrayList<>();
        JPanel quizArea = new JPanel(new GridLayout(2, 2, 15, 15));
        List<String> definitions = getSlangQuiz(dictionary.getDictionary(), slang);
        for (int i = 0; i < definitions.size(); i++) {
            JButton button = getJButton(onBack, definitions, i);
            buttons.add(button);
        }

        Collections.shuffle(buttons);
        for (JButton button : buttons) {
            quizArea.add(button);
        }

        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(slangLabel);
        slangPanel.add(contentPanel, BorderLayout.NORTH);
        slangPanel.add(quizArea, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
        add(slangPanel, BorderLayout.CENTER);
    }

    private JButton getJButton(Runnable onBack, List<String> definitions, int i) {
        JButton button = new JButton();
        button.setText("<html><center>" + definitions.get(i) + "</center></html>");

        button.setMaximumSize(new Dimension(200, 30));
        if (i == 0) {
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Victory! You won!");
                removeAll();
                add(new SlangQuizUI(dictionary, onBack));
                revalidate();
                repaint();
            });
        } else {
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Oh no! You lose!");
                removeAll();
                add(new SlangQuizUI(dictionary, onBack));
                revalidate();
                repaint();
            });
        }
        return button;
    }
}
