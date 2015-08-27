package project.security;

import java.lang.System;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by Daniel on 8/14/2015.
 */
public class FireSensor extends Sensor{
    public FireSensor(int sensorID, int roomID) {
        super(sensorID, roomID);
    }

    public void raiseAlarm() {
        System.out.println("Fire Detected in room: " + roomID);
        //Make phone call a thread, to be canceled when passCode is inputted
        if(MainSystem.phone1IsEnabled())
            System.out.println("Call: " + MainSystem.getPhone1());
        if(MainSystem.phone2IsEnabled())
            System.out.println("Call: " + MainSystem.getPhone2());
    }
}
