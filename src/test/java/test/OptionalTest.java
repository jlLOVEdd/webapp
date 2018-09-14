package test;

import java.util.Optional;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 14:58 2018/9/14
 */
public class OptionalTest {
    public static void main(String[] args) {
        Teather teather = null;

        Optional<Teather> s = Optional.ofNullable(teather);
        s.orElse(null);
        System.out.println(s.map(n -> n.getName())
                .map(nam -> nam.toUpperCase())
                .orElse("notAge"));

        Optional<Object> optional2 = Optional.empty();
        System.out.println(optional2);
    }


}
