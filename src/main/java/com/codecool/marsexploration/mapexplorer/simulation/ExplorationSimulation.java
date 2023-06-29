package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.Configuration.Config;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.RoverAi;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.OutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.SuccessAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.TimeoutAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.routines.ExploringRoutine;
import com.codecool.marsexploration.mapexplorer.simulation.routines.ReturningRoutine;
import com.codecool.marsexploration.mapexplorer.simulation.routines.Routine;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.Analyze;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.Movement;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.RoverAction;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.Scan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class ExplorationSimulation {

    private SimulationContext simContext;
    private Queue <RoverAction> steps;
    private MissionLogger missionLogger;

    private boolean buildCommandCenter = true;
    RoverDeployer roverDeployer;

    public ExplorationSimulation(Config configuration, MarsRover rover){

        Map map = MapLoader.load(configuration.mapFilePath());



        simContext = new SimulationContext(
                0,
                configuration.timeoutSteps(),
                rover,
                configuration.landingCoordinates(),
                MapLoader.load(configuration.mapFilePath()),
                configuration.resources(),
                ExplorationOutcome.UNRESOLVED);

        roverDeployer = new RoverDeployer();
        roverDeployer.placeRover(rover, simContext.getMap(), configuration);



        missionLogger = new MissionLogger("src/main/resources/missionLog.txt");
    }

    public void run(){
        while(simContext.stepsToTimeout > simContext.stepsNumber){
            if (simContext.getOutcome() != ExplorationOutcome.UNRESOLVED) {
                if(simContext.getOutcome() == ExplorationOutcome.COLONIZABLE && buildCommandCenter){
                    simContext.getRover().get(0).setAi(RoverAi.Build);
                    simContext.getRover().get(0).setTarget(new Coordinate(10, 10));
                    System.out.println("Rover: Build a command center");
                    buildCommandCenter = false;
                }
            }
            simContext.getRover().forEach(e->{
                e.runRover(simContext);
                missionLogger.logStep(simContext, e);
            });
            simContext.getBuildingList().forEach(e->{
                Optional<MarsRover> newRover = e.work((simContext));
                if(newRover.isPresent()) {
                    roverDeployer.placeRover(newRover.get(), simContext.getMap(), e);
                    newRover.get().setAi(RoverAi.Scavenge);
                    newRover.get().setTarget(e.getBaseCoordinate(), simContext.popWater());
                    simContext.addRover(newRover.get());
                }
            });


            simContext.raiseStep();
        }
        missionLogger.logOutcome(simContext);
    }


}
