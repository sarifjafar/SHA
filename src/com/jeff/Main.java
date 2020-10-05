package com.jeff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    private static String H0 = "01100111010001010010001100000001";
    private static String H1 = "11101111110011011010101110001001";
    private static String H2 = "10011000101110101101110011111110";
    private static String H3 = "00010000001100100101010001110110";
    private static String H4 = "11000011110100101110000111110000";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Operations op = new Operations();
        Constants constants = new Constants();
        Util util = new Util();

        String binaryString = op.getBinaryString(input);
        //util.print(binaryString);
        String[] chunks = new String[constants.chunkSize];

        int index = 0;

        for (int i = 0; i < binaryString.length(); i += 32) {
            chunks[index++] = binaryString.substring(i, i+32);
            System.out.println("Chunks "+(index-1)+" "+chunks[index-1]);
            //getDec(chunks[index-1]);
        }

        for (int i = 16; i < 80; i++) {
            String xored = op.doXOR(chunks[i-3], chunks[i-8], chunks[i-14], chunks[i-16]);
            //getDec(xored);
            chunks[i] = op.doCircularShift(1, xored);
            System.out.println("Chunks "+i+" "+chunks[i]);
        }

        String A = H0, B = H1, C = H2, D = H3, E = H4;

        for (int i = 0; i < 80; i++) {
            System.out.print("Iteration "+(i+1)+" : ");
            String circularShiftA = op.doCircularShift(5, A);
            String tempF = op.f(i, B, C, D);
            String k = op.getK(i);
            String Temp = op.doAddidtion(
                    op.doAddidtion(
                            op.doAddidtion(
                                    op.doAddidtion(circularShiftA, tempF),
                                    E),
                            k),
                    chunks[i]);
            E = D;
            D = C;
            C = op.doCircularShift(30, B);
            B = A;
            A = Temp;
            util.getHexa(A);
            util.getHexa(B);
            util.getHexa(C);
            util.getHexa(D);
            util.getHexa(E);
            System.out.println();
        }

        H0 = op.doAddidtion(H0, A);
        H1 = op.doAddidtion(H1, B);
        H2 = op.doAddidtion(H2, C);
        H3 = op.doAddidtion(H3, D);
        H4 = op.doAddidtion(H4, E);

        System.out.print("H0 : ");
        util.getHexa(H0);
        System.out.print("\nH1 : ");
        util.getHexa(H1);
        System.out.print("\nH2 : ");
        util.getHexa(H2);
        System.out.print("\nH3 : ");
        util.getHexa(H3);
        System.out.print("\nH4 : ");
        util.getHexa(H4);

        String finalHash = op.doCircularShift(128, H0) +
                op.doCircularShift(96, H1) +
                op.doCircularShift(64, H2) +
                op.doCircularShift(32, H3) +
                H4;
        BigInteger b = new BigInteger(finalHash, 2);
        System.out.println("\n"+input+" -> "+b.toString(16));
    }
}
