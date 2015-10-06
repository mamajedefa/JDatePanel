/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.fk9424.jdatepanel.events;

import com.att.fk9424.jdatepanel.model.CustomDate;
import java.util.EventObject;

/**
 *
 * @author fk9424
 */
public class DateEvent extends EventObject {
    private CustomDate cDate;
    
    public DateEvent(Object source, CustomDate newDate){
        super(source);
        this.cDate = newDate;
    }
    /**
     * 
     * @return 
     */
    public CustomDate getNewDate(){
        return this.cDate;
    }
}
