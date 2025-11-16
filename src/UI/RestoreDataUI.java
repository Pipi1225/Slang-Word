package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

public class RestoreDataUI extends BaseFeatureUI {
    public RestoreDataUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Click here if you want to restore the data");
        label.setFont(label.getFont().deriveFont(14f));
        JButton button = new JButton("Reset Data");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(button);
        buttonPanel.add(contentPanel);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        button.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to reset the slang dictionary to default?",
                    "Confirm Reset",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                dictionary.reloadFromFile("data/slang.txt");

                JOptionPane.showMessageDialog(
                        this,
                        "Data has been successfully restored!",
                        "Restore Complete",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}