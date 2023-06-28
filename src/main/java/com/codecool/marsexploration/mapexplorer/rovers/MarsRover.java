package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.Configuration.Config;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.MissionLogger;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.OutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.SuccessAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.analyzers.TimeoutAnalyzer;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.Analyze;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.Movement;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.RoverAction;
import com.codecool.marsexploration.mapexplorer.simulation.roveraction.Scan;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codecool.marsexploration.mapexplorer.rovers.RoverAi.Explore;

public class MarsRover {

    String id;
    Coordinate currentPosition;
    int sight;
    RoverAi currentMotive;
    List<Coordinate> resourceCoordinates = new ArrayList<>();

    Map<String, RoverAction> actions;


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
                break;
            }
            case Scavenge -> {

            }
        }
    }

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
}