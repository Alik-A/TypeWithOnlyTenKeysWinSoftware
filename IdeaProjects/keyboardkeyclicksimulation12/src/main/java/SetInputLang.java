import javax.swing.*;
import java.awt.im.InputContext;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SetInputLang{

    public static String currentLang;
    public static ArrayList<String > chars = new ArrayList<String>();
    private static int flag = 0;
    private static FileInputStream langsFile;
    private static BufferedReader langLetters;
    private static String fileLine;
    private static String fileName = "langs.txt";
    private static FileReader file;
    private static ArrayList <String> lettersList;
    public static String getCurrentLang() {
        return currentLang;
    }

    public static void setCurrentLang(String currentLang) {
        SetInputLang.currentLang = currentLang;
    }

    public static void setInputLang() {
        currentLang = InputContext.getInstance().getLocale().toString();
    }

    public static void changeLblsLetters() {
        setInputLang();
        System.out.println(currentLang + "/n");
        try {
            file = new FileReader(fileName);
            langLetters = new BufferedReader(file);
            while ((fileLine = langLetters.readLine()) != null){
//                System.out.println(fileLine);
//                System.out.println(currentInputLang + "/n");

                if(fileLine.toString().equals(currentLang)) {
                    fileLine = langLetters.readLine();
                lettersList = new ArrayList<String>(Arrays.asList(fileLine.toString().split(" ")));
                int i = 0;

                        for (String s1: lettersList) {
                            if (i == 0 || i == 11 || i == 22) {
                                i++;
                            }
                                GUIKeysWin.jLabelsArrayList.get(i).setText(s1);
                                i++;
                            System.out.print(s1 + " ");
                        }
                        return;

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
