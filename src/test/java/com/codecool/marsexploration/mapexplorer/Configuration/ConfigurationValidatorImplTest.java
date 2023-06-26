package com.codecool.marsexploration.mapexplorer.Configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationValidatorImplTest {


    private ConfigurationValidatorImpl configurationValidator;
    private Config validConfig;
    private Config invalidConfig;

    @BeforeEach
    public void setUp() {
        configurationValidator = new ConfigurationValidatorImpl();

        // Create a valid configuration for testing
        validConfig = new Config(
                "C:\\Users\\kubas\\IdeaProjects\\mars-exploration-2-1q2023-java-Satora1\\src\\main\\resources\\exploration-0.map",
                new Coordinate(0, 0),
                List.of(Resource.WATER, Resource.MINERALS),
                10
        );

        // Create an invalid configuration for testing
        invalidConfig = new Config(
                "",
                null,
                List.of(),
                -5
        );
    }

    @Test
    public void testLandingValidation_ValidConfig_ReturnsTrue() {
        boolean isValid = configurationValidator.landingValidation(validConfig);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testLandingValidation_InvalidConfig_ReturnsFalse() {
        boolean isValid = configurationValidator.landingValidation(invalidConfig);
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testHasEmptyAdjacentCoordinate_ValidConfig_ReturnsTrue() {
        boolean hasEmptyAdjacent = configurationValidator.hasEmptyAdjacentCoordinate(validConfig);
        Assertions.assertTrue(hasEmptyAdjacent);
    }

    @Test
    public void testHasEmptyAdjacentCoordinate_InvalidConfig_ReturnsFalse() {
        boolean hasEmptyAdjacent = configurationValidator.hasEmptyAdjacentCoordinate(invalidConfig);
        Assertions.assertFalse(hasEmptyAdjacent);
    }

    @Test
    public void testMapFilePathNotEmpty_ValidConfig_ReturnsTrue() {
        boolean isNotEmpty = configurationValidator.mapFilePathNotEmpty(validConfig);
        Assertions.assertTrue(isNotEmpty);
    }

    @Test
    public void testMapFilePathNotEmpty_InvalidConfig_ReturnsFalse() {
        boolean isNotEmpty = configurationValidator.mapFilePathNotEmpty(invalidConfig);
        Assertions.assertFalse(isNotEmpty);
    }

    @Test
    public void testValidateResources_ValidConfig_ReturnsTrue() {
        boolean isValid = configurationValidator.validateResources(validConfig);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testValidateResources_InvalidConfig_ReturnsFalse() {
        boolean isValid = configurationValidator.validateResources(invalidConfig);
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testValidateTimeout_ValidConfig_ReturnsTrue() {
        boolean isValid = configurationValidator.validateTimeout(validConfig);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testValidateTimeout_InvalidConfig_ReturnsFalse() {
        boolean isValid = configurationValidator.validateTimeout(invalidConfig);
        Assertions.assertFalse(isValid);
    }
}