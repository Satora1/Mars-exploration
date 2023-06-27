package com.codecool.marsexploration.mapexplorer.simulation.roveraction;

import com.codecool.marsexploration.mapexplorer.Configuration.Resource;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

import java.util.*;

public class Scan implements RoverAction{

    private final Set<Coordinate> scannedTiles = new HashSet<>();
    private final Set<Coordinate> tilesToScan = new HashSet<>();

    private int amountOfWaterFound = 0;

    private int amountOfMineralsFound = 0;


    @Override
    public void roverDoAction(SimulationContext context) {


        int roverSightSize = context.getRover().getSight();

        if (roverSightSize <= 0) {
            throw new IllegalStateException("Sight of the Rover can't be less than 1. It must be broken!");
        }

        int scanRoundCounter = 0;

        Coordinate scanStartingPoint = context.getRover().getCurrentPosition();

        Coordinate[] tilesAdjacentToScanStartingPoint = generateCoordinates(scanStartingPoint);

        scanRoundCounter++;

        tilesToScan.addAll(Arrays.asList(tilesAdjacentToScanStartingPoint));

        List<Resource> resources = context.getResources();


        scanTiles(context, resources);

        scannedTiles.addAll(tilesToScan);
        tilesToScan.clear();


        while (scanRoundCounter <= roverSightSize) {

            for (Coordinate coordinate : tilesAdjacentToScanStartingPoint) {
                Coordinate[] newCoordinatesForScanning = generateCoordinates(coordinate);

                for (Coordinate coordinateToScan : newCoordinatesForScanning) {

                    if (!scannedTiles.contains(coordinateToScan)) {
                        tilesToScan.add(coordinateToScan);
                    }
                }

                scanTiles(context, resources);
                scannedTiles.addAll(tilesToScan);
                tilesToScan.clear();
            }
            scanRoundCounter++;
        }

        context.addToMineralsAmount(amountOfMineralsFound);
        context.addToWaterAmount(amountOfWaterFound);

        amountOfMineralsFound = 0;
        amountOfWaterFound = 0;

    }

    private static Coordinate[] generateCoordinates(Coordinate scanStartingPoint) {
        return new Coordinate[] {
                new Coordinate(scanStartingPoint.X(), scanStartingPoint.Y() - 1),
                new Coordinate(scanStartingPoint.X(), scanStartingPoint.Y() + 1),
                new Coordinate(scanStartingPoint.X() - 1, scanStartingPoint.Y()),
                new Coordinate(scanStartingPoint.X() + 1, scanStartingPoint.Y())
        };
    }

    private void scanTiles(SimulationContext context, List<Resource> resources) {
        for (Coordinate coordinate : tilesToScan) {
            if(context.getMap().inBounds(coordinate)) {
                    if (context.getMap().getByCoordinate(coordinate).equals(Resource.MINERALS.getSymbol())) {
                            amountOfMineralsFound++;
                    } else if (context.getMap().getByCoordinate(coordinate).equals(Resource.WATER.getSymbol())) {
                            amountOfWaterFound++;
                        }
                    }
            }
        }

}
