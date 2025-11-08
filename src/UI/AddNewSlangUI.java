package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class AddNewSlangUI extends BaseFeatureUI {
    JButton addNewMeaning = new JButton("+");
    List<String> listMeaning = new ArrayList<>();
    JPanel meaningsPanel;

    public AddNewSlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Nhập slang word:");
        JTextField inputField = new JTextField(16);

        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(label);
        topPanel.add(inputField);

        meaningsPanel = new JPanel();
        meaningsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        meaningsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        JButton confirmButton = new JButton("Confirm");
        confirmPanel.add(confirmButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(meaningsPanel, BorderLayout.CENTER);
        contentPanel.add(confirmPanel, BorderLayout.SOUTH);
        add(contentPanel, BorderLayout.CENTER);

        listMeaning.add("");
        refreshPanel();

        addNewMeaning.addActionListener(e -> {
            listMeaning.add("");
            refreshPanel();
        });

        confirmButton.addActionListener(e -> {

        });
    }

    private void refreshPanel() {
        meaningsPanel.removeAll();

        JPanel _contentPanel = new JPanel();
        _contentPanel.setLayout(new BoxLayout(_contentPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < listMeaning.size(); i++) {
            JPanel _meaning = new JPanel();
            _meaning.setLayout(new BoxLayout(_meaning, BoxLayout.X_AXIS));

            final int index = i;
            JTextField _newMeaning = new JTextField(30);
            _newMeaning.setFont(new Font("Monospaced", Font.PLAIN, 14));
            _newMeaning.setText(listMeaning.get(i));
            _newMeaning.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    listMeaning.set(index, _newMeaning.getText());
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    listMeaning.set(index, _newMeaning.getText());
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    listMeaning.set(index, _newMeaning.getText());
                }
            });
            _meaning.add(_newMeaning);

            if (listMeaning.size() > 1) {
                JButton _deleteButton = new JButton("-");
                _deleteButton.addActionListener(ev -> {
                    listMeaning.remove(index);
                    refreshPanel();
                });
                _meaning.add(Box.createHorizontalStrut(10));
                _meaning.add(_deleteButton);
            }

            _meaning.setMaximumSize(_meaning.getPreferredSize());

            _contentPanel.add(_meaning);
            _contentPanel.add(Box.createVerticalStrut(10));
        }
        JPanel plusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        plusPanel.add(addNewMeaning);

        _contentPanel.add(plusPanel, BorderLayout.CENTER);
        meaningsPanel.add(_contentPanel);
        meaningsPanel.revalidate();
        meaningsPanel.repaint();
    }
}
