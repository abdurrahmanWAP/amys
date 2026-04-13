package com.miniproject.amys.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToDate {

    public static LocalDate stringToDate(String date) {
        if (date != null) {
            LocalDate result = LocalDate
                    .parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return result;
        }

        return null;
    }
}
