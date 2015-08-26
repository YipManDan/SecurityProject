package project.security;

import java.util.ArrayList;

public class SubAreas {
    private int subAreaId;
    private Schedule schedule;
    private FireSensor fs = null;
    private MotionSensor ms = null;
    private Boolean fireSensorExists = false;
    private Boolean motionSensorExists = false;

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
        fireSensorExists = true;
        return true;
    }
    public boolean createMotionSensor() {
        if (ms != null)
            return false;
        int sensorID = subAreaId*2;
        ms = new MotionSensor(sensorID, subAreaId);
        schedule.addSensor(ms);
        motionSensorExists = true;
        return true;
    }
    public boolean hasFireSensor() {
        return fireSensorExists;
    }
    public boolean hasMotionSensor() {
        return motionSensorExists;
    }
    public boolean removeFireSensor() {
        if (fs == null)
            return false;
        schedule.removeSensor(fs);
        fs = null;
        fireSensorExists = false;
        return true;
    }
    public boolean removeMotionSensor() {
        if (ms == null)
            return false;
        schedule.removeSensor(ms);
        ms = null;
        motionSensorExists = false;
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