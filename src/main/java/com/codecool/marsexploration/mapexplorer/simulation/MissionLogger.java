package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;

public class MissionLogger {
    private FileLogger fLog;
    private ConsoleLogger cLog;
    
    public MissionLogger(String path){
        fLog = new FileLogger(path);
        cLog = new ConsoleLogger();
    }

    public void logStep(SimulationContext simulationContext, MarsRover rover) {
        String message = "STEP " + simulationContext.getStepsNumber() +
                "; EVENT position; UNIT " + rover.getId() +
                "; POSITION " + rover.getCurrentPosition().toString();
        cLog.log(message);
        fLog.log(message);
    }
    public  void logOutcome(SimulationContext simulationContext){
        String message = "\n STEP " + simulationContext.getStepsNumber() +
                " ; EVENT outcome; OUTCOME " + simulationContext.getOutcome().toString() + "\n" +
                "MINERALS: " + simulationContext.getMineralsAmount() + " WATER: " + simulationContext.getWaterAmount();
        cLog.log(message);
        fLog.log(message);
    }

}
