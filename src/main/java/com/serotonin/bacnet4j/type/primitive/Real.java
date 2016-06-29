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
package com.serotonin.bacnet4j.type.primitive;

import org.json.JSONArray;
import org.json.JSONObject;

import com.serotonin.bacnet4j.base.BACnetUtils;
import com.serotonin.bacnet4j.util.sero.ByteQueue;

public class Real extends Primitive {
    private static final long serialVersionUID = -165304995181723832L;

    public static final byte TYPE_ID = 4;

    private float value;

    public Real(float value) {
        this.value = value;
    }

    public float floatValue() {
        return value;
    }

    //
    // Reading and writing
    //
    public Real(ByteQueue queue) {
        readTag(queue);
        value = Float.intBitsToFloat(BACnetUtils.popInt(queue));
    }

    @Override
    public void writeImpl(ByteQueue queue) {
        BACnetUtils.pushInt(queue, Float.floatToIntBits(value));
    }

    @Override
    protected long getLength() {
        return 4;
    }

    @Override
    protected byte getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + Float.floatToIntBits(value);
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
        final Real other = (Real) obj;
        if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return Float.toString(value);
    }
    
    public String toJsonString(){
    	return toJsonObject().toString();
    }
    
    public Object getValue(){
    	return value;
    }
    
    public JSONObject toJsonObject(){
    	JSONObject obj = new JSONObject();
    	obj.put(JSON_CAPSULE, value);
    	return obj;
    }
    
    @Override
    public void updateFromJson(String value){
    	this.value = Float.parseFloat(value);
    }
}
