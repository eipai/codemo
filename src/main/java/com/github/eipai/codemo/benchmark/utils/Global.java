package com.github.eipai.codemo.benchmark.utils;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.github.eipai.codemo.benchmark.bean.TranException;

public abstract class Global {
    public static abstract class Decimal {
        public static final BigDecimal _0 = new BigDecimal("0");
        public static final BigDecimal _1 = new BigDecimal("1");
        public static final BigDecimal _10000 = new BigDecimal("10000");
    }

    public static abstract class Symbol {
        public static final String PIPE = "|";
        public static final String COMMA = ",";
        public static final String COLON = ":";
        public static final String SEMICOLON = ";";
        public static final String SPACE = " ";
    }

    public static abstract class Assert {
        public static void notNull(Object obj) {
            if (null == obj) {
                throw new RuntimeException("Object is null.");
            }
            if (obj instanceof String && StringUtils.isBlank((String) obj)) {
                throw new RuntimeException("String is blank.");
            }
        }

        public static void notNull(Object obj, String message) {
            if (null == obj) {
                throw new RuntimeException(message);
            }
            if (obj instanceof String && StringUtils.isBlank((String) obj)) {
                throw new RuntimeException(message);
            }
        }
    }

    public static abstract class Utils {
        public static boolean checkAmount(BigDecimal amount) {
            if (null == amount) return false;
            if (amount.compareTo(Global.Decimal._0) < 1) return false;
            return true;
        }

        public static Integer parseInt(String str, boolean wrongException) {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                if (wrongException) throw e;
                return null;
            }
        }

        public static Long parseLong(String str, boolean wrongException) {
            try {
                return Long.parseLong(str);
            } catch (Exception e) {
                if (wrongException) throw e;
                return null;
            }
        }

        public static BigDecimal parseBigDecimal(String str) {
            try {
                return new BigDecimal(str);
            } catch (Exception e) {
                return null;
            }
        }

        public static BigDecimal toNegative(BigDecimal arg) {
            if (null == arg) return null;
            return Global.Decimal._0.subtract(arg);
        }

        public static boolean equalsZero(BigDecimal arg) {
            if (null == arg) throw new TranException("argment cannot be empty");
            return Global.Decimal._0.compareTo(arg) == 0;
        }

        public static boolean isLessThanZero(BigDecimal arg) {
            if (null == arg) throw new TranException("argment cannot be empty");
            return Global.Decimal._0.compareTo(arg) == 1;
        }

        public static boolean isGreaterThanZero(BigDecimal arg) {
            if (null == arg) throw new TranException("argment cannot be empty");
            return Global.Decimal._0.compareTo(arg) == -1;
        }

    }
}
