import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private JPanel jPanel;
    private GroupLayout groupLayout;

    public Gui() {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.jPanel = new JPanel();

        Button button = new Button("test lbl");
        button.setLocation(300, 200);
        button.addActionListener(seton);
        this.jPanel.add(button);
        this.add(this.jPanel);
        this.setVisible(true);
    }
}
