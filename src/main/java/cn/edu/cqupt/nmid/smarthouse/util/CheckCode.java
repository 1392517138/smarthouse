package cn.edu.cqupt.nmid.smarthouse.util;

import java.util.Random;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/10 4:44 PM
 */
public class CheckCode {
    private static char keys_mix[] = {'0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static char keys_number[] = {'0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9'};

    public static String getCheckCode(int codeLength) throws InterruptedException {
        char[] data = new char[codeLength];
        for (int i = 0; i < data.length; i++) {
            data[i] = getRandomChar();
            Thread.sleep(1);
        }
        return new String(data);
    }


    public static char getRandomChar() {
        return keys_number[getRandom(0, keys_number.length - 1)];
    }

    public static int getRandom(int begin, int end) {
        Random r = new Random(System.currentTimeMillis());
        return Math.abs(r.nextInt() % (end + 1) + begin);
    }
}
