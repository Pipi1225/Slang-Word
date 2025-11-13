package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddEditSlangUI extends BaseFeatureUI {
    protected final JButton addNewMeaning = new JButton("+");
    protected List<String> listMeaning = new ArrayList<>();
    protected final JPanel meaningsPanel = new JPanel();
    protected final JTextField inputField = new JTextField(16);

    public AddEditSlangUI(SlangDictionary dictionary, Runnable onBack) {
        super(dictionary, onBack);
    }

    protected void resetForm(boolean addPlaceholder) {
        inputField.setText("");
        resetList(addPlaceholder);
    }

    protected void resetList(boolean addPlaceholder) {
        listMeaning.clear();
        if (addPlaceholder) {
            listMeaning.add("");
        }
        refreshPanel();
    }

    protected void refreshPanel() {
        meaningsPanel.removeAll();

        JPanel _contentPanel = new JPanel();
        _contentPanel.setLayout(new BoxLayout(_contentPanel, BoxLayout.Y_AXIS));
        if (!listMeaning.isEmpty()) {
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
        }

        meaningsPanel.add(_contentPanel);
        meaningsPanel.revalidate();
        meaningsPanel.repaint();
    }
}
