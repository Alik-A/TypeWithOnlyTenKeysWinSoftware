package OptionsAndSystemTray;

import ManageKeyboardTapAndPerformClicks.ManageKeybdLayoutsAndLangsWin;
import ManageKeyboardTapAndPerformClicks.mainWinGUIManage.MainlyGUIWin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SysTray {
    private Toolkit toolkit;
    private Image sysTrayImg;
    private SystemTray sysTray;
    private PopupMenu popupMenu;
    private Menu fadeMIt;
    private MenuItem manageKeybdLangsAndLayoutsMI;
    private MenuItem optionsMIt;
    private MenuItem exitIMt;
    private MenuItem fade30;
    private MenuItem fade50;
    private MenuItem fade80;
    private MenuItem fade100;
    private TrayIcon icon;

    public SysTray() {

        this.toolkit = Toolkit.getDefaultToolkit();
        System.out.println(getClass().getResource(""));

        this.sysTrayImg = this.toolkit.getImage(getClass().getResource("icon.png"));


        this.sysTray = SystemTray.getSystemTray();
        // initialize the popup menu object (the popup menu which the user can open from the program icon in system tray)
        this.popupMenu = new PopupMenu();
        //  The set opacity of the main win of the program main menu - menu item
        this.fadeMIt = new Menu("Opacity    ");
        // the open manage keyboard layouts and langs of Easy TypeWin - menu item
        this.manageKeybdLangsAndLayoutsMI = new MenuItem("manage layouts & languages");

        // the open Easy Type options window menu item
        this.optionsMIt = new MenuItem("Options");
        // the exit from Easy Type program menu item
        this.exitIMt = new MenuItem("Exit");
        // set opacity sub menu items
        this.fade30 = new MenuItem("30%");
        this.fade50 = new MenuItem("50%");
        this.fade80 = new MenuItem("80%");
        this.fade100 = new MenuItem("100%");
        // add the main window set opacity to fadeMIt menu item as sub menu
        this.fadeMIt.add(this.fade30);
        this.fadeMIt.add(this.fade50);
        this.fadeMIt.add(this.fade80);
        this.fadeMIt.add(this.fade100);
        // opacity item: set the opacity of the main program win to 30%
        this.fade30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainlyGUIWin.mainlyKeysJFrame.setOpacity(0.3f);
            }
        });
        // menu item: opacity item: set the opacity of the main program win to 50%
        this.fade50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainlyGUIWin.mainlyKeysJFrame.setOpacity(0.5f);
            }
        });
        // menu item: opacity item: set the opacity of the main program win to 80%
        this.fade80.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainlyGUIWin.mainlyKeysJFrame.setOpacity(0.8f);
            }
        });
        // menu item: opacity item: set the opacity of the main program win to 100%
        this.fade100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainlyGUIWin.mainlyKeysJFrame.setOpacity(1.0f);
            }
        });

        // menu item: manage keybd layouts & languages item: open the manage languages of window of Easy Type
        this.manageKeybdLangsAndLayoutsMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageKeybdLayoutsAndLangsWin manageKeybdLayoutsAndLangsWin = new ManageKeybdLayoutsAndLangsWin();
            }
        });
        // menu item: options item: open the options window of Easy Type
        this.optionsMIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsWin optionsWin = new OptionsWin();
            }
        });
        // menu item: exit item: exit from Easy Type
        this.exitIMt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        // add the menu items to the popup menu which opened when the user will clicking with right mouse button on
        // the Easy Type icon that shown in system tray
        this.popupMenu.add(this.fadeMIt);
        this.popupMenu.add(this.manageKeybdLangsAndLayoutsMI);
        this.popupMenu.add(this.optionsMIt);
        this.popupMenu.add(this.exitIMt);
        // create icon object that will be shown in system tray set to him the Easy Type logo to as system tray icon,
        // set the name of program as tooltips text, and set the popup menu to be opened when clicking with
        // the right mouse button
        this.icon = new TrayIcon(this.sysTrayImg, "Easy type", this.popupMenu);
        this.icon.setImageAutoSize(true);
        // add Easy Type icon to the system tray
        try {
            this.sysTray.add(this.icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}