import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrayCode {
    private ArrayList<Integer> grayNum;
    private ArrayList<Integer> binaryNum;
    private Integer decimalNum;

    public GrayCode(ArrayList<Integer> grayNum, ArrayList<Integer> binaryNum, Integer decimalNum) {
        this.grayNum = grayNum;
        this.binaryNum = binaryNum;
        this.decimalNum = decimalNum;
    }

    public GrayCode() {
        this.binaryNum = new ArrayList<>();
        this.grayNum = new ArrayList<>();
    }

    public ArrayList<Integer> getGrayNum() {
        return grayNum;
    }

    public void setGrayNum(ArrayList<Integer> grayNum) {
        this.grayNum = grayNum;
    }

    public ArrayList<Integer> getBinaryNum() {
        return binaryNum;
    }

    public void setBinaryNum(ArrayList<Integer> binaryNum) {
        this.binaryNum = binaryNum;
    }

    public Integer getDecimalNum() {
        return decimalNum;
    }

    public void setDecimalNum(Integer decimalNum) {
        this.decimalNum = decimalNum;
    }

    public void generateGrayNum(Integer min, Integer max) {
        Random random = new Random();
        this.decimalNum = random.nextInt((max - min) + 1) + min;
        this.convertAndSetToBinary(this.decimalNum);
        this.convertAndSetToGrayCode(this.binaryNum);
    }

    private void convertAndSetToBinary(Integer decNum) {
        Integer temp = decNum <= 16 ? 16 : decNum <= 32 ? 32 : decNum <= 64 ? 64 : decNum <= 128 ? 128 : decNum <= 256 ? 256 : 512;
        System.out.println(decNum);
        if (temp > decNum) temp /= 2;
        while (temp != 0) {
            binaryNum.add(temp <= decNum ? 1 : 0);
            System.out.print(temp <= decNum ? 1 : 0);
            if(temp <= decNum) decNum -= temp;
             temp /= 2;
//            System.out.println("temp: " + temp + " " + "decNum: " + decimalNum);
        }
    }

    private void convertAndSetToGrayCode(ArrayList<Integer> binNum) {
        this.grayNum.add(binNum.get(0));
        for (int i = 1; i < binNum.size(); i++) {
            this.grayNum.add(binNum.get(i) == binNum.get(i - 1) ? 0 : 1);
        }
        for (Integer dig :
             this.grayNum) {
            System.out.print(" " + dig + " ");
        }
    }
}
