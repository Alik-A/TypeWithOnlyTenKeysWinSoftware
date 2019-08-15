package LanguageManagePackage;

import ManageKeyboardTapAndPerformClicks.CatchClickedKeys;
import ManageKeyboardTapAndPerformClicks.KeyboardTypePerform;
import ManageKeyboardTapAndPerformClicks.mainWinGUIManage.CustomJLabel;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import ManageKeyboardTapAndPerformClicks.mainWinGUIManage.MainlyGUIWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.im.InputContext;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class SetInputLang{

    public static String currentInputLang;
    public SetInputLang() {

    }

    public void setChangeInputLangThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                changeInputLang();
            }
        }).start();

    }

    public String getCurrentInputLang() {
        return currentInputLang;
    }

    public void setCurrentInputLang() {
        currentInputLang = InputContext.getInstance().getLocale().toString();
    }
    // change input lang (like ALT + SHIFT) and change the labels text adjusting to new sets language (the letters)
    public void changeInputLang() {
        try {
            WinDef.HWND currentFocusedWinHWND = User32.INSTANCE.GetForegroundWindow();
            WinDef.HWND easyTypeHWND = User32.INSTANCE.FindWindow("SunAwtFrame", "Easy type");
            User32.INSTANCE.SetForegroundWindow(easyTypeHWND);
            sleep(150);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_ALT);
            sleep(200);
            this.setCurrentInputLang();
            sleep(200);
            this.getLettersOfCurrentInputLang();
            User32.INSTANCE.SetForegroundWindow(currentFocusedWinHWND);
        } catch (InterruptedException | AWTException e1) {
            e1.printStackTrace();
        }
    }
    public void getLettersOfCurrentInputLang() {
        setCurrentInputLang();
        System.out.println(currentInputLang + "/n");
        try {
            if (new File("langsList.txt").exists()) {
                FileReader fileReader = new FileReader("langsList.txt");
                BufferedReader reader = new BufferedReader(fileReader);
                String readLine = null;
                while ((readLine = reader.readLine()) != null) {
                    if (readLine.contains(currentInputLang)) {
                        int numOfRows = Integer.parseInt(reader.readLine());
                        String[] currentLangLetters = reader.readLine().split(" ");
                        setCurrentLangLettersToLettersLbls(currentLangLetters, numOfRows);
                        String[] vkList = reader.readLine().split(" ");
                        KeyboardTypePerform.vKCodesOfCurrLang.clear();
                        for (String vk : vkList) {
                            KeyboardTypePerform.vKCodesOfCurrLang.add(Integer.valueOf(vk));
                        }
                    }
                }
                reader.close();
                fileReader.close();
            }
            else {
                Path file = Paths.get("langsList.txt");
                Files.write(file, Collections.singleton(""), Charset.forName("UTF-8"));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setCurrentLangLettersToLettersLbls(String[] currentLangLetters, int numOfRows) {
        MainlyGUIWin.setLettersRowsNum(numOfRows/10);
        int i = 0;
        for (CustomJLabel label: MainlyGUIWin.threeRowsJLabelsArrayList) {
            if (label.getBackground() != MainlyGUIWin.rowNumsColor) {
                label.setText(currentLangLetters[i++]);
            }
        }
    }

    public void switchLblsCapitalLetters(Boolean capitalInd) {
        if (capitalInd) {
            for (JLabel label: MainlyGUIWin.threeRowsJLabelsArrayList) {
                label.setText(label.getText().toUpperCase());
            }
            if (this.getCurrentInputLang().equals("ru_RU")) {
                int newWinWidth = MainlyGUIWin.mainlyKeysJFrame.getWidth()*125/100;
                MainlyGUIWin.mainlyKeysJFrame.setSize(newWinWidth, MainlyGUIWin.mainlyKeysJFrame.getHeight());
                MainlyGUIWin.mainlyKeysJFrame.repaint();
                MainlyGUIWin.mainlyKeysJFrame.validate();
                MainlyGUIWin.mainlyKeysJFrame.revalidate();
                MainlyGUIWin.mainlyKeysJFrame.setShape(new RoundRectangle2D.Double(0,0, MainlyGUIWin.mainlyKeysJFrame.getWidth(), MainlyGUIWin.mainlyKeysJFrame.getHeight(), 20, 20));
            }
        }
        else {
            for (JLabel label: MainlyGUIWin.threeRowsJLabelsArrayList) {
                label.setText(label.getText().toLowerCase());
            }

            if (this.getCurrentInputLang().equals("ru_RU")) {
                int newWinWidth = MainlyGUIWin.mainlyKeysJFrame.getWidth()*75/100;
                MainlyGUIWin.mainlyKeysJFrame.setSize(newWinWidth, MainlyGUIWin.mainlyKeysJFrame.getHeight());
                MainlyGUIWin.mainlyKeysJFrame.repaint();
                MainlyGUIWin.mainlyKeysJFrame.validate();
                MainlyGUIWin.mainlyKeysJFrame.revalidate();
                MainlyGUIWin.mainlyKeysJFrame.setShape(new RoundRectangle2D.Double(0,0, MainlyGUIWin.mainlyKeysJFrame.getWidth(), MainlyGUIWin.mainlyKeysJFrame.getHeight(), 20, 20));
            }

        }
    }

   public void setSpecialChars() {
       try {
           if (new File("langsList.txt").exists()) {
               FileReader fileReader = new FileReader("langsList.txt");
               BufferedReader reader = new BufferedReader(fileReader);
               String readLine = null;
               while ((readLine = reader.readLine()) != null) {
                   if (readLine.contains("sp_CHR")) {
                       int numOfRows = Integer.parseInt(reader.readLine());
                       String[] currentLangLetters = reader.readLine().split(" ");
                       setCurrentLangLettersToLettersLbls(currentLangLetters, numOfRows);
                       String[] vkList = reader.readLine().split(" ");
                       KeyboardTypePerform.vKCodesOfCurrLang.clear();
                       for (String vk : vkList) {
                           KeyboardTypePerform.vKCodesOfCurrLang.add(Integer.valueOf(vk));
                       }
                   }
               }
               reader.close();
               fileReader.close();
           }
           else {
               Path file = Paths.get("langsList.txt");
               Files.write(file, Collections.singleton(""), Charset.forName("UTF-8"));
           }
       } catch(IOException e){
           e.printStackTrace();
       }
   }
    // called when the program started to solve input language problems
    public static void setSameInputMethodForAllApps() {
        try {
            Runtime.getRuntime().exec("powershell.exe Set-WinLanguageBarOption");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}