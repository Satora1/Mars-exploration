package com.codecool.marsexploration.mapexplorer.exploration;

import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;

public class MissionLogger {
    private FileLogger fLog;
    private ConsoleLogger cLog;
    
    public MissionLogger(String path){
        fLog = new FileLogger(path);
        cLog = new ConsoleLogger();
    }

    public void logStep(SimulationContext simulationContext) {
        String message = "STEP " + simulationContext.getStepsNumber() +
                "; EVENT position; UNIT " + simulationContext.getRover().getId() +
                "; POSITION " + simulationContext.getRover().getCurrentPosition().toString();
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
