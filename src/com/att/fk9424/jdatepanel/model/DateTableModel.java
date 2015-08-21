/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.model;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fk9424
 */
public class DateTableModel extends AbstractTableModel {
    private ResourceBundle labels = ResourceBundle.getBundle("com.att.fk9424.jdatepanel.properties/labels", Locale.getDefault());    
    private String[] colNames ={
        labels.getString("SUN"),
        labels.getString("MON"),
        labels.getString("TUE"),
        labels.getString("WED"),
        labels.getString("THU"),
        labels.getString("FRI"),
        labels.getString("SAT"),
    };
    private ArrayList<ArrayList<CustomDate>> rowData;
    private SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
    private Date theDate;

    public DateTableModel(){
        rowData = new ArrayList<ArrayList<CustomDate>>();
    }
    
    @Override
    public Class<?> getColumnClass(int colIndex){
        return String.class;
    }
    @Override
    public int getRowCount() {
        return this.rowData.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int index){
        return this.colNames[index];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.rowData.get(rowIndex).get(columnIndex);
    }
    /**
     * setDate would set the table model data with date for all the day of the 
     * month. the table will always have 6 rows and 7 columns (days Sunday to
     * Saturday).
     * @param theDate a Date that is the reference for the table model data to
     * be set
     */
    public void setData(Date theDate){
        this.theDate = theDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(theDate);
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        if (firstDayOfMonth != Calendar.SUNDAY){
            calendar.add(Calendar.DATE, -(firstDayOfMonth-1));
        }         
        this.rowData = new ArrayList<ArrayList<CustomDate>>();
        ArrayList<CustomDate> weekDay = null;
        
        for (int i=0 ; i < 6 ; i++){ // each month has 6 rows
            weekDay = new ArrayList<CustomDate>();
            for (int j=Calendar.SUNDAY ; j<=Calendar.SATURDAY ; j++){ // each row has 7 days
                weekDay.add(new CustomDate(calendar.getTime()));
                calendar.add(Calendar.DATE, +1);
            }
            this.rowData.add(weekDay);
        }
        this.fireTableStructureChanged();
    }
    /**
     * getDate 
     * @return a String that represent the date stored in the table model
     * formated as formatter pattern
     */
    public String getDate(){
        return formatter.format(this.theDate);
    }
    /**
     * getToday() provide today's date and set the table at today's month.
     * @return a String that represent today's date.
     */
    public String getToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        this.theDate = calendar.getTime();
        this.setData(theDate);
        
        return formatter.format(theDate);        
    }
    /**
     * getPreviousYear()
     * @return a String that represent the previous year of the date stored
     * into the table model. it would also set the stored date with that new
     * value
     */
    public String getPreviousYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.theDate);
        calendar.add(Calendar.YEAR, -1);
        this.theDate = calendar.getTime();
        this.setData(theDate);
        
        return formatter.format(theDate);
        
    }
    /**
     * getPreviousMonth()
     * @return a String that represent the previous month from the date stored
     * in the table model. the date is formated using formatter's pattern
     */
    public String getPreviousMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.theDate);
        calendar.add(Calendar.MONTH, -1);
        this.theDate = calendar.getTime();
        this.setData(theDate);
        
        return formatter.format(theDate);
    }
    /**
     * getNextYear()
     * @return a String that represent the next year of the date stored
     * into the table model. it would also set the stored date with that new
     * value
     */
    public String getNextYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.theDate);
        calendar.add(Calendar.YEAR, +1);
        this.theDate = calendar.getTime();
        this.setData(theDate);
        
        return formatter.format(theDate);
        
    }
    /**
     * getNextMonth()
     * @return a String that represent the next month of the date stored
     * into the table model. it would also set the stored date with that new
     * value
     */
    public String getNextMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.theDate);
        calendar.add(Calendar.MONTH, +1);
        this.theDate = calendar.getTime();
        this.setData(theDate);
        
        return formatter.format(theDate);
        
    }
    /**
     * getBgColor provide a simple way to colored the table
     * @param colIndex the columnTable index
     * @return a Color that represent the cell background color. This color is
     * white for current month, light gray for other month and orange for today
     */
    public Color getBgColor(int rowIndex, int colIndex){
        Color theColor = Color.WHITE;
        Date date = ((CustomDate)this.getValueAt(rowIndex, colIndex)).getDate();
        if (formatter.format(date).equals(formatter.format(new Date()))){
            theColor = Color.ORANGE;
        }
        int thisMonth = getThisMonth();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (thisMonth != cal.get(Calendar.MONTH))
            theColor = Color.LIGHT_GRAY;
        return theColor;
    }
    /**
     * getFgColor provide the foreground color depending on row and column index
     * @param rowIndex a table row index
     * @param colIndex a table column index
     * @return a Color that would be for the given month either red or black
     * black for week day red for days that are not in the given month
     */
    public Color getFgColor(int rowIndex, int colIndex){
        Color theColor = Color.BLACK;
        Calendar cal = Calendar.getInstance();
        int thisMonth = getThisMonth();
        Date date = ((CustomDate)this.getValueAt(rowIndex, colIndex)).getDate();
        cal.setTime(date);
        if (thisMonth != cal.get(Calendar.MONTH)){
            theColor = Color.RED;
        }
        return theColor;
    }
    
    private int getThisMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);
        return cal.get(Calendar.MONTH);
    }
    /*
     * testing if today's date is on the table month display. this is used
     * to enabled or disabled the today's button ....
     */
    public boolean isTodayInTable(){
        for(int r = 0; r < this.getRowCount() ; r++){
            for(int c = 0; c < this.getColumnCount(); c++){
                Date date = ((CustomDate)this.getValueAt(r, c)).getDate();
                if (formatter.format(date).equals(formatter.format(new Date())))
                    return true;
            }
        }
        return false;
    }
}