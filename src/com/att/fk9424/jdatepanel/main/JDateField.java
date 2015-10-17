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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * JDateField is an extension of JTextField with a JDialog showing a calendar.
 * JDateField attached a calendar window to the field for the user to select
 * the appropriate date. see <JDateDialog>
 * @author fk9424
 */
public final class JDateField extends JTextField implements DateAction {
    private JDateDialog dateWin;

    public JDateField(JFrame frame){
        this.dateWin = new JDateDialog(frame,this);
        init();
    }
    public JDateField(JDialog dialog){
        this.dateWin = new JDateDialog(dialog,this);
        init();
    }
    public void init(){
        this.setForeground(Color.LIGHT_GRAY);
        CustomDate tDate = new CustomDate(new Date());
        this.setText(tDate.getDateAsString());
        this.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    JTextField textField = (JTextField)e.getSource();
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                    int xPos = textField.getLocationOnScreen().x;
                    int yPos = textField.getLocationOnScreen().y + textField.getHeight();
                    int maxWidth = dateWin.getWidth()+textField.getLocationOnScreen().x;
                    int maxHeight = dateWin.getHeight() + textField.getLocationOnScreen().y + textField.getHeight();
                    if ( maxWidth > Toolkit.getDefaultToolkit().getScreenSize().width ){ // we are going to be off width
                        xPos = xPos - dateWin.getWidth() + textField.getWidth();
                    }
                    if ( maxHeight > Toolkit.getDefaultToolkit().getScreenSize().height){
                        yPos = textField.getLocationOnScreen().y - dateWin.getHeight();
                    }      
                    dateWin.setLocation(xPos, yPos);
                    dateWin.setVisible(true);         
                }
        });
    }    
    @Override
    public void dateChanged(CustomDate aDate){
        this.setText(aDate.getDateAsStringYY());
    }
}