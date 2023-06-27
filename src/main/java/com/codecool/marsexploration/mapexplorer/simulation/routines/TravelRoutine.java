package com.codecool.marsexploration.mapexplorer.simulation.routines;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

public class TravelRoutine implements Routine {

    private Coordinate destination;

    public TravelRoutine(Coordinate destination) {
        this.destination = destination;
    }

    @Override
    public void performMovement(SimulationContext context) {

        Coordinate roverCurrentCoordinate = context.getRover().getCurrentPosition();

        Coordinate mapDimension = new Coordinate(context.getMap().getDimensionY(), context.getMap().getDimensionX());

        if (coordinateIsInBounds(destination, mapDimension)) {
                int xCoordinate = roverCurrentCoordinate.X();
                int yCoordinate = roverCurrentCoordinate.Y();

                if (xCoordinate > destination.X()) {
                    xCoordinate--;
                } else if (xCoordinate < destination.X()) {
                    xCoordinate++;
                }

                if (yCoordinate > destination.Y()) {
                    yCoordinate--;
                } else if (yCoordinate < destination.Y()) {
                    yCoordinate++;
                }

                context.getRover().setCurrentPosition(new Coordinate(xCoordinate, yCoordinate));

            }
            else {
                throw new IndexOutOfBoundsException("Destination is out of map bounds!");
            }
        }

    @Override
    public boolean shouldBeUsed(SimulationContext context) {
        return context.getOutcome() == ExplorationOutcome.COLONIZABLE;
    }

    private static boolean coordinateIsInBounds(Coordinate coordinate, Coordinate mapDimension) {
        return coordinate.X() >= 1 && coordinate.X() <= mapDimension.X() &&
                coordinate.Y() >= 1 && coordinate.Y() <= mapDimension.Y();
    }

    public Coordinate getDestination() {
        return destination;
    }

    public void setDestination(Coordinate destination) {
        this.destination = destination;
    }

    public boolean checkIfRoverReachedCoordinates(SimulationContext context, Coordinate destination) {
        return context.getRover().getCurrentPosition().equals(destination);
    }
}
