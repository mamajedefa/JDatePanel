/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.main;

import com.att.fk9424.jdatepanel.model.CustomDate;
import com.att.fk9424.jdatepanel.model.DateAction;
import com.att.fk9424.jdatepanel.model.JDateDialog;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;

/**
 * JDateButton is a simple JButton that interact with JDateDialog for user
 * to select a date from a table calendar. the date selected will be set as
 * title of the button. see main method at the bottom for testing
 * @author fk9424
 */
public class JDateButton extends JButton implements ActionListener, DateAction {
    private JDateDialog dateWin;
    private CustomDate aDate = new CustomDate(new Date());
    private ResourceBundle labels = ResourceBundle.getBundle("com.att.fk9424.jdatepanel.properties/labels", Locale.getDefault());
    /**
     * JDateButton
     * construct a JButton with no frame and a white background. Associate it
     * to JDateDialog so it will show the calendar dialog once user click on it
     * @param frame 
     */
    public JDateButton(Window win){
        super();
        this.dateWin = new JDateDialog(win,this);
        init();
    }
    private void init(){     
        this.addActionListener(this);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setBackground(Color.WHITE);
        this.setText(this.getText());
    }
    public JDateDialog getDialog(){
        return this.dateWin;
    }
    /**
     * fireDateChanged
     * this is triggered by JDateDialog that call it as soon as user has click
     * on a date from JDateDialog calendar. the date selected is then set as 
     * the title of the button
     * @param aDate 
     */
    @Override
    public void dateChanged(CustomDate aDate){
        this.setText(aDate.getDateAsStringYY());
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
        int yPos = theButton.getLocationOnScreen().y + theButton.getHeight();
        int maxWidth = dateWin.getWidth()+theButton.getLocationOnScreen().x;
        int maxHeight = dateWin.getHeight() + theButton.getLocationOnScreen().y + theButton.getHeight();
        if ( maxWidth > Toolkit.getDefaultToolkit().getScreenSize().width ){ // we are going to be off width
            xPos = xPos - dateWin.getWidth() + theButton.getWidth();
        }
        if ( maxHeight > Toolkit.getDefaultToolkit().getScreenSize().height){
            yPos = theButton.getLocationOnScreen().y - dateWin.getHeight();
        }      
        dateWin.setLocation(xPos, yPos);
        dateWin.setVisible(true); 
    }
//    public static void main(String[] args){
//        JFrame frame = new JFrame("testing date button");
//        JDateButton bDate = new JDateButton(frame);
//        CustomDate aDate = new CustomDate(new Date());
//        bDate.setText(aDate.getDateAsStringYY());
//        frame.setLayout(new FlowLayout());
//        frame.add(bDate);
//        frame.setSize(new Dimension(150,100));
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}