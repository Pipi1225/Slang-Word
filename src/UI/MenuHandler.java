package UI;

import Service.SlangDictionary;

import javax.swing.*;
import java.awt.*;

public class MenuHandler extends JFrame {
    private final ShowHistoryUI showHistory;
    public MenuHandler(SlangDictionary dictionary) {
        setTitle("Slang Dictionary - Search by Slang");
        setSize(525, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JButton feature1 = new JButton("Tìm kiếm theo slang word");
        JButton feature2 = new JButton("Tìm kiếm theo definition");
        JButton feature3 = new JButton("Hiển thị lịch sử slang word");
        JButton feature4 = new JButton("Thêm slang word mới");
        JButton feature5 = new JButton("Chỉnh sửa slang word");
        JButton feature6 = new JButton("Xóa slang word");
        JButton feature7 = new JButton("Khôi phục danh sách slang words gốc");
        JButton feature8 = new JButton("Random 1 slang word");
        JButton feature9 = new JButton("Đố vui slang word");
        JButton feature10 = new JButton("Đố vui definition");
        JButton exit = new JButton("Thoát");

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JButton[] leftButtons = {feature1, feature2, feature3, feature4, feature5};
        for (JButton button : leftButtons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 30));
            leftPanel.add(button);
            leftPanel.add(Box.createVerticalStrut(10));
        }

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JButton[] rightButtons = {feature6, feature7, feature8, feature9, feature10};
        for (JButton button : rightButtons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 30));
            rightPanel.add(button);
            rightPanel.add(Box.createVerticalStrut(10));
        }

        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(100, 30));

        JPanel featurePanel = new JPanel(new GridLayout(1, 2));
        featurePanel.add(leftPanel, BorderLayout.CENTER);
        featurePanel.add(rightPanel, BorderLayout.CENTER);
        featurePanel.setMaximumSize(featurePanel.getPreferredSize());
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(featurePanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(exit);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        mainPanel.add(contentPanel, "menu");
        mainPanel.add(new SearchBySlangUI(dictionary, () -> cardLayout.show(mainPanel, "menu")), "searchBySlang");
        mainPanel.add(new SearchByDefinitionUI(dictionary, () -> cardLayout.show(mainPanel, "menu")), "searchByDefinition");
        showHistory = new ShowHistoryUI(dictionary, () -> cardLayout.show(mainPanel, "menu"));
        mainPanel.add(showHistory, "showHistory");
        mainPanel.add(new AddNewSlangUI(dictionary, () -> cardLayout.show(mainPanel, "menu")), "addNewSlang");


        feature1.addActionListener(e -> cardLayout.show(mainPanel, "searchBySlang"));
        feature2.addActionListener(e -> cardLayout.show(mainPanel, "searchByDefinition"));
        feature3.addActionListener(e -> {
            showHistory.reloadHistory();
            cardLayout.show(mainPanel, "showHistory");
        });
        feature4.addActionListener(e -> cardLayout.show(mainPanel, "addNewSlang"));
        exit.addActionListener(e -> System.exit(0));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 40, 20));
        add(mainPanel, BorderLayout.CENTER);
    }
}
