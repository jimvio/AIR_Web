package utils;

import java.util.regex.Pattern;

/**
 * Created by jimvio on 2018/7/14.
 */
public class Test {
    public static void main(String[] args) {
        String s = "1001";
        Pattern pattern1 = Pattern.compile("[0-9]+");
        System.out.println(pattern1.matcher(s).matches());
    }
}
