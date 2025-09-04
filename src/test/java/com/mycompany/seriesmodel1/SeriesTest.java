/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.seriesmodel1;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test class for Series application - contains unit tests to verify functionality
public class SeriesTest {
    // Instance variables for test setup
    private Series seriesApp;      // Main application instance to test
    private SeriesModel testSeries; // Test data model instance
    // Setup method that runs before each test case
    @BeforeEach
    public void setUp() {
        // Create new instance of Series application for testing
        seriesApp = new Series();
        
        // Create a test series with sample data
        testSeries = new SeriesModel();
        testSeries.SeriesId = "101";                    // Set test series ID
        testSeries.SeriesName = "Test Series";          // Set test series name
        testSeries.SeriesAge = "12";                    // Set test age restriction
        testSeries.SeriesNumberOfEpisodes = "10";       // Set test episode count
        
        // Create ArrayList and add test series to it
        ArrayList<SeriesModel> seriesList = new ArrayList<>();
        seriesList.add(testSeries);                     // Add test series to list
        
        // Set the prepared list in the application instance
        seriesApp.setSeriesList(seriesList);
    }
    
    // Test case to verify successful series search functionality
    @Test
    public void TestSearchSeries() {
        // Test that the correct series data is returned when searching for existing ID
        SeriesModel result = seriesApp.SearchSeries("101");
        
        // Assertions to verify all series data is correctly returned
        assertNotNull(result, "Series should be found");             // Verify series was found
        assertEquals("101", result.SeriesId);                           // Verify correct ID
        assertEquals("Test Series", result.SeriesName);                // Verify correct name
        assertEquals("12", result.SeriesAge);                           // Verify correct age restriction
        assertEquals("10", result.SeriesNumberOfEpisodes);             // Verify correct episode count
    }
    // Test case to verify handling of non-existent series search
    @Test
    public void TestSearchSeries_SeriesNotFound() {
        // Test that no series is found when searching with incorrect ID
        SeriesModel result = seriesApp.SearchSeries("999");
        
        // Assert that null is returned when series doesn't exist
        assertNull(result, "Series should not be found");
    }
    
    // Test case to verify series update functionality
    @Test
    public void TestUpdateSeries() {
        // Test that the series is successfully updated with new data
        boolean result = seriesApp.UpdateSeries("101", "Updated Series", "14", "15");
        
        // Verify update operation returned success
        assertTrue(result, "Series should be updated successfully");
        
        // Verify the updates were actually applied to the series
        SeriesModel updatedSeries = seriesApp.SearchSeries("101");
        assertEquals("Updated Series", updatedSeries.SeriesName);     // Verify updated name
        assertEquals("14", updatedSeries.SeriesAge);                    // Verify updated age
        assertEquals("15", updatedSeries.SeriesNumberOfEpisodes);          // Verify updated episode count
    }
    
    // Test case to verify successful series deletion
    @Test
    public void TestDeleteSeries() {
        // Test that the series is successfully deleted from the system
        boolean result = seriesApp.DeleteSeries("101");
        
        // Verify deletion operation returned success
        assertTrue(result, "Series should be deleted successfully");
        // Verify the series list is now empty after deletion
        assertEquals(0, seriesApp.getSeriesList().size(), "Series list should be empty");
    }
    
    // Test case to verify handling of deletion attempt for non-existent series
    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        // Test that series is not deleted when using incorrect ID
        boolean result = seriesApp.DeleteSeries("999");
        
        // Verify deletion operation returned failure
        assertFalse(result, "Series should not be deleted");
        // Verify the series list still contains the original item
        assertEquals(1, seriesApp.getSeriesList().size(), "Series list should still have one item");
    }
     // Test case to validate age restriction verification with valid inputs
    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        // Test that valid age restrictions are accepted by the validation method
        assertTrue(seriesApp.validateAgeRestriction("2"), "Age 2 should be valid");     // Minimum valid age
        assertTrue(seriesApp.validateAgeRestriction("12"), "Age 12 should be valid");   // Middle valid age
        assertTrue(seriesApp.validateAgeRestriction("18"), "Age 18 should be valid");   // Maximum valid age
    }
    
    // Test case to validate age restriction verification with invalid inputs
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        // Test that invalid age restrictions are rejected by the validation method
        assertFalse(seriesApp.validateAgeRestriction("1"), "Age 1 should be invalid");      // Below minimum
        assertFalse(seriesApp.validateAgeRestriction("19"), "Age 19 should be invalid");    // Above maximum
        assertFalse(seriesApp.validateAgeRestriction("0"), "Age 0 should be invalid");      // Zero age
        assertFalse(seriesApp.validateAgeRestriction("100"), "Age 100 should be invalid");  // Very high age
        assertFalse(seriesApp.validateAgeRestriction("abc"), "Non-numeric age should be invalid"); // Non-numeric
        assertFalse(seriesApp.validateAgeRestriction(""), "Empty age should be invalid");   // Empty input
    }
}