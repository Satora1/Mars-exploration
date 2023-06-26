package com.codecool.marsexploration.mapexplorer.exploration;

import com.codecool.marsexploration.mapexplorer.Configuration.Config;
import com.codecool.marsexploration.mapexplorer.exploration.analyzers.OutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.exploration.analyzers.SuccessAnalyzer;
import com.codecool.marsexploration.mapexplorer.exploration.analyzers.TimeoutAnalyzer;
import com.codecool.marsexploration.mapexplorer.exploration.routines.ExploringRoutine;
import com.codecool.marsexploration.mapexplorer.exploration.routines.ReturningRoutine;
import com.codecool.marsexploration.mapexplorer.exploration.routines.Routine;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverDeployer;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ExplorationSimulation {

    private SimulationContext simContext;
    private Queue <RoverAction> steps;
    private Movement movementRoutines;
    private Scan scan;
    private Analyze analyze;
    private MissionLogger missionLogger;

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

        RoverDeployer roverDeployer = new RoverDeployer();
        roverDeployer.placeRover(rover, simContext.getMap(), configuration);

        List<Routine> routineList = new ArrayList<>();
        routineList.add(new ExploringRoutine());
        routineList.add(new ReturningRoutine());
        movementRoutines = new Movement(routineList, new ExploringRoutine());

        scan = new Scan();

        ArrayList<OutcomeAnalyzer> analyzers = new ArrayList<>();
        analyzers.add(new SuccessAnalyzer());
        analyzers.add(new TimeoutAnalyzer());
        analyze = new Analyze(analyzers);

        missionLogger = new MissionLogger("src/main/resources/missionLog.txt");
    }

    public void run(){
        while(simContext.stepsToTimeout > simContext.stepsNumber){
           // ArrayList<RoverAction> Actions = generateSteps();
            //Actions.forEach(e->e.roverDoAction(simContext));
            if (simContext.getOutcome() != ExplorationOutcome.UNRESOLVED) {
                System.out.println("Mission end.");
                break;
            }
            movementRoutines.roverDoAction(simContext);
            scan.roverDoAction(simContext);
            analyze.roverDoAction(simContext);
            missionLogger.logStep(simContext);
            simContext.raiseStep();
        }
        missionLogger.logOutcome(simContext);
    }

    private ArrayList<RoverAction> generateSteps(){
        ArrayList<RoverAction> steps = new ArrayList<>();
        steps.add(movementRoutines);
        steps.add(scan);
        steps.add(analyze);
        return steps;
    }
}
