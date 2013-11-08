/*
 * 创建日期 2005-8-22
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.util;

import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * 
 *         TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class DateFormatTime
{
    
    public static Calendar toCalendar(String str)
        throws ParseException
    {
        try
        {
            if (str == null || "".equals(str.trim()))
                return null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date dd1 = df.parse(str);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd1);
            
            return cal;
        }
        catch (ParseException e)
        {
            throw new ParseException("You should pass the String like this:2001-4-5 21:11:11", 1);
        }
    }
    
    public static String today(int flag)
    {
        GregorianCalendar now = new GregorianCalendar();
        return datetoString(now, flag);
    }
    
    public static Timestamp today()
    {
        GregorianCalendar now = new GregorianCalendar();
        return new Timestamp((now.getTime()).getTime());
    }
    
    public static Timestamp getTimestamp(String str, int flag)
    {
        try
        {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat df = null;
            java.util.Date dd1 = null;
            switch (flag)
            {
                case 1:
                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    dd1 = df.parse(str);
                    cal.setTime(dd1);
                    break;
                case 2:
                    df = new SimpleDateFormat("yyyy-MM-dd");
                    dd1 = df.parse(str);
                    cal.setTime(dd1);
                    break;
            }
            return new Timestamp((cal.getTime()).getTime());
        }
        catch (ParseException e)
        {
            System.out.println("格式化 错误 DateFormatTime toCalendar(String str)");
        }
        return null;
        
    }
    
    public static String datetoString(GregorianCalendar ts, int flag)
    {
        SimpleDateFormat sdf = null;
        if (flag == 1)
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (flag == 2)
            sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        if (flag == 3)
            sdf = new SimpleDateFormat("yyyyMMddHHmm");
        if (flag == 4)
            sdf = new SimpleDateFormat("yyyyMMddHH");
        if (flag == 5)
            sdf = new SimpleDateFormat("yyyyMMdd");
        if (flag == 6)
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (flag == 7)
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (flag == 8)
            sdf = new SimpleDateFormat("yyMMddHHmm");
        if (flag == 9)
            sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        return sdf.format(ts.getTime());
    }
    
    public static String DatetoString(GregorianCalendar ts)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        return sdf.format(ts.getTime());
    }
    
    public static String datetoString(Calendar ts)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        return sdf.format(ts.getTime());
    }
    
    public static String datetoString(Calendar ts, int flag)
    {
        SimpleDateFormat sdf = null;
        if (flag == 1)
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        else if (flag == 2)
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        return sdf.format(ts.getTime());
    }
    
    // 计算两个日期之间的天使
    
    public static int getDays(GregorianCalendar g1, GregorianCalendar g2)
    {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        
        if (g2.after(g1))
        {
            gc2 = (GregorianCalendar)g2.clone();
            gc1 = (GregorianCalendar)g1.clone();
        }
        else
        {
            gc2 = (GregorianCalendar)g1.clone();
            gc1 = (GregorianCalendar)g2.clone();
        }
        
        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        
        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);
        
        while (gc1.before(gc2))
        {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }
    
    public static int getMonths(GregorianCalendar g1, GregorianCalendar g2)
    {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        
        if (g2.after(g1))
        {
            gc2 = (GregorianCalendar)g2.clone();
            gc1 = (GregorianCalendar)g1.clone();
        }
        else
        {
            gc2 = (GregorianCalendar)g1.clone();
            gc1 = (GregorianCalendar)g2.clone();
        }
        
        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        gc1.clear(Calendar.DATE);
        
        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);
        gc2.clear(Calendar.DATE);
        
        while (gc1.before(gc2))
        {
            gc1.add(Calendar.MONTH, 1);
            elapsed++;
        }
        return elapsed;
    }
    
    public static int getHours(GregorianCalendar g1, GregorianCalendar g2)
    {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        
        if (g2.after(g1))
        {
            gc2 = (GregorianCalendar)g2.clone();
            gc1 = (GregorianCalendar)g1.clone();
        }
        else
        {
            gc2 = (GregorianCalendar)g1.clone();
            gc1 = (GregorianCalendar)g2.clone();
        }
        
        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        
        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        
        while (gc1.before(gc2))
        {
            
            gc1.add(Calendar.HOUR_OF_DAY, 1);
            elapsed++;
        }
        return elapsed;
    }
    
    public static int getMinutes(GregorianCalendar g1, GregorianCalendar g2)
    {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        
        if (g2.after(g1))
        {
            gc2 = (GregorianCalendar)g2.clone();
            gc1 = (GregorianCalendar)g1.clone();
        }
        else
        {
            gc2 = (GregorianCalendar)g1.clone();
            gc1 = (GregorianCalendar)g2.clone();
        }
        
        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        
        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        
        while (gc1.before(gc2))
        {
            gc1.add(Calendar.MINUTE, 1);
            elapsed++;
        }
        return elapsed;
    }
    
    public static int getbetweendays(GregorianCalendar gc1, GregorianCalendar gc2)
    {
        
        int days = getDays(gc1, gc2);
        return days;
        
    }
    
    public static int getbetweenhours(GregorianCalendar gc1, GregorianCalendar gc2)
    {
        
        int hours = getHours(gc1, gc2);
        return hours;
        
    }
    
    public static int getbetweenminutes(GregorianCalendar gc1, GregorianCalendar gc2)
    {
        
        int minutes = getMinutes(gc1, gc2);
        return minutes;
        
    }
}
