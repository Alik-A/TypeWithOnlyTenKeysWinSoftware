package ManageKeyboardTapAndPerformClicks;


import javax.swing.*;

public class LayoutsEditDataModel {
    private JTextField jTextField;
    private MutablePair<String, Integer> letterKeyCodePair;

    public LayoutsEditDataModel(JTextField jTextField, String letter, Integer keyCode) {
        this.jTextField = jTextField;
        this.letterKeyCodePair = new MutablePair<>(letter, keyCode);
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public MutablePair<String, Integer> getLetterKeyCodePair() {
        return letterKeyCodePair;
    }

    public void setLetterKeyCodePair(String letter, Integer keyCode) {
        this.letterKeyCodePair.setKeyValValues(letter, keyCode);
    }


    public static class MutablePair<A, B> {
        private A fst;
        private B snd;

        public MutablePair(A fst, B snd) {
            this.fst = fst;
            this.snd = snd;
        }

        public A getFst() {
            return fst;
        }

        public void setFst(A fst) {
            this.fst = fst;
        }

        public B getSnd() {
            return snd;
        }

        public void setSnd(B snd) {
            this.snd = snd;
        }

        private void setKeyValValues(A fst, B snd) {
            this.fst = fst;
            this.snd = snd;
        }

    }
}
