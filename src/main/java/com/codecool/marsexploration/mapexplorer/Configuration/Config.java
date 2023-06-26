package com.codecool.marsexploration.mapexplorer.Configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.List;

public record Config(
        String mapFilePath,
       Coordinate landingCoordinates,
        List<Resource> resources,
        int timeoutSteps)
{}
