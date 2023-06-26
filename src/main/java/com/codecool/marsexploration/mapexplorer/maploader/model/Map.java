package com.codecool.marsexploration.mapexplorer.maploader.model;

public class Map {
    private String[][] representation;
    private boolean successfullyGenerated;

    public Map(String[][] representation, boolean successfullyGenerated) {
        this.representation = representation;
        this.successfullyGenerated = successfullyGenerated;
    }

    public int getDimensionX() {
        return representation.length;
    }
    public int getDimensionY(){ return representation[0].length;}

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (String[] strings : arr) {
            StringBuilder s = new StringBuilder();
            for (String string : strings) {
                s.append(string == null ? " " : string);
            }

            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public String getByCoordinate(Coordinate coordinate) {
        return representation[coordinate.X()][coordinate.Y()];
    }

    public boolean isEmpty(Coordinate coordinate) {
        int x = coordinate.X();
        int y = coordinate.Y();

        if (x >= 0 && x < representation.length && y >= 0 && y < representation[x].length) {
            String value = representation[x][y];
            return value == null || value.isEmpty() || value.equals(" ");
        }


        return false;
    }

    public boolean inBounds(Coordinate coordinate){
        if(coordinate.X() >= 0 && coordinate.X() < getDimensionX() &&
                coordinate.Y()>=0 && coordinate.Y()< getDimensionY()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}
