package ManageKeyboardTapAndPerformClicks;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;

import static com.sun.jna.Platform.isWindows;
import static com.sun.jna.platform.win32.WinUser.WH_KEYBOARD_LL;
import static com.sun.jna.platform.win32.WinUser.WM_KEYDOWN;

public class NumericKeysInputBlock extends Thread  {
    private static HHOOK hhk;
    private static LowLevelKeyboardProc keyboardHook;
    private static User32 lib;
    public static Thread thread;
    // make and start scanKeysThread that will block input of numeric keyboard keys: 0-9, *, /

    public static void blockWindowsKey(CatchClickedKeys catchClickedKeys) {
        if (isWindows()) {
            thread = new Thread(new Runnable() {
                public void run() {
                    lib = User32.INSTANCE;
                    HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
                    keyboardHook = new LowLevelKeyboardProc() {

                        public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
                            System.out.println("special's value: " + CatchClickedKeys.easyTypePause);
                            if (wParam.intValue() == WM_KEYDOWN && ((info.vkCode >= 96 && info.vkCode <= 109)
                                    || info.vkCode == 111)) {
                                if (info.vkCode == 107) {
                                    CatchClickedKeys.easyTypePause = !CatchClickedKeys.easyTypePause;
                                    return new LRESULT(1);
                                }
                                else if (!CatchClickedKeys.easyTypePause) {
                                    catchClickedKeys.callToPerformTypeMethod(info.vkCode);
                                    return new LRESULT(1);
                                }

                            }
                            else if (wParam.intValue() == WM_KEYDOWN && ManageKeybdLayoutsAndLangsWin.learningMode) ManageKeybdLayoutsAndLangsWin.currVKCode = info.vkCode;
                            return null;
                        }
                    };
                    hhk = lib.SetWindowsHookEx(WH_KEYBOARD_LL, keyboardHook, hMod, 0);
                    // This bit never returns from GetMessage=
                    int result;
                    MSG msg = new MSG();
                    while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
                        if (result == -1) {
                            break;
                        } else {
                            lib.TranslateMessage(msg);
                            lib.DispatchMessage(msg);
                        }
                    }
                    lib.UnhookWindowsHookEx(hhk);
                }
            });
            thread.start();
        }
    }
public static synchronized void ll() {
    synchronized (thread) {
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}