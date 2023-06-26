package com.codecool.marsexploration.mapexplorer.exploration.routines;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.exploration.SimulationContext;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.*;

public class ExploringRoutine implements Routine {

    private static final Set<Coordinate> visitedTiles = new HashSet<>();
    private static final Random random = new Random();

    @Override
    public void performMovement(SimulationContext context) {
        moveRover(context);
    }

    private static void moveRover(SimulationContext context) {

        Coordinate roverCurrentCoordinate = context.getRover().getCurrentPosition();

        Coordinate mapDimension = new Coordinate(context.getMap().getDimensionY(), context.getMap().getDimensionX());

        Coordinate[] adjacentCoordinatesOfRover = {
                new Coordinate(roverCurrentCoordinate.X(), roverCurrentCoordinate.Y() - 1),
                new Coordinate(roverCurrentCoordinate.X(), roverCurrentCoordinate.Y() + 1),
                new Coordinate(roverCurrentCoordinate.X() - 1, roverCurrentCoordinate.Y()),
                new Coordinate(roverCurrentCoordinate.X() + 1, roverCurrentCoordinate.Y())
        };

        List<Coordinate> newRoverCoordinateList = Arrays.stream(adjacentCoordinatesOfRover)
                  .filter(coordinate -> coordinateIsInBounds(coordinate, mapDimension)).toList();

        if (!newRoverCoordinateList.isEmpty()) {
            int possibleCoordinates = newRoverCoordinateList.size() - 1;


            int newCoordinateIndex = random.nextInt(possibleCoordinates);

            while(visitedTiles.contains(newRoverCoordinateList.get(newCoordinateIndex))) {
                if (visitedTiles.containsAll(newRoverCoordinateList)) {
                    break;
                }
                newCoordinateIndex = random.nextInt(possibleCoordinates + 1);
            }
            context.getRover().setCurrentPosition(newRoverCoordinateList.get(newCoordinateIndex));
            visitedTiles.add(newRoverCoordinateList.get(newCoordinateIndex));
        }
        else {
            throw new IllegalStateException("No valid coordinates");
        }
    }

    private static boolean coordinateIsInBounds(Coordinate coordinate, Coordinate mapDimension) {
        return coordinate.X() >= 1 && coordinate.X() <= mapDimension.X() &&
                coordinate.Y() >= 1 && coordinate.Y() <= mapDimension.Y();
    }


    @Override
    public boolean shouldBeUsed(SimulationContext context) {
        return context.getOutcome() == ExplorationOutcome.UNRESOLVED &&
                context.getStepsToTimeout() != context.getStepsNumber();
    }
}
