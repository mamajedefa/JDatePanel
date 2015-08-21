/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.model;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * JDateTable is an extension of JTable couple with <DateTableModel> to provide
 * a nice table for calendar display. see also <JDateField> for further info
 * @author fk9424
 */
public class JDateTable extends JTable {
    private DateTableModel tableModel;
    
    public JDateTable(DateTableModel tableModel){
        super(tableModel);
        this.tableModel = tableModel;
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setRowSelectionAllowed(false);
        this.getTableHeader().setReorderingAllowed(false);   
    }
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        c.setBackground(tableModel.getBgColor(rowIndex, vColIndex));
        c.setForeground(tableModel.getFgColor(rowIndex, vColIndex));
        return c;
    }                             
}