package com.jeff;

import java.math.BigInteger;

public class Util {
    public void getDec(String s) {
        BigInteger b = new BigInteger(s, 2);
        System.out.println(b.toString(10)+" --> "+b.toString(2).length());
    }

    public void getHexa(String s) {
        BigInteger b = new BigInteger(s, 2);
        System.out.print(b.toString(16)+"   ");
    }
}
