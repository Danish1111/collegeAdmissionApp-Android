package com.example.abc.itmcollegealigarh.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Utils {
    public static List<String> monthList = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

    public static void callNumber(Context context, String number) {
        try {
            if (isValidPhoneNumber(number)) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:+91" + number));
//                context.startActivity(intent);
                context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + number)));
            } else {
                Toast.makeText(context, "Invalid Number", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAddress(Context context, double lat, double lng) throws IOException {
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
        if (addresses.size() > 0) {
            String address = addresses.get(0).getSubLocality();
            if (address == null || address.isEmpty()) {
                address = addresses.get(0).getLocality();
            }
            return address;
        } else {
            return "searching";
        }
    }

    public static void launchGoogleMaps(Context context, String address) {
        try {
            Intent searchAddress = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
            context.startActivity(searchAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchGoogleNavigation(Context context, double mLat, double mLong) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + mLat + "," + mLong));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static String readFileFromAssets(Context context, String fileName) throws IOException {
        StringBuilder buf = new StringBuilder();
        InputStream json = context.getAssets().open(fileName);
        BufferedReader in =
                new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;

        while ((str = in.readLine()) != null) {
            buf.append(str);
        }
        in.close();
        return buf.toString();
    }

    public static boolean isValidText(String text) {
        return text != null && !TextUtils.isEmpty(text.trim()) && !text.toLowerCase().equals("null");
    }

    public static boolean isValidPhoneNumber(String number) {
        if (!isValidText(number) || number.length() != 10) {
            return false;
        }
        return (android.util.Patterns.PHONE.matcher(number).matches());
    }

    public static String getTrimmedDistance(String distance) {
        if (isValidText(distance)) {
            if (distance.indexOf(".") != -1) {
                distance = distance.substring(0, distance.lastIndexOf(".") + 3);
            }
            return distance + " KM";
        } else {
            return "";
        }
    }

    public static void rateApplication(Context context) {

        String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void androidShare(Context context) {

        String appPackageName = context.getPackageName();
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            String data = "https://play.google.com/store/apps/details?id=" + appPackageName;
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Hey there I'm using DalmiaMedicare\n");
            sendIntent.putExtra(Intent.EXTRA_TEXT, data);
            sendIntent.setType("text/plain");
            context.startActivity(sendIntent);
        } catch (Exception e) {
        }
    }

    public static void shareLab(Context context, String labId) {

//        String url = "http://52.66.104.87/lab/" + labId;
        String url = "https://www.dalmiamedicare.com/lab/" + labId;
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Explore this Lab\n");
            sendIntent.putExtra(Intent.EXTRA_TEXT, url);
            sendIntent.setType("text/plain");
            context.startActivity(sendIntent);
        } catch (Exception e) {
        }
    }

    public static String getFormattedDate(int date, int month, int year) {
        String dateString = "";

        dateString = dateString + date + "-" + monthList.get(month) + "-" + year;
        return dateString;
    }

    public static boolean isValidCityName(String name) {
        Pattern pattern_ = Pattern.compile("^[a-zA-Z][a-zA-Z\\s-]+[a-zA-Z]$");
        Matcher matcher = pattern_.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        Pattern pattern_ = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$");
        Matcher matcher = pattern_.matcher(name);
//        return matcher.matches();
        return isValidText(name);
    }

    public static String getNameMessage(String name) {
        Pattern pattern_ = Pattern.compile("[[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$]*$");
        Matcher matcher = pattern_.matcher(name);
        String message = null;
        if (matcher.find()) {
            int position = matcher.start();
            Log.i(" index " , position+"");
            final int length = name.length();
            switch (position) {
                case 0:
                    message = "name cannot start with " + name.charAt(0);
                    break;
                /*case length:

                    break;*/

                default:
                    message = name.charAt(position - 1) + " not allowed in name";
                    break;
            }
        }
        return message;
    }

    public static boolean isValidBloodGroup(String name) {
        Pattern pattern_ = Pattern.compile("^(A|B|AB|O)[+-]$");
        Matcher matcher = pattern_.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        return (isValidText(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static void openSettings(Activity context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static String getFormattedDate(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(new Date(timeStamp));
    }

    public static long getMillisecondsFromDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeInMilliseconds = 0;
        try {
            Date mDate = sdf.parse(date);
            timeInMilliseconds = mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }

    public static int getDeviceWidth(Activity activity) {

        int screenWidth;
        if (Build.VERSION.SDK_INT >= 17) {
            Point size = new Point();
            try {
                activity.getWindowManager().getDefaultDisplay().getRealSize(size);
                screenWidth = size.x;
            } catch (NoSuchMethodError e) {
                screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
            }

        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            screenWidth = metrics.widthPixels;
        }
        return screenWidth;
    }

 /*   public static void appLaunched(Context mContext) {
        int LAUNCHES_UNTIL_PROMPT = 5;//Min number of launches

        // Increment launch counter
        int launch_count = Integer.valueOf(SharedPreferencesUtil.getValue(Constants.LAUNCH_COUNT, "-1")) + 1;
        save(Constants.LAUNCH_COUNT, String.valueOf(launch_count));
        Logger.e("App Launch Counts " + launch_count);
        // Get date of first launch

        if (LAUNCHES_UNTIL_PROMPT == launch_count) {
//            rateUsAlertDialog(mContext, editor);
        }
    }*/

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
//        final String PASSWORD_PATTERN = "^[^<>'\\\\\"/;`:|~(){}[/] %]*$";

        final String PASSWORD_PATTERN = "[a-zA-Z0-9!@#$&*_\\-=+?]+$";

//        final String PASSWORD_PATTERN1 =
//                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static String passwordMessage(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "[a-zA-Z0-9!@#$*_\\-=+?]*$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        if (matcher.matches()) {
            return null;
        } else {
//            final String PATTERN = "[a-zA-Z0-9!@#$*_\\-=+?]*$";
            Pattern p = Pattern.compile(PASSWORD_PATTERN);  // insert your pattern here
            Matcher m = p.matcher(password);
            if (m.find()) {
                int position = m.start();
                String s = "" + password.charAt(position - 1);
                if (s.equals(" ")) {
                    return "SPACE not allowed in password";
                }
                return password.charAt(position - 1) + " not allowed in password";
            }
        }

        return null;
    }

    public static String getPasswordMessage(String password) {
        if (!isValidText(password))
            return "Enter valid password";

        if (password.length() > 20)
            return "Password exceeds 20 characters";

        if (password.length() < 6)
            return "Password less than 6 characters";

        return null;
    }

   /* public static boolean isValidName(String name){
        String regex = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}";
        Pattern pattern_ = Pattern.compile(regex);
        Matcher matcher = pattern_.matcher(name);
        return matcher.matches();
    }*/

    public static boolean isNumberOnly(String text) {
        final String PATTERN = "[0-9]*$";
        Pattern pattern_ = Pattern.compile(PATTERN);
        Matcher matcher = pattern_.matcher(text);
        return matcher.matches();
    }

    public static String extractOTP(String message) {
        String PATTERN = "(|^)\\d{4}";
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(message);
        String otp = null;
        if (m.find()) {
            otp = m.group(0);
        }
        return otp;
    }

    public static long dateToTimestamp(String date) {
        long timeStamp = 0;
        if (date != null && !date.isEmpty()) {
            String[] splitDob = date.split("-");
            if (splitDob.length == 3) {
                int day = Integer.valueOf(splitDob[0]);
                int month = Utils.monthList.indexOf(splitDob[1]);
                int year = Integer.valueOf(splitDob[2]);
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.DAY_OF_MONTH, day);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.YEAR, year);
                timeStamp = mCalendar.getTimeInMillis();
            }
        }
        return timeStamp;
    }

    public static String getReadableDate(String date) {
        String readableDate = null;
        if (date != null && !date.isEmpty()) {
            String[] actualDob =/*splitDob[0]*/date.split("-");
            if (actualDob.length == 3) {
                int day = Integer.valueOf(actualDob[0]);
                int month = Integer.valueOf(actualDob[1]) - 1;
                int year = Integer.valueOf(actualDob[2]);
                readableDate = Utils.getFormattedDate(day, month, year);
            }
        }
        return readableDate;
    }

    public static String getDuration(long start, long end) {
        long millis = end - start;
        return String.format(Locale.getDefault(), "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public static String getHours(long millis) {
        return String.format(Locale.getDefault(), "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public static String getWorkingHours(long millis) {
        return String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
    }

    public static String formatTime(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }


    public static String getTotalHours(long milliseconds) {
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        return String.valueOf(hours);
//        return String.format(Locale.getDefault(), "%02d", hours);
    }


    public static String getTotalMinutes(long milliseconds) {
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        return String.valueOf(minutes);
//        return String.format(Locale.getDefault(), "%02d",minutes);
    }


    public static String getTotalSeconds(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        return String.format(Locale.getDefault(), "%02d", seconds);
    }


    public static int getIntSeconds(long milliseconds) {
        return (int) (milliseconds / 1000) % 60;
    }

    public static int calculateNoOfColumns(Context context, int width) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / width);
        return noOfColumns;
    }


    public static boolean hasGPS(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    }

    public static String getBase64ToString(String base64) {
        String text;
        if (isBase64(base64)) {
            byte[] data = Base64.decode(base64, Base64.DEFAULT);
            if (Build.VERSION.SDK_INT < 19) {
                text = new String(data);
            } else {
                text = new String(data, StandardCharsets.UTF_8);
            }
            return text;
        } else {
            return base64;
        }
    }

    private static boolean isBase64(String text) {
        final String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return isValidText(text) && text.matches(regex);
    }

    public static String getStringToBase64(String text) {

        String base64 = "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            byte[] data = new byte[0];
            try {
                data = text.getBytes("UTF-8");
                base64 = Base64.encodeToString(data, Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            byte[] data = text.getBytes(StandardCharsets.UTF_8);
            base64 = Base64.encodeToString(data, Base64.DEFAULT);
        }
        return base64;
    }



}
