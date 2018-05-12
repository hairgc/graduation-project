package com.github.mahui53541.graduation.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/12 17:50
 * @Version: 1.0
 */
@Component
public class DateConverterConfig implements Converter<String, Date> {
    private static final List<String> formarts = new ArrayList<>(4);
    static{
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String source) {
        if(StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            if(source.contains("-")) {
            if(source.matches("^\\d{4}-\\d{1,2}$")){
                return parseDate(source, formarts.get(0));
            }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
                return parseDate(source, formarts.get(1));
            }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
                return parseDate(source, formarts.get(2));
            }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
                return parseDate(source, formarts.get(3));
            }else {
                throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
            }
        }else if(source.matches("^\\d+$")) {
            Long lDate = new Long(source);
            return new Date(lDate);
        }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        return null;
    }

    /**
     * 格式化日期
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    public  Date parseDate(String dateStr, String format) {
        Date date=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {

        }
        return date;
    }
}
