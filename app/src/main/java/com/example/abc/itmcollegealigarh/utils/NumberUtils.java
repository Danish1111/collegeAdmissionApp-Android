package com.example.abc.itmcollegealigarh.utils;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {
    public static String getCommaSeparated(int value) {
        DecimalFormat myFormatter = new DecimalFormat("#,##,###");
        return myFormatter.format(value);
    }

    public static String getCommaSeparated(float value) {
//        DecimalFormat myFormatter = new DecimalFormat("#,##,###");
//        return myFormatter.format(value);

        return getCommaSeparated(String.valueOf(value));
    }

    public static String getCommaSeparated(String value) {
        try {
            if (Utils.isValidText(value)) {
                DecimalFormat myFormatter = new DecimalFormat("#,##,###");
//            DecimalFormat myFormatter1 = new DecimalFormat("#,##,###");
                String values[] = value.split("\\.");
                if (values.length > 1)
                    return myFormatter.format(Double.parseDouble(values[0])) + (Double.parseDouble(values[1]) != 0 ? "." + values[1] : "");
                else
                    return myFormatter.format(Double.parseDouble(value));
            } else {
                return value;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    public static boolean isNumericDecimal(String text) {
        final String PATTERN = "\\d+(?:\\.\\d+)?";
        Pattern pattern_ = Pattern.compile(PATTERN);
        Matcher matcher = pattern_.matcher(text);
        return matcher.matches();
    }

    public static String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }


    public static <T extends Number> String format(String number) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (Integer.class.isAssignableFrom(number.getClass()))
            return number;

        return df.format(number);
    }
}
