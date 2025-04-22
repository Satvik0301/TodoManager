package com.satvik.todomanager.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Helper {

    //parse sting into date
    public static Date parseDate(LocalDateTime localDateTime) throws ParseException {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }

}
