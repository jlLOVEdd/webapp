package test;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 15:50 2018/9/14
 */
public enum EnumDemo {
    PAY(1), SS(2), AMOUNT(2);

    private int code;

    private EnumDemo(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
