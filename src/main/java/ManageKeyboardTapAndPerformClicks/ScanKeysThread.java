package ManageKeyboardTapAndPerformClicks;

import OptionsAndSystemTray.OptionsWin;

public class ScanKeysThread extends Thread {
    // if the property type using scanning keys this scanKeysThread will keep working and will perform the key scan
        public void run() {
            // scan rows until the user will select row of letters using the key that was defined in the
            // program settings
            while (OptionsWin.scanKeysOnOff && CatchClickedKeys.ind == -1) {
                if (!CatchClickedKeys.scanKeysTimer.isRunning())
                    CatchClickedKeys.scanKeysTimer.start();
            }
            // scan letters in the selected row until the user will select letters to be typed using the key that was defined in the
            // program settings
            while (OptionsWin.scanKeysOnOff && CatchClickedKeys.ind != -1) {
                CatchClickedKeys.scanKeysTimer.start();
            }
        }
}