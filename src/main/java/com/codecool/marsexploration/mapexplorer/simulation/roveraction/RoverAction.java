package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

public interface RoverAction {


    void roverDoAction(SimulationContext simulationContext, MarsRover rover);

}
