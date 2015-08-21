package com.att.fk9424.jdatepanel.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author fk9424
 */
public class CustomDate {
    /**
     * instance variables
     */
    private Calendar cal;
    private Date aDate;
    /**
     * constructor 
     * @param aDate a Date to be stored 
     */
    public CustomDate(Date aDate){
        this.aDate = aDate;
        cal = Calendar.getInstance();
        cal.setTime(this.aDate);
    }
    /**
     * getDate()
     * @return the date stored
     */
    public Date getDate(){
        return this.aDate;
    }
    
    public String getDateAsString(){
        SimpleDateFormat formatPattern = new SimpleDateFormat("ddMMMyyyy");
        return formatPattern.format(aDate);
    }
    
    public static String getTodayStringDate(){
        SimpleDateFormat formatPattern = new SimpleDateFormat("ddMMMyyyy");
        return formatPattern.format(new Date()); 
    }
    /**
     * override toString()
     * @return a String that represent the number of day in the month of 
     * this date
     */
    @Override
    public String toString(){
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }
}
