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
package com.serotonin.bacnet4j.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.AccumulatorRecord;
import com.serotonin.bacnet4j.type.constructed.ActionList;
import com.serotonin.bacnet4j.type.constructed.AddressBinding;
import com.serotonin.bacnet4j.type.constructed.BACnetArray;
import com.serotonin.bacnet4j.type.constructed.CalendarEntry;
import com.serotonin.bacnet4j.type.constructed.ClientCov;
import com.serotonin.bacnet4j.type.constructed.CovSubscription;
import com.serotonin.bacnet4j.type.constructed.DailySchedule;
import com.serotonin.bacnet4j.type.constructed.DateRange;
import com.serotonin.bacnet4j.type.constructed.DateTime;
import com.serotonin.bacnet4j.type.constructed.Destination;
import com.serotonin.bacnet4j.type.constructed.DeviceObjectPropertyReference;
import com.serotonin.bacnet4j.type.constructed.DeviceObjectReference;
import com.serotonin.bacnet4j.type.constructed.EventLogRecord;
import com.serotonin.bacnet4j.type.constructed.EventTransitionBits;
import com.serotonin.bacnet4j.type.constructed.LimitEnable;
import com.serotonin.bacnet4j.type.constructed.LogMultipleRecord;
import com.serotonin.bacnet4j.type.constructed.LogRecord;
import com.serotonin.bacnet4j.type.constructed.ObjectPropertyReference;
import com.serotonin.bacnet4j.type.constructed.ObjectTypesSupported;
import com.serotonin.bacnet4j.type.constructed.Prescale;
import com.serotonin.bacnet4j.type.constructed.PriorityArray;
import com.serotonin.bacnet4j.type.constructed.ReadAccessResult;
import com.serotonin.bacnet4j.type.constructed.ReadAccessSpecification;
import com.serotonin.bacnet4j.type.constructed.Recipient;
import com.serotonin.bacnet4j.type.constructed.Scale;
import com.serotonin.bacnet4j.type.constructed.Sequence;
import com.serotonin.bacnet4j.type.constructed.ServicesSupported;
import com.serotonin.bacnet4j.type.constructed.SetpointReference;
import com.serotonin.bacnet4j.type.constructed.ShedLevel;
import com.serotonin.bacnet4j.type.constructed.SpecialEvent;
import com.serotonin.bacnet4j.type.constructed.StatusFlags;
import com.serotonin.bacnet4j.type.constructed.TimeStamp;
import com.serotonin.bacnet4j.type.constructed.VtSession;
import com.serotonin.bacnet4j.type.enumerated.Action;
import com.serotonin.bacnet4j.type.enumerated.BackupState;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.DeviceStatus;
import com.serotonin.bacnet4j.type.enumerated.DoorAlarmState;
import com.serotonin.bacnet4j.type.enumerated.DoorSecuredStatus;
import com.serotonin.bacnet4j.type.enumerated.DoorStatus;
import com.serotonin.bacnet4j.type.enumerated.DoorValue;
import com.serotonin.bacnet4j.type.enumerated.EngineeringUnits;
import com.serotonin.bacnet4j.type.enumerated.ErrorClass;
import com.serotonin.bacnet4j.type.enumerated.ErrorCode;
import com.serotonin.bacnet4j.type.enumerated.EventState;
import com.serotonin.bacnet4j.type.enumerated.EventType;
import com.serotonin.bacnet4j.type.enumerated.FileAccessMethod;
import com.serotonin.bacnet4j.type.enumerated.LifeSafetyMode;
import com.serotonin.bacnet4j.type.enumerated.LifeSafetyOperation;
import com.serotonin.bacnet4j.type.enumerated.LifeSafetyState;
import com.serotonin.bacnet4j.type.enumerated.LockStatus;
import com.serotonin.bacnet4j.type.enumerated.LoggingType;
import com.serotonin.bacnet4j.type.enumerated.Maintenance;
import com.serotonin.bacnet4j.type.enumerated.NodeType;
import com.serotonin.bacnet4j.type.enumerated.NotifyType;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.Polarity;
import com.serotonin.bacnet4j.type.enumerated.ProgramError;
import com.serotonin.bacnet4j.type.enumerated.ProgramRequest;
import com.serotonin.bacnet4j.type.enumerated.ProgramState;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.enumerated.Reliability;
import com.serotonin.bacnet4j.type.enumerated.RestartReason;
import com.serotonin.bacnet4j.type.enumerated.Segmentation;
import com.serotonin.bacnet4j.type.enumerated.ShedState;
import com.serotonin.bacnet4j.type.enumerated.SilencedState;
import com.serotonin.bacnet4j.type.enumerated.VtClass;
import com.serotonin.bacnet4j.type.eventParameter.EventParameter;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.Date;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.Primitive;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.type.primitive.SignedInteger;
import com.serotonin.bacnet4j.type.primitive.Time;
import com.serotonin.bacnet4j.type.primitive.Unsigned16;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;

