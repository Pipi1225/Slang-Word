package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class BaseQuizUI extends BaseFeatureUI {
    protected JPanel quizArea = new JPanel(new GridLayout(2, 2, 15, 15));
    protected JLabel quizLabel = new JLabel();
    public BaseQuizUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);
    }

    protected abstract void refreshQuiz();

    protected JButton getJButton(List<String> keys, int i) {
        JButton button = new JButton();
        button.setText("<html><center>" + keys.get(i) + "</center></html>");

        button.setMaximumSize(new Dimension(200, 30));
        if (i == 0) {
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Victory! You won!");
                refreshQuiz();
                revalidate();
                repaint();
            });
        } else {
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Oh no! You lose!");
                refreshQuiz();
                revalidate();
                repaint();
            });
        }
        return button;
    }
}
