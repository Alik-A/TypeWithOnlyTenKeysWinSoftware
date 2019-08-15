package ManageKeyboardTapAndPerformClicks.mainWinGUIManage;

import javax.swing.*;

public class HideSpecialActionsLbls extends Thread {
    private JLabel label;
    private int j;
    // the constructor initialize the lbl variable (some JLabel of special actions row) for make him invisible when
    // the program hide the special actions row
    public HideSpecialActionsLbls(JLabel label) {
        this.label = label;
    }
    // hide the special actions JLabel with "in slide" effect
    @Override
    public void run() {
        super.run();

        int i = label.getX();
        j = i - i / 2;
        for (; j != i; i--) {
            label.setLocation(i, 0);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        label.setVisible(false);
    }
}