public class ObjectProperties {
    private static final Map<ObjectType, List<PropertyTypeDefinition>> propertyTypes = new HashMap<ObjectType, List<PropertyTypeDefinition>>();

    public static PropertyTypeDefinition getPropertyTypeDefinition(ObjectType objectType,
            PropertyIdentifier propertyIdentifier) {
        List<PropertyTypeDefinition> list = propertyTypes.get(objectType);
        if (list == null)
            return null;
        for (PropertyTypeDefinition def : list) {
            if (def.getPropertyIdentifier().equals(propertyIdentifier))
                return def;
        }
        return null;
    }

    public static PropertyTypeDefinition getPropertyTypeDefinitionRequired(ObjectType objectType,
            PropertyIdentifier propertyIdentifier) throws BACnetServiceException {
        PropertyTypeDefinition def = getPropertyTypeDefinition(objectType, propertyIdentifier);
        if (def == null)
            throw new BACnetServiceException(ErrorClass.property, ErrorCode.unknownProperty, objectType + "/"
                    + propertyIdentifier);
        return def;
    }

    public static List<PropertyTypeDefinition> getPropertyTypeDefinitions(ObjectType objectType) {
        return getPropertyTypeDefinitions(objectType, 0);
    }

    public static List<PropertyTypeDefinition> getRequiredPropertyTypeDefinitions(ObjectType objectType) {
        return getPropertyTypeDefinitions(objectType, 1);
    }

    public static List<PropertyTypeDefinition> getOptionalPropertyTypeDefinitions(ObjectType objectType) {
        return getPropertyTypeDefinitions(objectType, 2);
    }

    public static boolean isCommandable(ObjectType type, PropertyIdentifier pid) {
        if (!pid.equals(PropertyIdentifier.presentValue))
            return false;
        return type.equals(ObjectType.analogOutput) || type.equals(ObjectType.analogValue)
                || type.equals(ObjectType.binaryOutput) || type.equals(ObjectType.binaryValue)
                || type.equals(ObjectType.multiStateOutput) || type.equals(ObjectType.multiStateValue)
                || type.equals(ObjectType.accessDoor);
    }

    /**
     * @param objectType
     * @param include
     *            0 = all, 1 = required, 2 = optional
     * @return
     */
    private static List<PropertyTypeDefinition> getPropertyTypeDefinitions(ObjectType objectType, int include) {
        List<PropertyTypeDefinition> result = new ArrayList<PropertyTypeDefinition>();
        List<PropertyTypeDefinition> list = propertyTypes.get(objectType);
        if (list != null) {
            for (PropertyTypeDefinition def : list) {
                if (include == 0 || (include == 1 && def.isRequired()) || (include == 2 && def.isOptional()))
                    result.add(def);
            }
        }
        return result;
    }

    private static void add(ObjectType type, PropertyIdentifier pid, Class<? extends Encodable> clazz,
            boolean sequence, boolean required) {
        List<PropertyTypeDefinition> list = propertyTypes.get(type);
        if (list == null) {
            list = new ArrayList<PropertyTypeDefinition>();
            propertyTypes.put(type, list);
        }

        // Check for existing entries.
        for (PropertyTypeDefinition def : list) {
            if (def.getPropertyIdentifier().equals(pid)) {
                list.remove(def);
                break;
            }
        }

        list.add(new PropertyTypeDefinition(type, pid, clazz, sequence, required));
    }

    public static void addPropertyTypeDefinition(ObjectType type, PropertyIdentifier pid,
            Class<? extends Encodable> clazz, boolean sequence, boolean required) {
        List<PropertyTypeDefinition> list = propertyTypes.get(type);
        if (list == null)
            throw new RuntimeException("ObjectType not found: " + type);

        // Check for existing entries.
        for (PropertyTypeDefinition def : list) {
            if (def.getPropertyIdentifier().equals(pid))
                throw new RuntimeException("ObjectType already contains the given PropertyIdentifier");
        }

        list.add(new PropertyTypeDefinition(type, pid, clazz, sequence, required));
    }

