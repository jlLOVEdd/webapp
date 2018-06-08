package test;

import java.util.*;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 16:38 2018/6/7
 */
public class ListTest {
    public static void main(String[] args) {
       /* List list = new LinkedList();
        list.add("d");
        list.add("e");
        list.add("c");
        list.add("s");
        list.add("a");
        System.out.println(list.size());
        list.forEach(n->{
            System.out.println(n);
        });
        Set set = new HashSet();
        set.add("b");
        set.add("c");
        set.add("s");
        set.add("d");
        set.add("e");
        set.forEach(n->{
            System.out.println(n);
        });
        */
        List<String> strings = Arrays.asList("6", "1", "3", "1","2");

        Collections.sort(strings);//sort方法在这里

        for (String string : strings) {

            System.out.println(string);
        }
    }
}
