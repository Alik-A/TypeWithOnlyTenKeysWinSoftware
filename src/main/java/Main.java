import LanguageManagePackage.SetInputLang;
import ManageKeyboardTapAndPerformClicks.ManageKeybdLayoutsAndLangsWin;
import OptionsAndSystemTray.AboutWin;
import OptionsAndSystemTray.OptionsWin;
import OptionsAndSystemTray.SysTray;
import ManageKeyboardTapAndPerformClicks.CatchClickedKeys;
import ManageKeyboardTapAndPerformClicks.NumericKeysInputBlock;


public class Main {
    public static void main(String[] args) {

        // read and set the last saved settings
        OptionsWin.readAndSetPref();
        // start the thread which will block 0-9 numeric keyboard input
        NumericKeysInputBlock.blockWindowsKey(new CatchClickedKeys(new SetInputLang()));
        SysTray sysTray = new SysTray();
        ManageKeybdLayoutsAndLangsWin test = new ManageKeybdLayoutsAndLangsWin();
        new AboutWin();
//        GuideUseWin guideUseWin = new GuideUseWin();
    }
}