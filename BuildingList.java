package project.security;

import java.util.ArrayList;

/**
 * Created by Daniel on 8/25/2015.
 */
public class BuildingList {
    public static ArrayList<Building> buildings = new ArrayList<>(2);


    BuildingList() {
        buildings.add(new Building(1));
        buildings.get(0).createSubAreas(10);
        buildings.get(0).setSystemPass("0000");
    }
    public static Building getBuilding(int index) {
        return buildings.get(index);
    }



}
