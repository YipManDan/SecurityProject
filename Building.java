package project.security;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Building {

    private BufferedImage image;
    private int buildingId;
    //private SubAreas sub;
    private ArrayList<SubAreas> subAreaList;
    private MainSystem mainSystem;

    Building(int buildingId)
    {
        this.buildingId = buildingId;
        mainSystem = new MainSystem();
    }
    public void createSubAreas(int rooms) {
        subAreaList = new ArrayList<>(rooms);
        //loop to create rooms number of subAreas, numbered from 1-rooms. These are added to subAreaList.
        for(int i=0; i<rooms; i++) {
            SubAreas temp = new SubAreas(i+1, mainSystem.getSchedule());
            subAreaList.add(temp);
        }

    }
    public SubAreas getSubArea(int subAreaId) {
        for(SubAreas temp : subAreaList) {
            if(temp.getSubAreaId() == subAreaId) {
                int index = subAreaList.indexOf(temp);
                return subAreaList.get(index);
            }
        }
        return null;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }




}
