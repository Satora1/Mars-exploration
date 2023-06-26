package com.codecool.marsexploration.mapexplorer.exploration;

import com.codecool.marsexploration.mapexplorer.exploration.analyzers.OutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.exploration.routines.Routine;

import java.util.List;
import java.util.Optional;

public class Analyze implements RoverAction{

    private final List<OutcomeAnalyzer> analyzersList;

    public Analyze(List<OutcomeAnalyzer> analyzersList) {
        this.analyzersList = analyzersList;
    }

    @Override
    public void roverDoAction(SimulationContext context) {
        if (analyzersList.stream().anyMatch(outcomeAnalyzer -> outcomeAnalyzer.checkforOutcome(context))) {
            System.out.println("Mission ends. Return to the ship.");
        }
        else {
            System.out.println("Mission continues.");
        }
    }
}
