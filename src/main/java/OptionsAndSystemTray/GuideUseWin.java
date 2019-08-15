package OptionsAndSystemTray;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class GuideUseWin extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private BoxLayout mainLayout;
    private JLabel firstImgLbl;
    private JLabel secondImgLbl;
    private JLabel thierdImgLbl;
    private JLabel firstStepExpLbl;
    private JLabel secondStepExpLbl;
    private JLabel thierdStepExpLbl;

    public GuideUseWin() {
        this.setSize(400, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel = new JPanel();
        this.contentPanel = new JPanel();
        this.mainLayout = new BoxLayout(this.contentPanel, BoxLayout.PAGE_AXIS);
        this.contentPanel.setLayout(this.mainLayout);
        this.add(this.mainPanel);
        this.firstImgLbl = new JLabel();

        ArrayList <JLabel> photosLblsArr = new ArrayList<JLabel>();
        photosLblsArr.add(this.firstImgLbl);
        photosLblsArr.add(this.secondImgLbl);
        photosLblsArr.add(this.thierdImgLbl);

        String[] imgsDescriptionArr = {"The mainly program window", "After selecting the first letters row", "After selecting a letter to be typed"};
        String[] stepsArr = {"Basic manual - how to use Easy Type",
                "As first you need to select a row, by typing on 1-3 keys which placed on numeric keyboard",
        "after that, choose the letter you want to be typed, by tapping on 0-9 keys on numeric " +
                "keyboard According to the index of The requested letter (beside to each letter has his index"};

        int i = 1;
        for (JLabel photoLbl : photosLblsArr) {
            StringBuilder sb = new StringBuilder("<html>");
            JLabel stepLbl = new JLabel("<html><span><font size=\"5\">" + stepsArr[i-1] + "</font></span></html>");
            stepLbl.setForeground(Color.black);
//            stepLbl.setFont(new Font("Serif", Font.BOLD, 20));
//            stepLbl.setBounds(100, 100, 100, 50);
//            stepLbl.setMinimumSize(new Dimension(100, 50));
            stepLbl.setOpaque(true);
            stepLbl.setBackground(Color.white);
            stepLbl.setSize(100, 300);
            this.contentPanel.add(stepLbl);
            photoLbl = new JLabel();
            URL photoUrl = getClass().getResource("photo" + i + ".png");
            photoLbl.setIcon(this.createIconAndFitHimToWinSize(photoUrl));
            photoLbl.setBorder(new EmptyBorder(10, 25, 10, 10));
            photoLbl.setFont(new Font("Serif", Font.BOLD, 14));
            photoLbl.setForeground(Color.blue);
            photoLbl.setText(imgsDescriptionArr[i++-1]);
            photoLbl.setHorizontalTextPosition(JLabel.CENTER);
            photoLbl.setVerticalTextPosition(JLabel.BOTTOM);
            this.contentPanel.add(photoLbl);

//            contentPanel.add(stepLbl);

        }
        JScrollPane pane = new JScrollPane(contentPanel);
        mainPanel.add(pane);

        this.firstStepExpLbl = new JLabel();
        this.secondStepExpLbl = new JLabel();
        this.thierdStepExpLbl = new JLabel();

        this.setVisible(true);
    }

    private ImageIcon createIconAndFitHimToWinSize(URL imgFileUrl) {
        ImageIcon imageIcon = new ImageIcon(imgFileUrl);
        return new ImageIcon(imageIcon.getImage().getScaledInstance((int) (imageIcon.getIconWidth() / 1.5),
                (int) (imageIcon.getIconHeight() / 1.5), Image.SCALE_DEFAULT));
    }

}
