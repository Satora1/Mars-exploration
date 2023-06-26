package com.codecool.marsexploration.mapexplorer.Configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;

import java.util.List;


public class ConfigurationValidatorImpl implements ConfigurationValidator {


    @Override
    public boolean landingValidation(Config config) {
        Coordinate landingCoordinates = config.landingCoordinates();
        Map map = MapLoader.load(config.mapFilePath());

        if (landingCoordinates == null || map == null) {
            return false;
        }

        return map.isEmpty(landingCoordinates);
    }

    @Override
    public boolean hasEmptyAdjacentCoordinate(Config config) {
        Coordinate landingCoordinates = config.landingCoordinates();
        Map map = MapLoader.load(config.mapFilePath());

        if (landingCoordinates == null || map == null) {
            return false;
        }

        Coordinate[] adjacentCoordinates = {
                new Coordinate(landingCoordinates.X(), landingCoordinates.Y() - 1),
                new Coordinate(landingCoordinates.X(), landingCoordinates.Y() + 1),
                new Coordinate(landingCoordinates.X() - 1, landingCoordinates.Y()),
                new Coordinate(landingCoordinates.X() + 1, landingCoordinates.Y())
        };

        for (Coordinate adjacentCoordinate : adjacentCoordinates) {
            if (map.isEmpty(adjacentCoordinate)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean mapFilePathNotEmpty(Config config) {

        return config.mapFilePath() != null && !config.mapFilePath().isEmpty();
    }

    @Override
    public boolean validateResources(Config config) {
        List<Resource> resources = config.resources();

        if (resources == null || resources.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateTimeout(Config config) {
        int timeoutSteps = config.timeoutSteps();

        return timeoutSteps > 0;
    }

}
