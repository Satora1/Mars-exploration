package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.Configuration.Config;
import com.codecool.marsexploration.mapexplorer.Configuration.Resource;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulation;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) {
        String mapFile = workDir + "/resources/exploration-0.map";
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(Resource.WATER);
        resourceList.add(Resource.MINERALS);
        Coordinate landingSpot = new Coordinate(6, 6);
        int timeoutSteps = 500;

        Config config = new Config(mapFile, landingSpot, resourceList, timeoutSteps);
        MarsRover rover = new MarsRover("Rover-1", 3);

        ExplorationSimulation simulation = new ExplorationSimulation(config, rover);
        simulation.run();

        // Add your code here
    }
}

