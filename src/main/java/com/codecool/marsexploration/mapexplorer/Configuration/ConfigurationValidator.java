package com.codecool.marsexploration.mapexplorer.Configuration;

public interface ConfigurationValidator {
    boolean landingValidation(Config config);
    boolean hasEmptyAdjacentCoordinate(Config config);
    boolean mapFilePathNotEmpty(Config config);
    boolean validateResources(Config config);
    boolean validateTimeout(Config config);
}
