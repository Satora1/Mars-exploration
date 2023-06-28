package com.codecool.marsexploration.mapexplorer.buildings;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

import java.util.Optional;

public abstract class Building {

    protected static int currentBuildingId = 0;
    protected static int currentRoverId = 0;

    protected boolean status = false;

    protected static BuildingType type;

    protected int mineralsOnStock = 0;
    protected int waterOnStock = 0;

    protected static final int ROVER_COST = 2;


    abstract Optional<MarsRover> work(SimulationContext context);

    public abstract Coordinate getBaseCoordinate();

    protected boolean checkIfBuildingIsFinished() {

        if (status) {
            return true;
        }

        if (mineralsOnStock >= type.getMineralCost() && waterOnStock >= type.getWaterCost()) {
            status = true;
            mineralsOnStock -= type.getMineralCost();
            waterOnStock = waterOnStock - type.getWaterCost();
            return true;
        }
        return false;
    }


}
