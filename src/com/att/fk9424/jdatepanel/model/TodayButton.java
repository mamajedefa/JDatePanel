/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author fk9424
 */
public class TodayButton extends JButton implements TableModelListener {
    private ResourceBundle labels = ResourceBundle.getBundle("com.att.fk9424.jdatepanel.properties/labels", Locale.getDefault());
    
    public TodayButton(final DateTableModel tableModel, final JLabel labelDate){
        this.setText(labels.getString("TODAY"));
        this.setEnabled(false);
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                labelDate.setText(tableModel.getToday());
            }
            
        });
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        DateTableModel tModel = (DateTableModel)e.getSource();
        this.setEnabled(tModel.isTodayInTable()? false : true);
    }
}