package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.Configuration.Config;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.MissionLogger;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.OutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.SuccessAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.TimeoutAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.routines.TravelRoutine;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.*;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codecool.marsexploration.mapexplorer.rovers.RoverAi.Explore;
import static com.codecool.marsexploration.mapexplorer.rovers.RoverAi.Scavenge;

public class MarsRover {

    String id;
    Coordinate currentPosition;
    int sight;
    RoverAi currentMotive;
    List<Coordinate> resourceCoordinates = new ArrayList<>();

    Map<String, RoverAction> actions;
    Coordinate target1;
    Coordinate target2;


    public MarsRover(String id, int sight) {
        this.id = id;
        this.sight = sight;

        actions = new HashMap<>();
        actions.put("Movement", new Movement());
        actions.put("Scan", new Scan());

        ArrayList<OutcomeAnalyzer> analyzers = new ArrayList<>();
        analyzers.add(new SuccessAnalyzer());
        analyzers.add(new TimeoutAnalyzer());

        actions.put("Analyze", new Analyze(analyzers));
        currentMotive = Explore;

    }

    public void runRover(SimulationContext simContext){
        switch(currentMotive){
            case Explore ->{
                actions.get("Movement").roverDoAction(simContext, this);
                actions.get("Scan").roverDoAction(simContext, this);
                actions.get("Analyze").roverDoAction(simContext, this);
                break;
            }
            case Build -> {

                if(currentPosition.equals(target1)){
                    actions.get("Build").roverDoAction(simContext, this);
                    currentMotive = Scavenge;
                }
                else{
                    actions.get("Travel to target1").roverDoAction(simContext, this);
                }
                break;
            }
            case Scavenge -> {
                if(currentPosition.equals(target1)){

                } else if (currentPosition.equals(target2)) {

                }
                actions.get("")

            }
        }
    }

    public void setAi(RoverAi ai){currentMotive = ai;}

    public String getId() {
        return id;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public int getSight() {
        return sight;
    }

    public List<Coordinate> getResourceCoordinates() {
        return resourceCoordinates;
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void addResourceCoordinate(Coordinate coordinate) {
        resourceCoordinates.add(coordinate);
    }

    public void setTarget(Coordinate target){
        target1 = target;
        actions.put("Travel to target1", new GoToMovement(new TravelRoutine(target1)));
    }
    public void setTarget(Coordinate target1, Coordinate target2){
        this.target1 = target1;
        this.target2 = target2;
        actions.put("Travel to target1", new GoToMovement(new TravelRoutine(target1)));
        actions.put("Travel to target2", new GoToMovement(new TravelRoutine(target2)));
    }
}