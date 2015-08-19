package project.security;

import java.time.LocalTime;

/**
 * Created by Daniel on 8/14/2015.
 */
public abstract class Sensor {
    protected int sensorID;
    protected int roomID;
    protected int passCode;
    Boolean on;
    LocalTime onTime, offTime;
    LocalTime weekdayOn, weekdayOff;
    LocalTime weekendOn, weekendOff;
    LocalTime vacationOn, vacationOff;
    Boolean manualOn;
    Schedule.Setting setting = Schedule.Setting.weekday;
    public Sensor(int sensorID, int roomID) {
        this.sensorID = sensorID;
        this.roomID = roomID;
        on = true;
    }
    public LocalTime getOnTime(Schedule.Setting setting) {
        this.setting = setting;
        switch (setting) {
            case weekday:
                return weekdayOn;
            case weekend:
                return weekendOn;
            case vacation:
                return vacationOn;
            default:
                return onTime;
        }
    }

    public LocalTime getOffTime(Schedule.Setting setting) {
        this.setting = setting;
        switch (setting) {
            case weekday:
                return weekdayOff;
            case weekend:
                return weekendOff;
            case vacation:
                return vacationOff;
            default:
                return offTime;
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
    public void manualMode() {
        if(manualOn)
            on = true;
    }
    abstract void raiseAlarm();
}
