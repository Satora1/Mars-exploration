package com.codecool.marsexploration.mapexplorer;

import VisualPanel.VisualPanel;
import com.codecool.marsexploration.mapexplorer.Configuration.Config;
import com.codecool.marsexploration.mapexplorer.Configuration.Resource;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulation;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final String workDir = "src/main";
    public static void main(String[] args) {

//update wszystkich obiektów bez ruchu
        String mapFile = workDir + "/resources/exploration-0.map";
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(Resource.WATER);
        resourceList.add(Resource.MINERALS);
        Coordinate landingSpot = new Coordinate(6, 6);

        int timeoutSteps = 500;


        Config config = new Config(mapFile, landingSpot, resourceList, timeoutSteps);
        MarsRover rover = new MarsRover("Rover-1", 3);
        ExplorationSimulation simulation = new ExplorationSimulation(config, rover);
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Mars exploration sprint3");
        VisualPanel vp=new VisualPanel();
        vp.setMarsRover(rover);
        window.add(vp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        vp.StartSimulationThread();
        simulation.run();
        //
        // Add your code here
    }
}

