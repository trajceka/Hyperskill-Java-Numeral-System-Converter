package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isOk = true;
        String sR;
        if (scanner.hasNext()) {
            sR = scanner.nextLine();
        } else {
            sR = "";
            isOk = false;
        }
        String[] number;
        if (scanner.hasNext() && isOk) {
            number = scanner.nextLine().split("\\.");
        } else {
            number = new String[] {""};
            isOk = false;
        }
        String sM;
        if (scanner.hasNext() && isOk) {
            sM = scanner.nextLine();
        } else {
            sM = "";
            isOk = false;
        }
        String str = "";
        if (isOk && sR.matches("\\d+") && sM.matches("\\d+")) {
            int r = Integer.parseInt(sR);
            int m = Integer.parseInt(sM);
            if (r > 0 && r < 37 && m > 0 && m < 37 && isOk) {
                str += con(r, number[0], m);
                if (number.length > 1 && isOk) {
                    if (isOk) {
                        str += "." + con2(r, number[1], m);
                    } else {
                        isOk = false;
                    }
                }
            } else {
                isOk = false;
            }
        } else {
            isOk = false;
        }
        if (!isOk) {
            str = "error";
        }
        System.out.println(str);
    }
    public static String con(int radix, String num, int destradix) {
        String prefix = "";
        StringBuilder strbuld = new StringBuilder();
        switch (destradix) {
            case 2:
                prefix = "0b";
                break;
            case 8:
                prefix = "0";
                break;
            case 16:
                prefix = "0x";
                break;
            default:
                break;
        }
        if (radix > 1 && destradix > 1) {
            return Integer.toString(Integer.parseInt(num, radix), destradix);
        } else if (radix == 1) {
            int a = num.length();
            return Integer.toString(a, destradix);
        } else if (destradix == 1) {
            int no = Integer.parseInt(num);
            int a = 0;
            while (no-- > 0) {
                strbuld.append("1");
            }
            //System.out.println();
            return strbuld.toString();
        }
        return strbuld.toString();
    }
    public static String con2(int radix, String num, int destradix) {
        double sum = 0;
        int numlen = num.length();
        if (radix != 10) {
            String[] strDigit = num.split("");
            int powradix = radix;
            for (int i = 0; i < strDigit.length; i++) {
                sum += Integer.parseInt(strDigit[i], radix) * 1.0 / powradix;
                powradix *= radix;
            }
        } else {
            num = "0." + num;
            sum = Double.parseDouble(num);
        }
        StringBuilder strbuld = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            double f = sum * destradix;
            strbuld.append(Integer.toString((int) f, destradix));
            sum = f - (int) f;
        }
        return strbuld.toString();
    }
}
