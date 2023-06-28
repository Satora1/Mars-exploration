package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

public class Build implements RoverAction{
    @Override
    public void roverDoAction(SimulationContext simulationContext, MarsRover rover) {
        Coordinate baseCoord = rover.getCurrentPosition();

        
    }
}
