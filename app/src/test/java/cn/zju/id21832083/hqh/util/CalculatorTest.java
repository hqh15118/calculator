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

}