package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.Configuration.Resource;
import com.codecool.marsexploration.mapexplorer.buildings.Building;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SimulationContext {
    int stepsNumber;
    int stepsToTimeout;
    List<MarsRover> rover;
    Coordinate shipCoordinate;
    private final Map map;
    List<Resource> resources;
    List<Coordinate> foundWater;
    List<Coordinate> foundMineral;
    ExplorationOutcome outcome;

    private int mineralsAmount = 0;
    private int waterAmount = 0;
    private List<Building> buildingList;

    public SimulationContext(
            int steps,
            int timeout,
            MarsRover rover,
            Coordinate shipCoord,
            Map map,
            List<Resource> resources,
            ExplorationOutcome outcome){
        this.stepsNumber = steps;
        this.stepsToTimeout = timeout;
        this.rover = new ArrayList<>();
        this.rover.add(rover);
        this.shipCoordinate = shipCoord;
        this.map = map;
        this.resources = resources;
        this.outcome = outcome;
        this.foundWater = new ArrayList<>();
        this.foundMineral = new ArrayList<>();

        buildingList = new ArrayList<Building>();
    }
    public int getStepsNumber(){return stepsNumber;}
    public void raiseStep(){stepsNumber++;}
    public int getStepsToTimeout(){return stepsToTimeout;}

    public List<MarsRover> getRover(){return rover;}
    public void addRover(MarsRover rover){
        this.rover.add(rover);
    }
    public Coordinate getShipsCoordinate(){return shipCoordinate;}
    public Map getMap(){return map;}
    public List<Resource> getResources(){return resources;}

    public ExplorationOutcome getOutcome() {return outcome;}

    public void setOutcome(ExplorationOutcome newOutcome) {this.outcome = newOutcome;}


    public int getMineralsAmount() {
        return mineralsAmount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void addToMineralsAmount(int mineralsAmount) {
        this.mineralsAmount = this.mineralsAmount + mineralsAmount;
    }

    public void addToWaterAmount(int waterAmount) {
        this.waterAmount = this.waterAmount + waterAmount;
    }


    public void addBuilding(Building building){buildingList.add(building);}

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void addFoundResource(Resource resource, Coordinate coordinate) {
        if (Objects.equals(resource.getSymbol(), Resource.WATER.getSymbol())) {
            foundWater.add(coordinate);
        } else if (Objects.equals(resource.getSymbol(), Resource.MINERALS.getSymbol())) {
            foundMineral.add(coordinate);
        }
    }
    public Coordinate popWater(){
        System.out.println("-------------------------------- "+ foundWater.size());
        return foundWater.remove(foundWater.size()-1);
    }
    public Coordinate popMineral(){
        return foundMineral.remove(foundMineral.lastIndexOf(foundMineral));
    }
}
