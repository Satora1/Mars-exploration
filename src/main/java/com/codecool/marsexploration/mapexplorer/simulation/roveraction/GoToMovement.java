package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.routines.Routine;
import com.codecool.marsexploration.mapexplorer.simulation.routines.TravelRoutine;

public class GoToMovement implements RoverAction{

    private final TravelRoutine travelRoutine;

    public GoToMovement(TravelRoutine travelRoutine) {
        this.travelRoutine = travelRoutine;
    }

    @Override
    public void roverDoAction(SimulationContext simulationContext) {

        if (travelRoutine.shouldBeUsed(simulationContext)) {
            if (!travelRoutine.checkIfRoverReachedCoordinates(simulationContext, travelRoutine.getDestination())) {
                travelRoutine.performMovement(simulationContext);
            }
            System.out.println("Rover is already on the spot.");
        }
        else throw new IllegalStateException("Planet is not yet colonizable!");

    }
}
