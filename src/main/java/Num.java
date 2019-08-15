import ManageKeyboardTapAndPerformClicks.CatchClickedKeys;
import ManageKeyboardTapAndPerformClicks.NumericKeyCodes;
import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static java.lang.Thread.sleep;

public class Num {
    // The structure as implemented by the MSDN article

     public static void main(String[] args) throws IOException, InterruptedException {


        for (int i = 0; i < 1; i++) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_NUMPAD3);
                robot.keyRelease(KeyEvent.VK_NUMPAD3);
                robot.keyRelease(KeyEvent.VK_ALT);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            pressSpChTest(i);
            System.out.println(i);
            sleep(1000);
        }
//
//        char[] chars = new char[512];
//        WinDef.HWND hwnd = User32.INSTANCE.GetForegroundWindow();
//        WinDef.HWND hwnd1 = User32.INSTANCE.FindWindow("SunAwtFrame", "Easy type");
//        User32.INSTANCE.GetWindowText(hwnd, chars, 512);
//        // assign the window handle here.
//            sleep(3000);
//        User32.INSTANCE.SetForegroundWindow(hwnd1);
    }
    protected static void performTypeByPressedKeyCode(int pressedVKCode) {

        WinUser.INPUT input = new WinUser.INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki"); // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        input.input.ki.wScan = new WinDef.WORD(0);
        input.input.ki.time = new WinDef.DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);

        // Press shift button
        input.input.ki.wVk = new WinDef.WORD(16); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(0);  // keydown
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());

        // Press "a"
        input.input.ki.wVk = new WinDef.WORD(0xa1); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(0);  // keydown
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());

        // Release "a"
        input.input.ki.wVk = new WinDef.WORD(0xa1); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(2);  // keyup
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());

        // Release shift key
        input.input.ki.wVk = new WinDef.WORD(16); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(2);  // keyup
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());

    }

    protected static void pressSpChTest(int vkCode) {
        try {
            Robot robot = new Robot();

            StringSelection stringSelection = new StringSelection("#");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);

            clipboard.setContents(stringSelection, null);


            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_NUMPAD9);
            robot.keyRelease(KeyEvent.VK_ALT);
        } catch (AWTException e) {
        e.printStackTrace();
        }
    }
}