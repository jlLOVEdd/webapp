package test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 16:38 2018/6/7
 */
public class ListTest {
    public static void main(String[] args) {
    /*    System.out.println("-----------LinkedList------------");
        List list = new LinkedList();
        list.add("d");
        list.add("e");
        list.add("c");
        list.add("s");
        list.add("a");
        list.add(null);
        System.out.println(list.size());
        list.forEach(n -> {
            System.out.println(n);
        });
        System.out.println("-----------hashSet------------");
        Set set = new HashSet();
        set.add("b");
        set.add("c");
        set.add("s");
        set.add("d");
        set.add("e");
        set.add(null);
        set.forEach(n -> {
            System.out.println(n);
        });
        System.out.println("-----------ArrayList------------");
        List<String> strings = Arrays.asList("6", "1", "3", "1", "2");

        //Collections.sort(strings);//sort方法在这里

        for (String string : strings) {

            System.out.println(string);
        }
        System.out.println("-----------treeSet-------------");
        //hashSet 能存一个null值  but treeSet 不能存入null值
        TreeSet<String> treeSet = new TreeSet<String>();
        treeSet.add("b");
        treeSet.add("c");
        treeSet.add("11");
        treeSet.add("2");
        treeSet.add("a");
        treeSet.add("e");
        treeSet.add("d");
        for (String s : treeSet) {
            System.out.println(s);
        }

        System.out.println("------------treeMap-----------------");
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).compareTo(o2.toString());
            }
        });
        treeMap.put("a", "1");
        treeMap.put("1", "1");
        treeMap.put("11", "1");
        treeMap.put("2", "1");
        treeMap.put("c", "1");
        treeMap.put("e", "1");
        treeMap.put("d", "1");
        for (Map.Entry entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + "----------" + entry.getValue());
        }*/
        System.out.println("---------------------HashMap-----------------------");
        Map<String, Object> map = new HashMap<>();
        map.put("ss", "ss");
        map.put("ss", "ssss");
        System.out.println(map.get("ss"));
    }
}
