package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.Configuration.Config;
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
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.ExplorationMovement;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.RoverAction;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.Scan;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ExplorationSimulation {

    private SimulationContext simContext;
    private Queue <RoverAction> steps;
    private ExplorationMovement movementRoutines;
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
        movementRoutines = new ExplorationMovement(routineList, new ExploringRoutine());

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
