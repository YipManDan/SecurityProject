package project.security;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Building {

    private BufferedImage image;
    private URL imageURL;
    private int buildingId;
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
    public String getSystemPass() {
        return mainSystem.getPassword();
    }
    public void setSystemPass(String password) {
        mainSystem.setPassword(password);
    }

    public URL getImage() {
        return imageURL;
    }

    public void setImage(URL image) {
        this.imageURL = image;
    }
    public int numSubAreas() {
        return subAreaList.size();
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }




}
