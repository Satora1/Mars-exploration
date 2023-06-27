package com.codecool.marsexploration.mapexplorer.simulation.routines;

import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

public interface Routine {
    void performMovement(SimulationContext context);

    boolean shouldBeUsed(SimulationContext context);
}
