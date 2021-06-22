package com.wenance.challenge;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static long createTimestamp(LocalDateTime time ) {
        ZoneId zoneId = ZoneId.systemDefault();
        return time.atZone(zoneId).toInstant().toEpochMilli();
    }
    public static long createTimestamp() {
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.now().atZone(zoneId).toInstant().toEpochMilli();
    }
    public static String createTimestampString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return time.format(formatter);
    }
    public static LocalDateTime createTimestampLong(Long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }
    public static LocalDateTime createTimestampString(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(time, formatter);
    }
}
