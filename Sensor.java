package project.security;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Daniel on 8/14/2015.
 */
public abstract class Sensor {
    protected int sensorID;
    protected int roomID;
    protected int passCode;
    protected Boolean on;
    //protected LocalTime onTime, offTime;
    protected LocalTime weekdayOn, weekdayOff;
    protected LocalTime weekendOn, weekendOff;
    protected LocalTime vacationOn, vacationOff;
    protected Boolean manualOn;
    Schedule.Setting setting = Schedule.Setting.weekday;

    public Sensor(int sensorID, int roomID) {
        this.sensorID = sensorID;
        this.roomID = roomID;
        on = true;
        manualOn = true;
        //Arbitrary defaults
        weekdayOn = LocalTime.of(19,0);
        weekdayOff = LocalTime.of(8,30);
        weekendOn = LocalTime.of(16,0);
        weekendOff = LocalTime.of(10,0);
        vacationOn = LocalTime.MIN;
        vacationOff = LocalTime.of(23,59);
    }
    public Integer getSensorID() {
        return sensorID;
    }
    public void setSetting(Schedule.Setting setting) {
        this.setting = setting;
        System.out.println(sensorID + ": " + setting);
    }
    public void setTime(LocalTime time) {
        //update on status
        if(setting == Schedule.Setting.manual) {
            on = manualOn;
            return;
        }
        LocalTime onTime = getOnTime(setting);
        LocalTime offTime = getOffTime(setting);
        if(onTime.isBefore(offTime)) {
            on = onTime.isBefore(time) && offTime.isAfter(time);
        }
        else {
            on = !(offTime.isBefore(time) && onTime.isAfter(time));
        }
    }
    public LocalTime getOnTime(Schedule.Setting setting) {
        switch (setting) {
            case weekday:
                return weekdayOn;
            case weekend:
                return weekendOn;
            case vacation:
                return vacationOn;
            default:
                return LocalTime.MIN;
        }
    }

    public LocalTime getOffTime(Schedule.Setting setting) {
        switch (setting) {
            case weekday:
                return weekdayOff;
            case weekend:
                return weekendOff;
            case vacation:
                return vacationOff;
            default:
                return LocalTime.MAX;
        }
    }
    public void setOnTime(Schedule.Setting setting, LocalTime time) {
        switch (setting) {
            case weekday:
                weekdayOn = time;
                return;
            case weekend:
                weekendOn = time;
                return;
            case vacation:
                vacationOn = time;
                return;
            default:
                return;
        }

    }
    public void setOffTime(Schedule.Setting setting, LocalTime time) {
        switch (setting) {
            case weekday:
                weekdayOff = time;
                return;
            case weekend:
                weekendOff = time;
                return;
            case vacation:
                vacationOff = time;
                return;
            default:
                return;
        }

    }
    public Boolean isOn() {
        return on;
    }
    public void setOn(Boolean on) {
        this.on = on;
    }
    public void setManualOn(Boolean manualOn) {
        this.manualOn = manualOn;
    }
    public Boolean getManualOn() {
        return manualOn;
    }
    public void manualMode() {
        if(manualOn)
            on = true;
    }
    public int getRoomID() {
        return roomID;
    }
    abstract void raiseAlarm();
}
