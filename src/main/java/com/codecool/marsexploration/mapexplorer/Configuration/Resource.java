package com.codecool.marsexploration.mapexplorer.Configuration;

public enum Resource {
    MOUNTAINS("#"),
    PITS("&"),
    MINERALS("%"),
    WATER("*");

    private final String symbol;

    Resource(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Resource fromSymbol(String symbol) {
        for (Resource resource : Resource.values()) {
            if (resource.symbol.equals(symbol)) {
                return resource;
            }
        }
        throw new IllegalArgumentException("Invalid resource symbol: " + symbol);
    }
}
