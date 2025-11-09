package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import static Util.Utility.filterEmpty;

public class AddNewSlangUI extends BaseFeatureUI {
    JButton addNewMeaning = new JButton("+");
    List<String> listMeaning = new ArrayList<>();
    JPanel meaningsPanel;
    JTextField inputField = new JTextField(16);

    JButton overwriteButton = new JButton("Overwrite");
    JButton duplicateButton = new JButton("Duplicate");
    int flag = 0;
    // -1 - overwrite
    // 0  - current slang word unique
    // 1  - duplicate (add more meaning)

    public AddNewSlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Nhập slang word:");

        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(label);
        topPanel.add(inputField);

        meaningsPanel = new JPanel();
        meaningsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        meaningsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JScrollPane scrollPane = new JScrollPane(meaningsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        JButton resetButton = new JButton("Reset");
        JButton confirmButton = new JButton("Confirm");

        overwriteButton.setEnabled(false);
        duplicateButton.setEnabled(false);

        actionPanel.add(overwriteButton);
        actionPanel.add(duplicateButton);
        actionPanel.add(Box.createHorizontalStrut(80));
        actionPanel.add(resetButton);
        actionPanel.add(confirmButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        listMeaning.add("");
        refreshPanel();

        addNewMeaning.addActionListener(e -> {
            listMeaning.add("");
            refreshPanel();
        });

        overwriteButton.addActionListener(e -> {
            overwriteButton.setEnabled(false);
            duplicateButton.setEnabled(true);
            flag = -1;
        });

        duplicateButton.addActionListener(e -> {
            overwriteButton.setEnabled(true);
            duplicateButton.setEnabled(false);
            flag = 1;
        });

        resetButton.addActionListener(e -> resetList());

        confirmButton.addActionListener(e -> {
            String slang = inputField.getText().trim();
            if (slang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập slang word!");
                return;
            }

            List<String> definitions = filterEmpty(listMeaning);
            if (definitions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập nghĩa cho slang word!");
                return;
            }

            if (dictionary.containsSlang(slang) && flag == 0) {
                JOptionPane.showMessageDialog(this, "Slang word này đã tồn tại, tự động chuyển sang chế độ thêm nghĩa (Duplicate).");
                overwriteButton.setEnabled(true);
                flag = 1;
                return;
            }

            if (flag == -1) {
                dictionary.overwriteSlang(slang, definitions);
                JOptionPane.showMessageDialog(this, String.format("Ghi đè nghĩa của '%s' thành công!", slang));
            } else if (flag == 0 || flag == 1) {
                dictionary.updateSlang(slang, definitions, flag);
                JOptionPane.showMessageDialog(this, String.format("Thêm nghĩa của '%s' thành công!", slang));
            }

            resetForm();
        });
    }

    private void resetForm() {
        flag = 0;
        overwriteButton.setEnabled(false);
        duplicateButton.setEnabled(false);
        inputField.setText("");
        resetList();
    }

    private void resetList() {
        listMeaning.clear();
        listMeaning.add("");
        refreshPanel();
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