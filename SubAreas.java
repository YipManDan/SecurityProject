package project.security;

import java.util.ArrayList;

public class SubAreas {
    private int subAreaId;

    Schedule schedule;
    FireSensor fs = null;
    MotionSensor ms = null;

    SubAreas(int subAreaId, Schedule schedule) {
        this.subAreaId = subAreaId;
        this.schedule = schedule;
    }

    public int getSubAreaId() {
        return subAreaId;
    }

    public void setSubAreaId(int subAreaId) {
        this.subAreaId = subAreaId;
    }

    public boolean createFireSensor() {
        if (fs != null)
            return false;
        int sensorID = subAreaId*2-1;
        fs = new FireSensor(sensorID, subAreaId);
        //Adds a sensor to the schedule list
        schedule.addSensor(fs);
        return true;
    }
    public boolean createMotionSensor() {
        if (ms != null)
            return false;
        int sensorID = subAreaId*2;
        ms = new MotionSensor(sensorID, subAreaId);
        schedule.addSensor(ms);
        return true;
    }
    public boolean hasFireSensor() {
        return fs != null;
    }
    public boolean hasMotionSensor() {
        return ms != null;
    }
    public boolean removeFireSensor() {
        if (fs == null)
            return false;
        schedule.removeSensor(fs);
        fs = null;
        return true;
    }
    public boolean removeMotionSensor() {
        if (ms == null)
            return false;
        schedule.removeSensor(ms);
        ms = null;
        return true;
    }
    public void setFireSensorOn(){
        fs.setOn(true);
    }
    public void setFireSensorOff() {
        fs.setOn(false);
    }
    public void setMotionSensorOn() {
        ms.setOn(true);
    }
    public void setMotionSensorOff() {
        ms.setOn(false);
    }

}