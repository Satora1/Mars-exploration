package com.codecool.marsexploration.mapexplorer.simulation.routines;

import com.codecool.marsexploration.mapexplorer.simulation.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

public class ReturningRoutine implements Routine {


    @Override
    public void performMovement(SimulationContext context) {
        Coordinate shipCoordinate = context.getShipsCoordinate();
        context.getRover().setCurrentPosition(shipCoordinate);
    }

    @Override
    public boolean shouldBeUsed(SimulationContext context) {
        return context.getOutcome() != ExplorationOutcome.UNRESOLVED || context.getStepsToTimeout() == context.getStepsNumber();
    }
}
