package com.codecool.marsexploration.mapexplorer.buildings;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

import java.util.Optional;

public class PowerPlant extends Building{
    private final int id;
    private static final BuildingType type = BuildingType.POWER_PLANT;

    private final Coordinate baseCoordinate;


    public PowerPlant(Coordinate baseCoordinate) {
        this.id = currentBuildingId++;
        this.baseCoordinate = baseCoordinate;
    }

    @Override
    public Optional<MarsRover> work(SimulationContext context) {
        if (checkIfBuildingIsFinished() && mineralsOnStock >= ROVER_COST) {
            mineralsOnStock  -= ROVER_COST;
            currentRoverId++;
            return Optional.of(new MarsRover(String.valueOf(currentRoverId), 2));
        }
        return Optional.empty();
    }

    @Override
    public Coordinate getBaseCoordinate() {
        return baseCoordinate;
    }

    public int getId() {
        return id;
    }
}
