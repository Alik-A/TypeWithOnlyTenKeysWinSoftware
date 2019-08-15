package ManageKeyboardTapAndPerformClicks;

import java.util.ArrayList;

public class NumericKeyCodes {
    // static variables, VK CODES of the numeric keyboard 0-9 keys
    public static final int ZERO_VK_CODE = 96;
    public static final int ONE_VK_CODE = 97;
    public static final int TWO_VK_CODE = 98;
    public static final int THREE_VK_CODE = 99;
    public static final int FOUR_VK_CODE = 100;
    public static final int FIVE_VK_CODE = 101;
    public static final int SIX_VK_CODE = 102;
    public static final int SEVEN_VK_CODE = 103;
    public static final int EIGHT_VK_CODE = 104;
    public static final int NINE_VK_CODE = 105;

    public static ArrayList<Integer> rowsVirtualKeyCodes;
    public static ArrayList<Integer> indexesVirtualKeyCodes;
    static {
        // Setting up the rowsVirtualKeyCodes array list: array list which including the possible rows
        rowsVirtualKeyCodes = new ArrayList<>();
        rowsVirtualKeyCodes.add(ONE_VK_CODE);
        rowsVirtualKeyCodes.add(TWO_VK_CODE);
        rowsVirtualKeyCodes.add(THREE_VK_CODE);
        rowsVirtualKeyCodes.add(FOUR_VK_CODE);
        rowsVirtualKeyCodes.add(SEVEN_VK_CODE);
        rowsVirtualKeyCodes.add(EIGHT_VK_CODE);
        // Setting up the RowKeys array list (0-9): array list which including the possible indexes of letter/special
        // sign/special action in selected row
        indexesVirtualKeyCodes = new ArrayList<>();
        indexesVirtualKeyCodes.add(ZERO_VK_CODE);
        indexesVirtualKeyCodes.add(ONE_VK_CODE);
        indexesVirtualKeyCodes.add(TWO_VK_CODE);
        indexesVirtualKeyCodes.add(THREE_VK_CODE);
        indexesVirtualKeyCodes.add(FOUR_VK_CODE);
        indexesVirtualKeyCodes.add(FIVE_VK_CODE);
        indexesVirtualKeyCodes.add(SIX_VK_CODE);
        indexesVirtualKeyCodes.add(SEVEN_VK_CODE);
        indexesVirtualKeyCodes.add(EIGHT_VK_CODE);
        indexesVirtualKeyCodes.add(NINE_VK_CODE);
    }
}