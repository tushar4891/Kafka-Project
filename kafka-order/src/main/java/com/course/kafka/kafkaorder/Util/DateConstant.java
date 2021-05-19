package com.course.kafka.kafkaorder.Util;

import java.time.format.DateTimeFormatter;

public class DateConstant {
    
    static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateConstant.DATE_TIME_FORMAT);
}
