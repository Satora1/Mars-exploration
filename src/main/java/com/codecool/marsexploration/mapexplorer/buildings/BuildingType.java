package com.codecool.marsexploration.mapexplorer.buildings;

public enum BuildingType {
    COMMAND_CENTER(3, 2),
    POWER_PLANT(6, 1),
    FARM(2, 6);

    private final int mineralCost;
    private final int waterCost;

    BuildingType(int mineralCost, int waterCost) {
        this.mineralCost = mineralCost;
        this.waterCost = waterCost;
    }

    public int getMineralCost() {
        return mineralCost;
    }

    public int getWaterCost() {
        return waterCost;
    }

}
