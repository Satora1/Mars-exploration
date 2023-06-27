package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.routines.ExploringRoutine;
import com.codecool.marsexploration.mapexplorer.simulation.routines.Routine;

import java.util.List;

public class ExplorationMovement implements RoverAction {
    private final List<Routine> routinesList;
    private ExploringRoutine explore;

    public ExplorationMovement(List<Routine> routinesList, ExploringRoutine explore) {
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
