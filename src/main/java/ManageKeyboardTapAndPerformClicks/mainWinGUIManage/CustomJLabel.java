package ManageKeyboardTapAndPerformClicks.mainWinGUIManage;

import javax.swing.*;
import java.awt.*;

public class CustomJLabel extends JLabel {
    // constructor for create rows JLabels
    public CustomJLabel(String text, int width, int height, Boolean opaque, Font font, Color backgroundColor,
                        Color foregroundColor, int txtOrientation) {
        this.setText(text);
        this.setSize(width, height);
        this.setOpaque(opaque);
        this.setFont(font);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        this.setHorizontalAlignment(txtOrientation);
    }
    // constructor for create indexes JLabels
    public CustomJLabel(String text, int width, int height, Font font,
                        Color foregroundColor) {
        this.setText(text);
        this.setSize(width, height);
        this.setFont(font);
        this.setForeground(foregroundColor);
    }
}