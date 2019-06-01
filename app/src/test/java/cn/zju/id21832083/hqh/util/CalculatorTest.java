package cn.zju.id21832083.hqh.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/6/1.
 */
public class CalculatorTest {
    @Test
    public void conversion() throws Exception {

        String expression = "1+3.6+0/0";
        System.out.println(Calculator.conversion(expression));
    }

    @Test
    public void charTest(){
        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";
        String e = "E";
        String f = "F";
        System.out.println(String.valueOf(((int) a.charAt(0))));
        System.out.println(String.valueOf(((int) b.charAt(0))));
        System.out.println(String.valueOf(((int) c.charAt(0))));
        System.out.println(String.valueOf(((int) e.charAt(0))));
        System.out.println(String.valueOf(((int) f.charAt(0))));
        String eight = "8";
        System.out.println(String.valueOf(((int) eight.charAt(0))));
        String two = "2";
        System.out.println(String.valueOf(((int) two.charAt(0))));
    }

}