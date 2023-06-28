package com.codecool.marsexploration.mapexplorer.simulation.routines;

import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

public interface Routine {
    void performMovement(SimulationContext context, MarsRover rover);

    boolean shouldBeUsed(SimulationContext context);
}