    static {
        // Access door
        add(ObjectType.accessDoor, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.presentValue, DoorValue.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.eventState, EventState.class, false, true); // EventState.normal
        add(ObjectType.accessDoor, PropertyIdentifier.reliability, Reliability.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.priorityArray, PriorityArray.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.relinquishDefault, DoorValue.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.doorStatus, DoorStatus.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.lockStatus, LockStatus.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.securedStatus, DoorSecuredStatus.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.doorMembers, DeviceObjectReference.class, true, false);
        add(ObjectType.accessDoor, PropertyIdentifier.doorPulseTime, UnsignedInteger.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.doorExtendedPulseTime, UnsignedInteger.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.doorUnlockDelayTime, UnsignedInteger.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.doorOpenTooLongTime, UnsignedInteger.class, false, true);
        add(ObjectType.accessDoor, PropertyIdentifier.doorAlarmState, DoorAlarmState.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.maskedAlarmValues, DoorAlarmState.class, true, false);
        add(ObjectType.accessDoor, PropertyIdentifier.maintenanceRequired, Maintenance.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.alarmValues, DoorAlarmState.class, true, false);
        add(ObjectType.accessDoor, PropertyIdentifier.faultValues, DoorAlarmState.class, true, false);
        add(ObjectType.accessDoor, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.accessDoor, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.accessDoor, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.accessDoor, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Accumulator
        add(ObjectType.accumulator, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.presentValue, UnsignedInteger.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.scale, Scale.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.units, EngineeringUnits.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.prescale, Prescale.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.maxPresValue, UnsignedInteger.class, false, true);
        add(ObjectType.accumulator, PropertyIdentifier.valueChangeTime, DateTime.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.valueBeforeChange, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.valueSet, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.loggingRecord, AccumulatorRecord.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.loggingObject, ObjectIdentifier.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.pulseRate, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.highLimit, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.lowLimit, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.limitMonitoringInterval, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.limitEnable, LimitEnable.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.accumulator, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.accumulator, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Analog input
        add(ObjectType.analogInput, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.presentValue, Real.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.updateInterval, UnsignedInteger.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.units, EngineeringUnits.class, false, true);
        add(ObjectType.analogInput, PropertyIdentifier.minPresValue, Real.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.maxPresValue, Real.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.resolution, Real.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.covIncrement, Real.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.highLimit, Real.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.lowLimit, Real.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.deadband, Real.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.limitEnable, LimitEnable.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.analogInput, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.analogInput, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.analogInput, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Analog output
        add(ObjectType.analogOutput, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.presentValue, Real.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.units, EngineeringUnits.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.minPresValue, Real.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.maxPresValue, Real.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.resolution, Real.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.priorityArray, PriorityArray.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.relinquishDefault, Real.class, false, true);
        add(ObjectType.analogOutput, PropertyIdentifier.covIncrement, Real.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.highLimit, Real.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.lowLimit, Real.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.deadband, Real.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.limitEnable, LimitEnable.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.analogOutput, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.analogOutput, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.analogOutput, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Analog value
        add(ObjectType.analogValue, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.presentValue, Real.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.units, EngineeringUnits.class, false, true);
        add(ObjectType.analogValue, PropertyIdentifier.priorityArray, PriorityArray.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.relinquishDefault, Real.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.covIncrement, Real.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.highLimit, Real.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.lowLimit, Real.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.deadband, Real.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.limitEnable, LimitEnable.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.analogValue, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.analogValue, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.analogValue, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Averaging
        add(ObjectType.averaging, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.minimumValue, Real.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.minimumValueTimestamp, DateTime.class, false, false);
        add(ObjectType.averaging, PropertyIdentifier.averageValue, Real.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.varianceValue, Real.class, false, false);
        add(ObjectType.averaging, PropertyIdentifier.maximumValue, Real.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.maximumValueTimestamp, DateTime.class, false, false);
        add(ObjectType.averaging, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.averaging, PropertyIdentifier.attemptedSamples, UnsignedInteger.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.validSamples, UnsignedInteger.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.objectPropertyReference, DeviceObjectPropertyReference.class,
                false, true);
        add(ObjectType.averaging, PropertyIdentifier.windowInterval, UnsignedInteger.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.windowSamples, UnsignedInteger.class, false, true);
        add(ObjectType.averaging, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.averaging, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Binary input
        add(ObjectType.binaryInput, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.presentValue, BinaryPV.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.polarity, Polarity.class, false, true);
        add(ObjectType.binaryInput, PropertyIdentifier.inactiveText, CharacterString.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.activeText, CharacterString.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.changeOfStateTime, DateTime.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.changeOfStateCount, UnsignedInteger.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.timeOfStateCountReset, DateTime.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.elapsedActiveTime, UnsignedInteger.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.timeOfActiveTimeReset, DateTime.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.alarmValue, BinaryPV.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.binaryInput, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.binaryInput, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.binaryInput, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Binary output
        add(ObjectType.binaryOutput, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.presentValue, BinaryPV.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.polarity, Polarity.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.inactiveText, CharacterString.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.activeText, CharacterString.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.changeOfStateTime, DateTime.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.changeOfStateCount, UnsignedInteger.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.timeOfStateCountReset, DateTime.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.elapsedActiveTime, UnsignedInteger.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.timeOfActiveTimeReset, DateTime.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.minimumOffTime, UnsignedInteger.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.minimumOnTime, UnsignedInteger.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.priorityArray, PriorityArray.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.relinquishDefault, BinaryPV.class, false, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.feedbackValue, BinaryPV.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.binaryOutput, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.binaryOutput, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Binary value
        add(ObjectType.binaryValue, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.binaryValue, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.binaryValue, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.binaryValue, PropertyIdentifier.presentValue, BinaryPV.class, false, true);
        add(ObjectType.binaryValue, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.binaryValue, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.binaryValue, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.binaryValue, PropertyIdentifier.inactiveText, CharacterString.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.activeText, CharacterString.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.changeOfStateTime, DateTime.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.changeOfStateCount, UnsignedInteger.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.timeOfStateCountReset, DateTime.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.elapsedActiveTime, UnsignedInteger.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.timeOfActiveTimeReset, DateTime.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.minimumOffTime, UnsignedInteger.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.minimumOnTime, UnsignedInteger.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.priorityArray, PriorityArray.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.relinquishDefault, BinaryPV.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.alarmValue, BinaryPV.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.binaryValue, PropertyIdentifier.eventMessageTexts, CharacterString.class, true, false);
        add(ObjectType.binaryValue, PropertyIdentifier.eventMessageTextsConfig, CharacterString.class, true, false);
        add(ObjectType.binaryValue, PropertyIdentifier.eventDetectionEnable, Boolean.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.eventAlgorithmInhibitRef, ObjectPropertyReference.class, false,
                false);
        add(ObjectType.binaryValue, PropertyIdentifier.eventAlgorithmInhibit, Boolean.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.timeDelayNormal, UnsignedInteger.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.reliabilityEvaluationInhibit, Boolean.class, false, false);
        add(ObjectType.binaryValue, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.binaryValue, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Calendar
        add(ObjectType.calendar, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.calendar, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.calendar, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.calendar, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.calendar, PropertyIdentifier.presentValue, Boolean.class, false, true);
        add(ObjectType.calendar, PropertyIdentifier.dateList, CalendarEntry.class, true, true);
        add(ObjectType.calendar, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.calendar, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Command
        add(ObjectType.command, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.command, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.command, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.command, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.command, PropertyIdentifier.presentValue, UnsignedInteger.class, false, true);
        add(ObjectType.command, PropertyIdentifier.inProcess, Boolean.class, false, true);
        add(ObjectType.command, PropertyIdentifier.allWritesSuccessful, Boolean.class, false, true);
        add(ObjectType.command, PropertyIdentifier.action, ActionList.class, true, true);
        add(ObjectType.command, PropertyIdentifier.actionText, CharacterString.class, true, false);
        add(ObjectType.command, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.command, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Device
        add(ObjectType.device, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.device, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.device, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.device, PropertyIdentifier.systemStatus, DeviceStatus.class, false, true);
        add(ObjectType.device, PropertyIdentifier.vendorName, CharacterString.class, false, true);
        add(ObjectType.device, PropertyIdentifier.vendorIdentifier, Unsigned16.class, false, true);
        add(ObjectType.device, PropertyIdentifier.modelName, CharacterString.class, false, true);
        add(ObjectType.device, PropertyIdentifier.firmwareRevision, CharacterString.class, false, true);
        add(ObjectType.device, PropertyIdentifier.applicationSoftwareVersion, CharacterString.class, false, true);
        add(ObjectType.device, PropertyIdentifier.location, CharacterString.class, false, false);
        add(ObjectType.device, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.device, PropertyIdentifier.protocolVersion, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.protocolRevision, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.protocolServicesSupported, ServicesSupported.class, false, true);
        add(ObjectType.device, PropertyIdentifier.protocolObjectTypesSupported, ObjectTypesSupported.class, false, true);
        add(ObjectType.device, PropertyIdentifier.objectList, ObjectIdentifier.class, true, true);
        add(ObjectType.device, PropertyIdentifier.structuredObjectList, ObjectIdentifier.class, true, false);
        add(ObjectType.device, PropertyIdentifier.maxApduLengthAccepted, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.segmentationSupported, Segmentation.class, false, true);
        add(ObjectType.device, PropertyIdentifier.maxSegmentsAccepted, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.vtClassesSupported, VtClass.class, true, false);
        add(ObjectType.device, PropertyIdentifier.activeVtSessions, VtSession.class, true, false);
        add(ObjectType.device, PropertyIdentifier.localTime, Time.class, false, false);
        add(ObjectType.device, PropertyIdentifier.localDate, Date.class, false, false);
        add(ObjectType.device, PropertyIdentifier.utcOffset, SignedInteger.class, false, false);
        add(ObjectType.device, PropertyIdentifier.daylightSavingsStatus, Boolean.class, false, false);
        add(ObjectType.device, PropertyIdentifier.apduSegmentTimeout, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.apduTimeout, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.numberOfApduRetries, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.timeSynchronizationRecipients, Recipient.class, true, false);
        add(ObjectType.device, PropertyIdentifier.maxMaster, UnsignedInteger.class, false, false);
        add(ObjectType.device, PropertyIdentifier.maxInfoFrames, UnsignedInteger.class, false, false);
        add(ObjectType.device, PropertyIdentifier.deviceAddressBinding, AddressBinding.class, true, true);
        add(ObjectType.device, PropertyIdentifier.databaseRevision, UnsignedInteger.class, false, true);
        add(ObjectType.device, PropertyIdentifier.configurationFiles, ObjectIdentifier.class, true, false);
        add(ObjectType.device, PropertyIdentifier.lastRestoreTime, TimeStamp.class, false, false);
        add(ObjectType.device, PropertyIdentifier.backupFailureTimeout, Unsigned16.class, false, false);
        add(ObjectType.device, PropertyIdentifier.backupPreparationTime, Unsigned16.class, false, false);
        add(ObjectType.device, PropertyIdentifier.restorePreparationTime, Unsigned16.class, false, false);
        add(ObjectType.device, PropertyIdentifier.restoreCompletionTime, Unsigned16.class, false, false);
        add(ObjectType.device, PropertyIdentifier.backupAndRestoreState, BackupState.class, false, true);
        add(ObjectType.device, PropertyIdentifier.activeCovSubscriptions, CovSubscription.class, true, true);
        add(ObjectType.device, PropertyIdentifier.slaveProxyEnable, Boolean.class, true, false);
        add(ObjectType.device, PropertyIdentifier.manualSlaveAddressBinding, AddressBinding.class, true, false);
        add(ObjectType.device, PropertyIdentifier.autoSlaveDiscovery, Boolean.class, true, false);
        add(ObjectType.device, PropertyIdentifier.slaveAddressBinding, AddressBinding.class, true, false);
        add(ObjectType.device, PropertyIdentifier.lastRestartReason, RestartReason.class, false, false);
        add(ObjectType.device, PropertyIdentifier.timeOfDeviceRestart, TimeStamp.class, false, false);
        add(ObjectType.device, PropertyIdentifier.restartNotificationRecipients, Recipient.class, true, false);
        add(ObjectType.device, PropertyIdentifier.utcTimeSynchronizationRecipients, Recipient.class, true, false);
        add(ObjectType.device, PropertyIdentifier.timeSynchronizationInterval, UnsignedInteger.class, false, false);
        add(ObjectType.device, PropertyIdentifier.alignIntervals, Boolean.class, false, false);
        add(ObjectType.device, PropertyIdentifier.intervalOffset, UnsignedInteger.class, false, false);
        add(ObjectType.device, PropertyIdentifier.serialNumber, CharacterString.class, false, false);
        add(ObjectType.device, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.device, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Event enrollment
        add(ObjectType.eventEnrollment, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.eventEnrollment, PropertyIdentifier.eventType, EventType.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.notifyType, NotifyType.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.eventParameters, EventParameter.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.objectPropertyReference,
                DeviceObjectPropertyReference.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.eventEnrollment, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.eventEnrollment, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Event log
        add(ObjectType.eventLog, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.enable, Boolean.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.startTime, DateTime.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.stopTime, DateTime.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.stopWhenFull, Boolean.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.bufferSize, UnsignedInteger.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.logBuffer, EventLogRecord.class, true, true);
        add(ObjectType.eventLog, PropertyIdentifier.recordCount, UnsignedInteger.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.totalRecordCount, UnsignedInteger.class, false, true);
        add(ObjectType.eventLog, PropertyIdentifier.notificationThreshold, UnsignedInteger.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.recordsSinceNotification, UnsignedInteger.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.lastNotifyRecord, UnsignedInteger.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.eventLog, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.eventLog, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.eventLog, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // File
        add(ObjectType.file, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.file, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.file, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.file, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.file, PropertyIdentifier.fileType, CharacterString.class, false, true);
        add(ObjectType.file, PropertyIdentifier.fileSize, UnsignedInteger.class, false, true);
        add(ObjectType.file, PropertyIdentifier.modificationDate, DateTime.class, false, true);
        add(ObjectType.file, PropertyIdentifier.archive, Boolean.class, false, true);
        add(ObjectType.file, PropertyIdentifier.readOnly, Boolean.class, false, true);
        add(ObjectType.file, PropertyIdentifier.fileAccessMethod, FileAccessMethod.class, false, true);
        add(ObjectType.file, PropertyIdentifier.recordCount, UnsignedInteger.class, false, false);
        add(ObjectType.file, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.file, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Group
        add(ObjectType.group, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.group, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.group, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.group, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.group, PropertyIdentifier.listOfGroupMembers, ReadAccessSpecification.class, true, true);
        add(ObjectType.group, PropertyIdentifier.presentValue, ReadAccessResult.class, true, true);
        add(ObjectType.group, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.group, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Life safety point
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.presentValue, LifeSafetyState.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.trackingValue, LifeSafetyState.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.reliability, Reliability.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.mode, LifeSafetyMode.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.acceptedModes, LifeSafetyMode.class, true, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.lifeSafetyAlarmValues, LifeSafetyState.class, true, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.alarmValues, LifeSafetyState.class, true, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.faultValues, LifeSafetyState.class, true, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.silenced, SilencedState.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.operationExpected, LifeSafetyOperation.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.maintenanceRequired, Maintenance.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.setting, UnsignedInteger.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.directReading, Real.class, false, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.units, EngineeringUnits.class, false, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.memberOf, DeviceObjectReference.class, true, false);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.lifeSafetyPoint, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Life safety zone
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.presentValue, LifeSafetyState.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.trackingValue, LifeSafetyState.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.reliability, Reliability.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.mode, LifeSafetyMode.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.acceptedModes, LifeSafetyMode.class, true, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.lifeSafetyAlarmValues, LifeSafetyState.class, true, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.alarmValues, LifeSafetyState.class, true, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.faultValues, LifeSafetyState.class, true, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.silenced, SilencedState.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.operationExpected, LifeSafetyOperation.class, false, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.maintenanceRequired, Maintenance.class, false, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.zoneMembers, DeviceObjectReference.class, true, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.memberOf, DeviceObjectReference.class, true, false);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.lifeSafetyZone, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Load control
        add(ObjectType.loadControl, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.presentValue, ShedState.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.stateDescription, CharacterString.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.reliability, Reliability.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.requestedShedLevel, ShedLevel.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.startTime, DateTime.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.shedDuration, UnsignedInteger.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.dutyWindow, UnsignedInteger.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.enable, Boolean.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.fullDutyBaseline, Real.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.expectedShedLevel, ShedLevel.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.actualShedLevel, ShedLevel.class, false, true);
        add(ObjectType.loadControl, PropertyIdentifier.shedLevels, UnsignedInteger.class, true, true);
        add(ObjectType.loadControl, PropertyIdentifier.shedLevelDescriptions, CharacterString.class, true, true);
        add(ObjectType.loadControl, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.loadControl, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.loadControl, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.loadControl, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Loop
        add(ObjectType.loop, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.presentValue, Real.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.updateInterval, UnsignedInteger.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.outputUnits, EngineeringUnits.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.manipulatedVariableReference, ObjectPropertyReference.class, false,
                true);
        add(ObjectType.loop, PropertyIdentifier.controlledVariableReference, ObjectPropertyReference.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.controlledVariableValue, Real.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.controlledVariableUnits, EngineeringUnits.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.setpointReference, SetpointReference.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.setpoint, Real.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.action, Action.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.proportionalConstant, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.proportionalConstantUnits, EngineeringUnits.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.integralConstant, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.integralConstantUnits, EngineeringUnits.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.derivativeConstant, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.derivativeConstantUnits, EngineeringUnits.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.bias, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.maximumOutput, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.minimumOutput, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.priorityForWriting, UnsignedInteger.class, false, true);
        add(ObjectType.loop, PropertyIdentifier.covIncrement, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.errorLimit, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.deadband, Real.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.loop, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.loop, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.loop, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Multi state input
        add(ObjectType.multiStateInput, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.presentValue, UnsignedInteger.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.numberOfStates, UnsignedInteger.class, false, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.stateText, CharacterString.class, true, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.alarmValues, UnsignedInteger.class, true, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.faultValues, UnsignedInteger.class, true, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.multiStateInput, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.multiStateInput, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Multi state output
        add(ObjectType.multiStateOutput, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.presentValue, UnsignedInteger.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.deviceType, CharacterString.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.numberOfStates, UnsignedInteger.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.stateText, CharacterString.class, true, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.priorityArray, PriorityArray.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.relinquishDefault, UnsignedInteger.class, false, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.feedbackValue, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.multiStateOutput, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.multiStateOutput, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Multi state value
        add(ObjectType.multiStateValue, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.presentValue, UnsignedInteger.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.numberOfStates, UnsignedInteger.class, false, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.stateText, CharacterString.class, true, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.priorityArray, PriorityArray.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.relinquishDefault, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.alarmValues, UnsignedInteger.class, true, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.faultValues, UnsignedInteger.class, true, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventMessageTexts, CharacterString.class, true, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventMessageTextsConfig, CharacterString.class, true, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventDetectionEnable, Boolean.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventAlgorithmInhibitRef, ObjectPropertyReference.class,
                false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.eventAlgorithmInhibit, Boolean.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.timeDelayNormal, UnsignedInteger.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.reliabilityEvaluationInhibit, Boolean.class, false, false);
        add(ObjectType.multiStateValue, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.multiStateValue, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Notification class
        add(ObjectType.notificationClass, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.notificationClass, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.notificationClass, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.notificationClass, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.notificationClass, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, true);
        add(ObjectType.notificationClass, PropertyIdentifier.priority, UnsignedInteger.class, true, true);
        add(ObjectType.notificationClass, PropertyIdentifier.ackRequired, EventTransitionBits.class, false, true);
        add(ObjectType.notificationClass, PropertyIdentifier.recipientList, Destination.class, true, true);
        add(ObjectType.notificationClass, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.notificationClass, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Program
        add(ObjectType.program, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.program, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.program, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.program, PropertyIdentifier.programState, ProgramState.class, false, true);
        add(ObjectType.program, PropertyIdentifier.programChange, ProgramRequest.class, false, true);
        add(ObjectType.program, PropertyIdentifier.reasonForHalt, ProgramError.class, false, false);
        add(ObjectType.program, PropertyIdentifier.descriptionOfHalt, CharacterString.class, false, true);
        add(ObjectType.program, PropertyIdentifier.programLocation, CharacterString.class, false, false);
        add(ObjectType.program, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.program, PropertyIdentifier.instanceOf, CharacterString.class, false, false);
        add(ObjectType.program, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.program, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.program, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.program, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.program, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Pulse converter
        add(ObjectType.pulseConverter, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.presentValue, Real.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.inputReference, ObjectPropertyReference.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.units, EngineeringUnits.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.scaleFactor, Real.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.adjustValue, Real.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.count, UnsignedInteger.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.updateTime, DateTime.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.countChangeTime, DateTime.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.countBeforeChange, UnsignedInteger.class, false, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.covIncrement, Real.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.covPeriod, UnsignedInteger.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.timeDelay, UnsignedInteger.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.highLimit, Real.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.lowLimit, Real.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.deadband, Real.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.limitEnable, LimitEnable.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.pulseConverter, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.pulseConverter, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Schedule
        add(ObjectType.schedule, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.presentValue, Primitive.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.effectivePeriod, DateRange.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.weeklySchedule, DailySchedule.class, true, false);
        add(ObjectType.schedule, PropertyIdentifier.exceptionSchedule, SpecialEvent.class, true, false);
        add(ObjectType.schedule, PropertyIdentifier.scheduleDefault, Primitive.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.listOfObjectPropertyReferences,
                DeviceObjectPropertyReference.class, true, true);
        add(ObjectType.schedule, PropertyIdentifier.priorityForWriting, UnsignedInteger.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.reliability, Reliability.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.outOfService, Boolean.class, false, true);
        add(ObjectType.schedule, PropertyIdentifier.eventDetectionEnable, Boolean.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.eventState, EventState.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.schedule, PropertyIdentifier.eventMessageTexts, CharacterString.class, true, false);
        add(ObjectType.schedule, PropertyIdentifier.eventMessageTextsConfig, CharacterString.class, true, false);
        add(ObjectType.schedule, PropertyIdentifier.reliabilityEvaluationInhibit, Boolean.class, false, false);
        add(ObjectType.schedule, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.schedule, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Structured View
        add(ObjectType.structuredView, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.structuredView, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.structuredView, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.structuredView, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.structuredView, PropertyIdentifier.nodeType, NodeType.class, false, false);
        add(ObjectType.structuredView, PropertyIdentifier.nodeSubtype, CharacterString.class, false, false);
        add(ObjectType.structuredView, PropertyIdentifier.subordinateList, DeviceObjectReference.class, true, true);
        add(ObjectType.structuredView, PropertyIdentifier.subordinateAnnotations, CharacterString.class, true, false);
        add(ObjectType.structuredView, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.structuredView, PropertyIdentifier.profileName, CharacterString.class, false, false);

        // Trend log
        add(ObjectType.trendLog, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.enable, Boolean.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.startTime, DateTime.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.stopTime, DateTime.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.logDeviceObjectProperty, DeviceObjectPropertyReference.class,
                false, false);
        add(ObjectType.trendLog, PropertyIdentifier.logInterval, UnsignedInteger.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.covResubscriptionInterval, UnsignedInteger.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.clientCovIncrement, ClientCov.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.stopWhenFull, Boolean.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.bufferSize, UnsignedInteger.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.logBuffer, LogRecord.class, true, true);
        add(ObjectType.trendLog, PropertyIdentifier.recordCount, UnsignedInteger.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.totalRecordCount, UnsignedInteger.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.notificationThreshold, UnsignedInteger.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.recordsSinceNotification, UnsignedInteger.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.lastNotifyRecord, UnsignedInteger.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.trendLog, PropertyIdentifier.profileName, CharacterString.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.loggingType, LoggingType.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.alignIntervals, Boolean.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.intervalOffset, UnsignedInteger.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.trigger, Boolean.class, false, false);
        add(ObjectType.trendLog, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.trendLog, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.trendLog, PropertyIdentifier.reliability, Reliability.class, false, false);

        // Trend log multiple
        add(ObjectType.trendLogMultiple, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.objectType, ObjectType.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.description, CharacterString.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.statusFlags, StatusFlags.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.eventState, EventState.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.reliability, Reliability.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.enable, Boolean.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.startTime, DateTime.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.stopTime, DateTime.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.logDeviceObjectProperty,
                DeviceObjectPropertyReference.class, true, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.loggingType, LoggingType.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.logInterval, UnsignedInteger.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.alignIntervals, Boolean.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.intervalOffset, UnsignedInteger.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.trigger, Boolean.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.stopWhenFull, Boolean.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.bufferSize, UnsignedInteger.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.logBuffer, LogMultipleRecord.class, true, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.recordCount, UnsignedInteger.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.totalRecordCount, UnsignedInteger.class, false, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.notificationThreshold, UnsignedInteger.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.recordsSinceNotification, UnsignedInteger.class, false,
                false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.lastNotifyRecord, UnsignedInteger.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.notificationClass, UnsignedInteger.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.eventEnable, EventTransitionBits.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.ackedTransitions, EventTransitionBits.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.notifyType, NotifyType.class, false, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.eventTimeStamps, TimeStamp.class, true, false);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.propertyList, PropertyIdentifier.class, true, true);
        add(ObjectType.trendLogMultiple, PropertyIdentifier.profileName, CharacterString.class, false, false);
        
        //Blinds command
        add(ObjectType.blindsCommand, PropertyIdentifier.objectIdentifier, ObjectIdentifier.class, false, true);
        add(ObjectType.blindsCommand, PropertyIdentifier.objectName, CharacterString.class, false, true);
        add(ObjectType.blindsCommand, PropertyIdentifier.presentValue, Real.class, true, true);
        add(ObjectType.blindsCommand, PropertyIdentifier.progressValue, Real.class, true, true);
    }
}
