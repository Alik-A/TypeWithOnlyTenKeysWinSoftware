import java.util.ArrayList;

public class ClickDo extends GUIKeysWin{

        protected ArrayList<Integer> chooseRow;
        protected ArrayList<Integer> rowKeys;


        public ClickDo() {
            // Setting up the rowsVirtualKeyCodes array list
            super();
            this.chooseRow = new ArrayList<Integer>();
            this.chooseRow.add(79);
            this.chooseRow.add(80);
            this.chooseRow.add(81);

            // Setting up the firstRowKeys array list

            this.rowKeys = new ArrayList<Integer>();
            this.rowKeys.add(82);
            this.rowKeys.add(79);
            this.rowKeys.add(80);
            this.rowKeys.add(81);
            this.rowKeys.add(75);
            this.rowKeys.add(76);
            this.rowKeys.add(77);
            this.rowKeys.add(71);
            this.rowKeys.add(72);
            this.rowKeys.add(73);
        }

        public ClickDo(ArrayList<Integer> chooseRow, ArrayList<Integer> RowKeys) {
            this.chooseRow = chooseRow;
            this.rowKeys = RowKeys;
        }

        public ArrayList<Integer> getChooseRow() {
            return this.chooseRow;
        }

        public void setChooseRow(ArrayList<Integer> chooseRow) {
            this.chooseRow = chooseRow;
        }

/*
    public static boolean keyPress(String[] args) {

        .addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_MINUS) {
                // do the stuff
                Robot robot = null;
                try {
                    robot = new Robot();
                    // Simulate a key press
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_A);
                }
                catch (AWTException e1) {
                    e1.printStackTrace();
                }
            }
        }
    });
    }*/

    }

