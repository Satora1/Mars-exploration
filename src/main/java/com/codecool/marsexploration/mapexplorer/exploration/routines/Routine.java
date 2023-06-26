package com.codecool.marsexploration.mapexplorer.exploration.routines;

import com.codecool.marsexploration.mapexplorer.exploration.SimulationContext;

public interface Routine {
    void performMovement(SimulationContext context);

    boolean shouldBeUsed(SimulationContext context);
}
