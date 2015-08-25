package project.security;

import java.util.ArrayList;

public class SubAreas {
    private int subAreaId;
    private boolean fireCheck;
    private int fireCount;
    private boolean motionCheck;
    private int motionCount;

    SubAreas() {

    }

    public int getSubAreaId() {
        return subAreaId;
    }

    public void setSubAreaId(int subAreaId) {
        this.subAreaId = subAreaId;
    }

    public void addFire(int subAreaId) {

        if (fireCheck = false) {
            fireCount++;
            fireCheck = true;
        } else {
            System.out.println(" Maximum number of fire sensor reached");
        }
    }

    public void deleteFire(int subAreaId) {

        if (fireCheck = true) {
            fireCount--;
        }

    }


    public void addMotion(int subAreaId) {
        motionCheck = false;
        if (motionCheck = false) {
            motionCount++;
            motionCheck = true;
        } else {
            System.out.println(" Maximum number of motion sensor reached");
        }
    }

    public void deleteMotion(int subAreaId) {

        if (motionCheck = true) {
            motionCount--;
        }

    }

}