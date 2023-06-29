package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.buildings.Building;
import com.codecool.marsexploration.mapexplorer.buildings.CommandCenter;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

public class Build implements RoverAction{
    @Override
    public void roverDoAction(SimulationContext simulationContext, MarsRover rover) {

        simulationContext.addBuilding(new CommandCenter(rover.getCurrentPosition()));

        
    }
}
