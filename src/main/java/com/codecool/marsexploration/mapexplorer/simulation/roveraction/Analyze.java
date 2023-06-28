package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.OutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.RoverAction;

import java.util.List;

public class Analyze implements RoverAction {

    private final List<OutcomeAnalyzer> analyzersList;

    public Analyze(List<OutcomeAnalyzer> analyzersList) {
        this.analyzersList = analyzersList;
    }

    @Override
    public void roverDoAction(SimulationContext context, MarsRover rover) {
        if (analyzersList.stream().anyMatch(outcomeAnalyzer -> outcomeAnalyzer.checkforOutcome(context))) {
            System.out.println("Mission ends. Return to the ship.");
        }
        else {
            System.out.println("Mission continues.");
        }
    }
}
