package test;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 15:28 2018/6/8
 */
public class InnerClassTest {

    public  static class  InnerStaticClass{
        private String name = "static";
        public void getName(){
            System.out.println(this.name);
        }
    }

    public  static class  InnerClass{
        private String name = "no-static";
        public void getName(){
            System.out.println(this.name);
        }
    }


    public static void main(String[] args) {
        InnerClassTest.InnerStaticClass innerClass = new InnerClassTest.InnerStaticClass();
        innerClass.getName();
        InnerClassTest.InnerClass innerClass1 = new InnerClassTest.InnerClass();
        innerClass1.getName();
    }
}
