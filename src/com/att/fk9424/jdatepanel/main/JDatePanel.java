/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.main;

import com.att.fk9424.jdatepanel.view.JDateButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * JDate panel is taking a JTextField with a JDateButton glue to it on the right
 * hand side. So when you user clic on the button it open the date selection
 * dialog and user selection from that dialog would be set into textfield
 * 
 * @input a JFrame or JDialog to be attached to.
 * @author fk9424
 */
public class JDatePanel extends JPanel {
    private JDateButton button;
    private JTextField textField;
    /*
     * constructor from a JFrame
     */
    public JDatePanel(JFrame frame){
        button = new JDateButton(frame);
        this.init();
    }
    /*
     * constructor from a JDialog
     */
    public JDatePanel(JDialog dialog){
        button = new JDateButton(dialog);
        this.init();
    }
    /*
     * provide access to the JTextField so user can set preference on size,
     * font etc..
     */
    public JTextField getField(){
        return textField;
    }
    /*
     * create TextField and associate both the JDateButton with JTextField
     */
    private void init(){
        textField = new JTextField();
        button.addFieldListener(textField);
        
        textField.setPreferredSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(textField);
        this.add(button);
        this.add(Box.createHorizontalGlue());
        
    }
    /*
     * for testing purpose. creating the panel into a basic frame
     */
//    public static void main(String[] args){
//        JFrame frame = new JFrame("testing date button");
//        JDatePanel pDate = new JDatePanel(frame);
//        pDate.getField().setPreferredSize(new Dimension(100,25));
//        frame.setLayout(new FlowLayout());
//        frame.add(pDate);
//        frame.setSize(new Dimension(300,200));
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    } 
}