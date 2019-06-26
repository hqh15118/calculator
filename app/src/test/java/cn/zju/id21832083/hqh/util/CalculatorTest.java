package cn.zju.id21832083.hqh.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
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

    @Test
    public void yihuoTest(){
        int i = 0;
        i ^= 1;
        System.out.println(i);
        i ^= 1;
        System.out.println(i);
    }

    @Test
    public void encodeAndDecodeTest(){
        String rawText = "orz。。。。ggg";
        Base64 base64 = new Base64();
        byte[] bytes = base64.decode(rawText);
        String hex = Hex.encodeHexString(bytes);
        System.out.println(hex);
        byte[] bytes1 = new byte[]{0x11};
        System.out.println(Hex.encodeHexString(bytes1));
    }

}