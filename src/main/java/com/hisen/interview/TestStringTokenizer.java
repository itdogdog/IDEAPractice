package com.hisen.interview;

import java.util.StringTokenizer;

/**
 * @Author hisenyuan
 * @Description 计算长度最大的子串长度(按空格分割), 自定义实现比较优
 * @Date 2019/3/26 20:53
 */
public class TestStringTokenizer {
    public static void main(String[] args) {
        String oriStr = "aa aa aaaa aaa a a a aa aa a";
        int times = 100000;
        // 自定义实现
        final long start1 = System.nanoTime();
        for (int i = 0; i < times; i++) {
            String splitStr = " ";
            findMaxLength(oriStr, splitStr);
        }
        final long end1 = System.nanoTime();

        // 利用 StringTokenizer
        final long start2 = System.nanoTime();
        for (int i = 0; i < times; i++) {
            findMaxLength4StringTokenizer(oriStr);
        }
        final long end2 = System.nanoTime();

        // 利用 Split
        final long start3 = System.nanoTime();
        for (int i = 0; i < times; i++) {
            String splitStr = " ";
            findMaxLength4Split(oriStr, splitStr);
        }
        final long end3 = System.nanoTime();

        System.out.println("use(nano): " + (end1 - start1) + ", by findMaxLength");
        System.out.println("use(nano): " + (end2 - start2) + ", by findMaxLength4StringTokenizer");
        System.out.println("use(nano): " + (end3 - start3) + ", by findMaxLength4Split");
//        output
//        use(nano): 15486049, by findMaxLength
//        use(nano): 66060102, by findMaxLength4StringTokenizer
//        use(nano): 80844275, by findMaxLength4Split
    }

    private static void findMaxLength4StringTokenizer(String oriStr) {
        int maxLength = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(oriStr);
        while (stringTokenizer.hasMoreElements()) {
            final int length = stringTokenizer.nextToken().length();
            maxLength = length > maxLength ? length : maxLength;
        }
//        System.out.println("max: " + maxLength);
    }

    private static void findMaxLength(String oriStr, String splitStr) {
        int index = 0;
        final int maxIndex = oriStr.lastIndexOf(splitStr);
        int maxLength = oriStr.length() - maxIndex;
        while (index < maxIndex) {
            final int currentIndex = oriStr.indexOf(splitStr, index);
            int currLength = currentIndex - index;
            maxLength = currLength > maxLength ? currLength : maxLength;
            index = currentIndex + splitStr.length();
        }
//        System.out.println("max: " + maxLength);
    }

    private static void findMaxLength4Split(String oriStr, String splitStr) {
        int maxLength = 0;
        final String[] strings = oriStr.split(splitStr);
        for (String str : strings) {
            final int length = str.length();
            maxLength = length > maxLength ? length : maxLength;
        }
//        System.out.println("max: " + maxLength);
    }
}
