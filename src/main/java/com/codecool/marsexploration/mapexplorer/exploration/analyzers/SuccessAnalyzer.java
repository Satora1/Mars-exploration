package com.codecool.marsexploration.mapexplorer.exploration.analyzers;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.exploration.SimulationContext;

public class SuccessAnalyzer implements OutcomeAnalyzer{
    @Override
    public boolean checkforOutcome(SimulationContext context) {
        if (context.getWaterAmount() >= 3 || context.getMineralsAmount() >= 3) {
            context.setOutcome(ExplorationOutcome.COLONIZABLE);
            System.out.println("Plantet is colonizable!");
            return true;
        }
        System.out.println("Still Searching");
        return false;
    }
}
