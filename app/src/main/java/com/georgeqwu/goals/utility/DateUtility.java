package com.georgeqwu.goals.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by glookogeorge on 5/29/17.
 */

public class DateUtility {

    private static final ThreadLocal<DateFormat> DATE_AMERICAN_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            return sdf;
        }
    };
    private static final ThreadLocal<DateFormat> DATE_AMERICAN_FORMAT_WITH_TIME = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
            return sdf;
        }
    };

    public static String formatLocalDateAmerican(Date date) {
        return DATE_AMERICAN_FORMAT.get().format(date);
    }
    public static String formatLocalDateWithTimeAmerican(Date date) {
        return DATE_AMERICAN_FORMAT_WITH_TIME.get().format(date);
    }

    /**
     * Parses a date string in MM-dd-yyyy format
     * @param dateStr
     * @return
     */
    public static Date getDateFromAmericanString(String dateStr) {
        if (dateStr == null) {
            return null;
        }

        try {
            return DATE_AMERICAN_FORMAT.get().parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Parses a date string in MM-dd-yyyy format
     * @param dateStr
     * @return
     */
    public static Date getDateWithTimeFromAmericanString(String dateStr) {
        if (dateStr == null) {
            return null;
        }

        try {
            return DATE_AMERICAN_FORMAT_WITH_TIME.get().parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


}
