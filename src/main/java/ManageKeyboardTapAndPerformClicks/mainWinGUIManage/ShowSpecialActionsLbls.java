package ManageKeyboardTapAndPerformClicks.mainWinGUIManage;

import javax.swing.*;

public class ShowSpecialActionsLbls extends Thread {
    private JLabel lbl;
    private int x;
    // the constructor initialize the lbl variable (some JLabel of special actions row) and the x variable (the start
    // position of the received JLabel) for make him to be visible with slide effect
    public ShowSpecialActionsLbls(JLabel lbl, int x) {
        this.lbl = lbl;
        this.x = x;
    }
    // this method make the label to be visible and move him to his place with slide effect
    @Override
    public void run() {
        this.lbl.setVisible(true);
        for (int i = MainlyGUIWin.specialActionsLblsWidth; i > 0; i--) {
            this.lbl.setLocation(this.x++, 0);
            try {
                sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}