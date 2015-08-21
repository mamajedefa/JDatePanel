/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.view;

import com.att.fk9424.jdatepanel.model.CustomDate;
import com.att.fk9424.jdatepanel.model.DateAction;
import com.att.fk9424.jdatepanel.model.JDateDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * JDateButton is a simple JButton that interact with JDateDialog for user
 * to select a date from a table calendar. the date selected will be set as
 * title of the button. see main method at the bottom for testing
 * @author fk9424
 */
public class JDateButton extends JButton implements ActionListener, DateAction {
    private JDateDialog dateWin;
    private CustomDate aDate = new CustomDate(new Date());
    private ArrayList<JTextField> fieldListeners;
    private ResourceBundle labels = ResourceBundle.getBundle("com.att.fk9424.jdatepanel.properties/labels", Locale.getDefault());
    /**
     * JDateButton
     * construct a JButton with no frame and a white background. Associate it
     * to JDateDialog so it will show the calendar dialog once user click on it
     * @param frame 
     */
    public JDateButton(JFrame frame){
        super();
        this.dateWin = new JDateDialog(frame,this);
        this.init();
//        this.setBorderPainted(false);
//        this.setBackground(Color.WHITE);
//        this.setText(CustomDate.getTodayStringDate());
    }
    public JDateButton(JDialog dialog){
        super();
        this.dateWin = new JDateDialog(dialog,this);
        init();
    }
    private void init(){
        this.setText(labels.getString("DATE"));        
        this.addActionListener(this);
        this.fieldListeners = new ArrayList<JTextField>();
    }
    /**
     * addFieldListener register any FieldListener that need to receive event
     * notification about a date selected from the JDateWindow table
     * @param l a FieldListener to be added (which is a JTextField)
     */
    public void addFieldListener(JTextField field){
        this.fieldListeners.add(field);
    }
    /**
     * fireDateChanged
     * this is triggered by JDateDialog that call it as soon as user has click
     * on a date from JDateDialog calendar. the date selected is then set as 
     * the title of the button
     * @param aDate 
     */
    @Override
    public void fireDateChanged(CustomDate aDate){
        Iterator<JTextField> listeners = fieldListeners.iterator();
        while(listeners.hasNext()){
            ((JTextField)listeners.next()).setText(aDate.getDateAsString());
        }
//        this.aDate = aDate;
//        this.setText(aDate.getDateAsString());
    }
    /**
     * actionPerformed
     * this is the action from the button, so it does call JDateDialog and show
     * the table calendar for user to choose a particular date.
     * @param e 
     */
    
    public Date getCustomDate(){
        return aDate.getDate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton theButton = (JButton)e.getSource();
        int xPos = theButton.getLocationOnScreen().x;
        int yPos = theButton.getLocationOnScreen().y;// + theButton.getHeight();
        dateWin.setLocation(xPos, yPos);
        dateWin.setVisible(true); 
    }
//    public static void main(String[] args){
//        JFrame frame = new JFrame("testing date button");
//        JDateButton bDate = new JDateButton(frame);
//        bDate.setText("10Aug2015");
//        JDateField tDate = new JDateField(frame);
//        tDate.setPreferredSize(new Dimension(70, 30));
//        tDate.setEditable(false);
//        frame.setLayout(new FlowLayout());
//        frame.add(bDate);
//        frame.add(tDate);
//        frame.setSize(new Dimension(300,200));
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }

}