package com.codecool.marsexploration.mapexplorer.exploration.routines;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.exploration.SimulationContext;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.RoverDeployer;

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
