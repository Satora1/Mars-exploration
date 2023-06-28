package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.routines.ExploringRoutine;
import com.codecool.marsexploration.mapexplorer.simulation.routines.Routine;

import java.util.ArrayList;
import java.util.List;

public class Movement implements RoverAction {

    private final ExploringRoutine explore;

    public Movement() {
        this.explore = new ExploringRoutine();
    }

    @Override
    public void roverDoAction(SimulationContext simulationContext, MarsRover rover) {

        explore.performMovement(simulationContext, rover);
        //Optional<Routine> chosenRoutine = routinesList.stream().filter(routine -> routine.shouldBeUsed(simulationContext)).findFirst();
        //chosenRoutine.ifPresent(routine -> routine.performMovement(simulationContext));
    }
}
