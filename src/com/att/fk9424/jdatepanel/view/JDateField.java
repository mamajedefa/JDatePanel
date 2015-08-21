/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.view;

import com.att.fk9424.jdatepanel.model.CustomDate;
import com.att.fk9424.jdatepanel.model.DateAction;
import com.att.fk9424.jdatepanel.model.JDateDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<JTextField> fieldListeners;

    public JDateField(JFrame frame){
        this.dateWin = new JDateDialog(frame,this);
        init();
        addFieldListener(this);
    }
    public void init(){
        fieldListeners = new ArrayList<JTextField>();
        this.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    JTextField textField = (JTextField)e.getSource();
                    int xPos = textField.getLocationOnScreen().x;
                    int yPos = textField.getLocationOnScreen().y + textField.getHeight();
                    dateWin.setLocation(xPos, yPos);
                    dateWin.setVisible(true);         
                }
        });       
    }
    /**
     * addFieldListener register any FieldListener that need to receive event
     * notification about a date selected from the JDateWindow table
     * @param l a FieldListener to be added (which is a JTextField)
     */
    public void addFieldListener(JTextField field){
        this.fieldListeners.add(field);
    }
    
    @Override
    public void fireDateChanged(CustomDate aDate){
        Iterator<JTextField> listeners = fieldListeners.iterator();
        while(listeners.hasNext()){
            ((JTextField)listeners.next()).setText(aDate.getDateAsString());
        }
    }
}