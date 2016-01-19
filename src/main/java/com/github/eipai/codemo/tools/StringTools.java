package com.github.eipai.codemo.tools;

public class StringTools {
    private static final String src_number = "0123456789";
    private static final String src_lower = "abcdefghijklmnopqrstuvwxyz";
    private static final String src_upper = src_lower.toUpperCase();
    private static final String src_hex_lower = "0123456789abcdef";
    private static final String src_hex_upper = src_hex_lower.toUpperCase();
    private static final String esc_char = "?";

    public static StringBuffer eval(StringBuffer s, CharSequence v) {
        s.setLength(0);
        s.append(v);
        return s;
    }

    /**
     * <p>
     * Checks if a String is non <code>null</code> and is not empty (
     * <code>length > 0</code>).
     * </p>
     * 
     * @param str
     *            the String to check
     * @return true if the String is non-null, and not length zero
     */
    public static boolean isNotEmpty(String str) {
        return (null != str && str.length() > 0);
    }

    /**
     * <p>
     * Checks if a (trimmed) String is <code>null</code> or empty.
     * </p>
     * 
     * @param str
     *            the String to check
     * @return <code>true</code> if the String is <code>null</code>, or length
     *         zero once trimmed
     */
    public static boolean isEmpty(String str) {
        return (null == str || str.length() == 0);
    }

    public static String getRandomAlphanumeric(int size) {
        StringBuffer r = new StringBuffer(size);
        String src = src_number + src_upper + src_lower;
        for (int i = 0; i < size; i++) {
            r.append(getRandomChar(src));
        }
        return r.toString();
    }

    public static String getRandom(int size) {
        StringBuffer r = new StringBuffer(size);
        String src = src_number + src_upper;
        for (int i = 0; i < size; i++) {
            r.append(getRandomChar(src));
        }
        return r.toString();
    }

    public static String getRandom(String format) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number + src_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(esc_char)) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getRandom(String format, char esc) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number + src_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(String.valueOf(esc))) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getRandomNum(int size) {
        StringBuffer r = new StringBuffer(size);
        String src = src_number;
        for (int i = 0; i < size; i++) {
            r.append(getRandomChar(src));
        }
        return r.toString();
    }

    public static String getRandomNum(String format) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(esc_char)) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getRandomNum(String format, char esc) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(String.valueOf(esc))) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getRandomHex(int size) {
        StringBuffer r = new StringBuffer(size);
        String src = src_hex_upper;
        for (int i = 0; i < size; i++) {
            r.append(getRandomChar(src));
        }
        return r.toString();
    }

    public static String getRandomHex(String format) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_hex_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(esc_char)) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getRandomHex(String format, char esc) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_hex_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(String.valueOf(esc))) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    private static final String getRandomChar(String src) {
        if (null == src || "".equals(src)) {
            return "";
        }
        return String.valueOf((src.charAt((int) (Math.random() * src.length()))));
    }

    public static String trim(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        boolean flag = true;
        int i = 0, j = 0, k = 0;
        while (i < str.length()) {
            if (!isSpace(str.charAt(i))) {
                flag = false;
                k = i;
            }
            if (flag) j++;
            i++;
        }
        return str.substring(j, k + 1);
    }

    public static boolean isSpace(char c) {
        return ('\u0020' == c || '\u3000' == c) ? true : false;
    }

    public static String cutSpace(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (!isSpace(str.charAt(i))) {
                temp.append(str.charAt(i));
            }
        }
        return temp.toString();
    }

    public static String Subbyte(String s, int length) throws Exception {
        byte[] bytes = s.getBytes("Unicode");
        int n = 0, i = 2;
        for (; i < bytes.length && n < length; i++) {
            if (i % 2 == 1) {
                n++;
            } else {
                if (bytes[i] != 0) n++;
            }
        }
        return new String(bytes, 0, i, "Unicode");
    }
}
