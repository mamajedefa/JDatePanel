/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.model;

import com.att.fk9424.jdatepanel.listeners.DateListener;
import com.att.fk9424.jdatepanel.events.DateEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *JDateDialog class is an extended class of JDialog to display a date table for
 * user to select appropriate date. the date format is ddMMMyyyy e.g 27May2013
 * 
 * @author fk9424
 */
public class JDateDialog extends JDialog implements WindowFocusListener {
    private ResourceBundle labels = ResourceBundle.getBundle("com.att.fk9424.jdatepanel.properties/labels", Locale.getDefault());
    private ArrayList<DateListener> dateListeners = new ArrayList<DateListener>();
    private CustomDate aDate = null;
    
    public JDateDialog(Window parent, final DateAction theButton){
        super(parent);
        init(theButton);
    }
    /**
     * 
     * @param theButton 
     */
    private void init(final DateAction theButton) {
        this.setUndecorated(true);
        JPanel panel = new JPanel(new BorderLayout());
        final DateTableModel tableModel = new DateTableModel();
        final JDateTable tableDate = new JDateTable(tableModel);
        tableModel.setData(new Date());
        panel.add(tableDate.getTableHeader(), BorderLayout.NORTH);
        panel.add(tableDate, BorderLayout.CENTER);
        this.getContentPane().add(createDateActionPanel(tableModel), BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        JPanel mainPanel = (JPanel)this.getContentPane();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setSize(this.getContentPane().getPreferredSize());
        this.addWindowFocusListener(this);
        tableDate.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent event){
                mouseReleased(event);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent event) {
                int rowIndex = tableDate.rowAtPoint(event.getPoint());
                int colIndex = tableDate.columnAtPoint(event.getPoint());               
                aDate = (CustomDate)tableDate.getModel().getValueAt(rowIndex, colIndex);
                theButton.dateChanged(aDate);
                windowLostFocus(null);                            
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tableDate.setDefaultRenderer(String.class, centerRenderer);
    }
    public void addDateListener(DateListener dl){
        this.dateListeners.add(dl);
    }
    public void fireDateChanged(CustomDate aDate){
        DateEvent event = new DateEvent(this, aDate);
        Iterator<DateListener> listeners = dateListeners.iterator();
        while(listeners.hasNext()) {
            ((DateListener) listeners.next()).updateDate(event);
        }
    }
    /**
     * createDateActionPanel
     * @param tableModel a DateTableModel to be use for all date calculation
     * @return a JPanel which would have 4 buttons for previous year, month, 
     * next year, month and a JLabel showing the date selected
     */
    private JPanel createDateActionPanel(final DateTableModel tableModel){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        
        final JLabel labelDate = new JLabel(tableModel.getDate());
        JButton prevYear = new JButton(labels.getString("PREVY"));
        prevYear.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                labelDate.setText(tableModel.getPreviousYear());
            }
            
        });
        JButton prevMonth = new JButton(labels.getString("PREVM"));
        prevMonth.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                labelDate.setText(tableModel.getPreviousMonth());
            }
            
        });
        JButton nextYear = new JButton(labels.getString("NEXTY"));
        nextYear.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                labelDate.setText(tableModel.getNextYear());
            }
            
        });
        JButton nextMonth = new JButton(labels.getString("NEXTM"));
        nextMonth.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                labelDate.setText(tableModel.getNextMonth());
            }
            
        });
        /*
         * here we use a custom button to set it enable or disable
         * depending on the month shown on the dialog. if current month then
         * today button is disabled otherwise enabled.
         */
        TodayButton today = new TodayButton(tableModel, labelDate);
        tableModel.addTableModelListener(today);

        JButton close = new JButton(labels.getString("CLOSE"));
        close.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                windowLostFocus(null);
            }
            
        });
        panel.add(Box.createHorizontalGlue());
        panel.add(prevYear);
        panel.add(prevMonth);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(labelDate);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(nextMonth);
        panel.add(nextYear);
        panel.add(today);
        panel.add(Box.createHorizontalGlue());
        panel.add(close);
       
        return panel;
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {}

    @Override
    public void windowLostFocus(WindowEvent e) {
        fireDateChanged(aDate);
        this.setVisible(false);
        this.dispose();
    }    
 }