/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2015 Infinite Automation Software. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * When signing a commercial license with Infinite Automation Software,
 * the following extension to GPL is made. A special exception to the GPL is
 * included to allow you to distribute a combined work that includes BAcnet4J
 * without being obliged to provide the source code for any proprietary components.
 *
 * See www.infiniteautomation.com for commercial license options.
 * 
 * @author Matthew Lohbihler
 */
package com.serotonin.bacnet4j.type.constructed;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.primitive.Time;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.bacnet4j.util.sero.ByteQueue;

public class TimeStamp extends BaseType {
    private static final long serialVersionUID = 728644269380254714L;

    public static final TimeStamp UNSPECIFIED_TIME = new TimeStamp(Time.UNSPECIFIED);
    public static final TimeStamp UNSPECIFIED_SEQUENCE = new TimeStamp(new UnsignedInteger(0));
    public static final TimeStamp UNSPECIFIED_DATETIME = new TimeStamp(DateTime.UNSPECIFIED);

    private final Choice choice;

    private static List<Class<? extends Encodable>> classes;
    static {
        classes = new ArrayList<Class<? extends Encodable>>();
        classes.add(Time.class);
        classes.add(UnsignedInteger.class);
        classes.add(DateTime.class);
    }

    public TimeStamp(Time time) {
        choice = new Choice(0, time);
    }

    public TimeStamp(UnsignedInteger sequenceNumber) {
        choice = new Choice(1, sequenceNumber);
    }

    public TimeStamp(DateTime dateTime) {
        choice = new Choice(2, dateTime);
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, choice);
    }

    public TimeStamp(ByteQueue queue) throws BACnetException {
        choice = new Choice(queue, classes);
    }

    public boolean isTime() {
        return choice.getContextId() == 0;
    }

    public Time getTime() {
        return (Time) choice.getDatum();
    }

    public boolean isSequenceNumber() {
        return choice.getContextId() == 1;
    }

    public UnsignedInteger getSequenceNumber() {
        return (UnsignedInteger) choice.getDatum();
    }

    public boolean isDateTime() {
        return choice.getContextId() == 2;
    }

    public DateTime getDateTime() {
        return (DateTime) choice.getDatum();
    }

    @Override
    public String toString() {
        return "TimeStamp [choice=" + choice + "]";
    }

    
    public String toJsonString(){
    	return toJsonObject().toString();
    }

    
    public JSONObject toJsonObject(){
    	if(isDateTime())
    		return getDateTime().toJsonObject();
    	else if(isTime())
    		return getTime().toJsonObject();
    	else if (isSequenceNumber())
    		return getSequenceNumber().toJsonObject();
    	else
    		return null;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((choice == null) ? 0 : choice.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TimeStamp other = (TimeStamp) obj;
        if (choice == null) {
            if (other.choice != null)
                return false;
        }
        else if (!choice.equals(other.choice))
            return false;
        return true;
    }

}
