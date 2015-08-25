package project.security;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Daniel on 8/25/2015.
 */
public class BuildingList {
    public static ArrayList<Building> buildings = new ArrayList<>(2);


    BuildingList() {
        Building temp = new Building(1);
        temp.createSubAreas(10);
        temp.setSystemPass("0000");
        buildings.add(temp);
        temp = new Building(2);
        buildings.add(temp);
        //temp.setImage(this.getClass().getClassLoader().getResource("singleHouse.jpg"));
    }
    public static Building getBuilding(int index) {
        return buildings.get(index);
    }



}
