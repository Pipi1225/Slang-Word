package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

public class DeleteSlangUI extends BaseFeatureUI {
    public DeleteSlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Enter slang word:");
        label.setFont(label.getFont().deriveFont(14f));
        JTextField inputField = new JTextField(16);
        JButton button = new JButton("Delete");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(inputField);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(button);
        buttonPanel.add(contentPanel);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        button.addActionListener(e -> {
            String slang = inputField.getText();
            if (slang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập nghĩa cho slang word!");
                return;
            }

            if (!dictionary.containsSlang(slang)) {
                JOptionPane.showMessageDialog(this, "Not found '" + slang + "' in database");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete '" + slang + "'?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                String message;
                if (dictionary.removeSlang(slang)) {
                    message = "'" + slang + "' has been successfully deleted!";
                } else {
                    message = "Failed to delete '" + slang + "'";
                }

                JOptionPane.showMessageDialog(
                        this,
                        message,
                        "Delete Complete",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}