package OptionsAndSystemTray;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutWin extends JFrame {

    private JPanel jPanel;
    private BorderLayout borderLayout;
    private JLabel titleLbl;
    private JLabel aboutSoftwareLbl;

    public AboutWin() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // initialize the jFrame
        this.setSize((int) (screenSize.width * 0.37), (int) (screenSize.height * 0.957));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.jPanel = new JPanel();
        this. borderLayout = new BorderLayout();
        this.jPanel.setLayout(this.borderLayout);

        this.titleLbl = new JLabel(" About Easy Type");
        this.titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        this.titleLbl.setFont(new Font("Serif", Font.BOLD, (int) (screenSize.width* 0.02)));
        this.jPanel.add(this.titleLbl, BorderLayout.NORTH);

        this.aboutSoftwareLbl = new JLabel();
        this.aboutSoftwareLbl.setText("<html>\n" +
                "<body>\n" +
                "<p>\n" +
                "<h2>Why did you create the Easy Type software?</h2>\n" +
                "<h3>Easy type software was made for being a solution for the problem of typing on a standard keyboard over time or at all, which usually troubles people with motor disabilities, but not only.</h3>\n" +
                "</p>\n" +
                "<p>\n" +
                "<h2>What can I do with easy type Software?</h2>\n" +
                "<h3>with easy type you can type in most languages, signs, using only one hand! By pressing the ten keypad keys only!\n" +
                " these gives you: <br>\n" +
                "<ul>\n" +
                "<li>the ability to type consistently for a long time using one hand only.</li><br> \n" +
                "<li>type using only native numeric keyboard, which enables to adjust Easy Type for using with right or left hand, it’s good also if you want to save space on your computer table (desktop), if your laptop's keyboard is not comfortable for you <br>to type or if your computer keyboard have some defects.</li><br>\n" +
                "<li>Easy Type is<br> also ready for learning New languages: you can to add new languages<br> easley, and customize the layout of the letters for each language.</li></h3>\n" +
                "</ul>" +
                "</p>\n" +
                "<p>\n" +
                "<h2>How much does it cost?</h2>\n" +
                "<h3>It's free to help as many people as possible. Just use it.\n" +
                "I will be Happy to know  that this software helped to someone.</h3>\n" +
                "</p>\n" +
                "<br>- Alik Amuev\n" +
                "\n" +
                "</body>\n" +
                "</html>\n");
        this.aboutSoftwareLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.aboutSoftwareLbl.setOpaque(true);
        this.aboutSoftwareLbl.setBackground(Color.white);
        this.aboutSoftwareLbl.setPreferredSize(new Dimension((int) (screenSize.width * 0.25), (int) (screenSize.height * 0.85)));
        this.jPanel.add(this.aboutSoftwareLbl, BorderLayout.CENTER);
        this.jPanel.add(new JScrollPane(this.aboutSoftwareLbl));
        this.add(this.jPanel);
        this.setResizable(false);
        this.setVisible(true);
    }
}
