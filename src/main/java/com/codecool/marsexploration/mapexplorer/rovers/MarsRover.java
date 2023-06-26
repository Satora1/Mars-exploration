package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

public class MarsRover {

    String id;
    Coordinate currentPosition;
    int sight;
    List<Coordinate> resourceCoordinates = new ArrayList<>();

    public MarsRover(String id, int sight) {
        this.id = id;
        this.sight = sight;
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