package com.jeff;

public class Operations {

    public String getBinaryString(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            String temp = Integer.toBinaryString(c);
            if (temp.length() < 8) {
                StringBuilder tempSB = new StringBuilder();
                sb.append("0".repeat(8 - temp.length()));
                tempSB.append(temp);
                //print(c+" -> "+tempSB.toString());
                sb.append(tempSB.toString());
            } else {
                sb.append(temp);
            }
            //print(sb.toString());
        }
        int Olen = sb.length();
        if (Olen % 8 == 0) {
            sb.append("1");
        }

        int len = sb.length();
        sb.append("0".repeat(Math.max(0, 448 - len)));

        String lenbitMask = Integer.toBinaryString(Olen);
        //int tempLen = lenbitMask.length();

        sb.append("0".repeat(64 - lenbitMask.length()));
        sb.append(lenbitMask);

        return sb.toString();
    }

    public String doAddidtion(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        //if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
        //return"";
    }

    public String getK(int i) {
        if (i <= 19) {
            return "01011010100000100111100110011001";
        } else if (i <= 39) {
            return "01101110110110011110101110100001";
        } else if (i <= 59) {
            return "10001111000110111011110011011100";
        } else if (i <= 79) {
            return "11001010011000101100000111010110";
        }

        return "00000000000000000000000000000000";
    }

    public String f(int i, String B, String C, String D) {
        StringBuilder sb = new StringBuilder();

        if (i <= 19) {
            //(b and c) or ((not b) and d)
            String temp = doAnd(B, C);
            String notC = doNot(B);
            String temp1 = doAnd(notC, D);
            sb.append(doOr(temp, temp1));

        } else if (i <= 39) {
            //b xor c xor d
            sb.append(doXOR(B, C, D));
        } else if (i <= 59) {
            //(b and c) or (b and d) or (c and d)
            String temp = doAnd(B, C);
            String temp1 = doAnd(B, D);
            String temp2 = doAnd(C, D);
            String temp3 = doOr(temp, temp1);
            sb.append(doOr(temp3, temp2));
        } else if (i <= 79) {
            //b xor c xor d
            sb.append(doXOR(B, C, D));
        }

        return sb.toString();
    }

    public String doNot(String a) {
        //print(a);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int bit1 = (a.charAt(i) - '0') == 0 ? 1 : 0;
            sb.append(bit1);
        }
        //print(a+" Not "+sb.toString());
        return sb.toString();
    }

    public String doAnd(String a, String b) {
        if (a.length() != b.length()) {
            System.out.println("doAnd Length is not same");
        }
        //print(a+" "+b);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int bit1 = a.charAt(i) - '0';
            int bit2 = b.charAt(i) - '0';
            int anded = bit1&bit2;
            //print(bit1+" ^ "+bit2+" == "+xored);
            sb.append(anded);
        }
        //print(a+" & "+b+" = "+sb.toString());
        return sb.toString();
    }

    public String doOr(String a, String b) {
        if (a.length() != b.length()) {
            System.out.println("doOr Length is not same");
        }
        //print(a+" "+b);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int bit1 = a.charAt(i) - '0';
            int bit2 = b.charAt(i) - '0';
            int ored = bit1|bit2;
            //print(bit1+" ^ "+bit2+" == "+xored);
            sb.append(ored);
        }
        //print(a+" | "+b+" = "+sb.toString());
        return sb.toString();
    }

    public String doXOR(String a, String b, String c) {
        if (a.length() != b.length()) {
            System.out.println("doXOR Length is not same");
        }
        //print(a+" "+b+" "+c);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int bit1 = a.charAt(i) - '0';
            int bit2 = b.charAt(i) - '0';
            int bit3 = c.charAt(i) - '0';
            //int bit4 = d.charAt(i) - '0';
            int xored = bit1^bit2^bit3;
            //print(bit1+" ^ "+bit2+" == "+xored);
            sb.append(xored);
        }
        //print(a+" ^ "+b+" = "+sb.toString());
        return sb.toString();
    }

    public String doXOR(String a, String b, String c, String d) {
        if (a.length() != b.length()) {
            System.out.println("doXOR Length is not same");
        }
        //print(a+" "+b+" "+c+" "+d);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int bit1 = a.charAt(i) - '0';
            int bit2 = b.charAt(i) - '0';
            int bit3 = c.charAt(i) - '0';
            int bit4 = d.charAt(i) - '0';
            int xored = bit1^bit2^bit3^bit4;
            //print(bit1+" ^ "+bit2+" == "+xored);
            sb.append(xored);
        }
        //print(a+" ^ "+b+" "+c+" ^ "+d+" = "+sb.toString());
        return sb.toString();
    }

    public String doCircularShift(int k, String s) {

        k = k % s.length();
        return s.substring(k) + s.substring(0, k);
    }
}
