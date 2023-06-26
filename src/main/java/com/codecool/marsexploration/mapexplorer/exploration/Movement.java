package com.codecool.marsexploration.mapexplorer.exploration;

import com.codecool.marsexploration.mapexplorer.exploration.routines.ExploringRoutine;
import com.codecool.marsexploration.mapexplorer.exploration.routines.Routine;

import java.util.List;
import java.util.Optional;

public class Movement implements RoverAction {
    private final List<Routine> routinesList;
    private ExploringRoutine explore;

    public Movement(List<Routine> routinesList, ExploringRoutine explore) {
        this.routinesList = routinesList;
        this.explore = explore;
    }

    @Override
    public void roverDoAction(SimulationContext simulationContext) {

        explore.performMovement(simulationContext);
        //Optional<Routine> chosenRoutine = routinesList.stream().filter(routine -> routine.shouldBeUsed(simulationContext)).findFirst();
        //chosenRoutine.ifPresent(routine -> routine.performMovement(simulationContext));
    }
}
