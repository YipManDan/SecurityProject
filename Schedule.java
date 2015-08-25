package project.security;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Daniel on 8/14/2015.
 */
public class Schedule {
    boolean isArmed, fireArmed, motionArmed;
    LocalTime time;
    public enum Setting {weekday, weekend, vacation, manual}
    Setting setting;
    ArrayList<Sensor> sensors = new ArrayList<>(20);
    public Schedule () {
        setting = Setting.weekday;
    }
    public void allOff() {
        for(Sensor temp: sensors) {
            temp.setOn(false);
        }
    }
    public void disarm() {
        allOff();
    }
    public void arm() {
        if(setting == Setting.manual)
            manualOn();
        else
            updateSchedule();
    }
    public void allOn() {
        for(Sensor temp: sensors) {
            temp.setOn(true);
        }
    }
    public void allFireSensorsOn(Boolean on) {
        setting = Setting.manual;
        for(Sensor temp: sensors) {
            if(temp.getClass().equals(FireSensor.class)) {
                if(on)
                    temp.setOn(true);
                else
                    temp.setOn(false);
            }

        }
    }
    public void allMotionSensorsOn(Boolean on) {
        setting = Setting.manual;
        for(Sensor temp: sensors) {
            if(temp.getClass().equals(MotionSensor.class)) {
                if(on)
                    temp.setOn(true);
                else
                    temp.setOn(false);
            }

        }
    }
    public void manualOn() {
        for(Sensor temp : sensors)
            temp.manualMode();
    }
    public void updateSchedule() {
        if(setting == Setting.manual)
            return;
        updateSetting();
        if(setting == Setting.manual)
            return;
        this.time = Clock.getTime();
        //sortByOn();
        for(Sensor temp: sensors) {
            if(!temp.getOnTime(setting).isAfter(time)) {
                temp.setOn(true);
            } else {
                break;
            }
        }
        //sortByOff();
        for(Sensor temp: sensors) {
            if(!temp.getOffTime(setting).isBefore(time)) {
                temp.setOn(false);
            } else {
                break;
            }
        }
    }
    public void addSensor(Sensor aSensor) {
        sensors.add(aSensor);
        sortById();
    }
    public void removeSensor(Sensor aSensor) {
        sensors.remove(aSensor);
        sortById();
    }
    public void setSetting(Schedule.Setting setting) {
        this.setting = setting;
        if (setting == Setting.manual) {
            manualOn();
        }
        else updateSchedule();
    }
    public void setVacation(int days) {
        //this.setting = Setting.vacation;
        Clock.setVacation(days);
        updateSchedule();
    }
    public void updateSetting() {
        if(setting == Setting.manual)
            return;
        else if(Clock.onVacation())
            setting = Setting.vacation;
        else if(Clock.isWeekend())
            setting = Setting.weekend;
        else
            setting = Setting.weekday;
    }
    public boolean isArmed() {
        return isArmed;
    }
    public boolean isFireArmed() {
        return fireArmed;
    }
    public boolean isMotionArmed() {
        return motionArmed;
    }
    private void sortByOn() {
        Collections.sort(sensors, ByOnTime);
    }
    private void sortByOff() {
        Collections.sort(sensors, ByOffTime);
    }
    private void sortById() {
        Collections.sort(sensors, BySensorId);
    }
    private final Comparator<Sensor> ByOnTime = new Comparator<Sensor>() {
        @Override
        public int compare(Sensor o1, Sensor o2) {
            return o1.getOnTime(setting).compareTo(o2.getOnTime(setting));
        }
    };
    private final Comparator<Sensor> BySensorId= new Comparator<Sensor>() {
        @Override
        public int compare(Sensor o1, Sensor o2) {
            return o1.getSensorID().compareTo(o2.getSensorID());
        }
    };
    private final Comparator<Sensor> ByOffTime = new Comparator<Sensor>() {
        @Override
        public int compare(Sensor o1, Sensor o2) {
            return o1.getOffTime(setting).compareTo(o2.getOffTime(setting));
        }
    };
}

