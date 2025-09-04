/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.seriesmodel1;

/**
 *
 * @author kionn
 */
import java.util.ArrayList;
import java.util.Scanner;

// Model class to represent a TV series with its properties
class SeriesModel {
    // Public fields to store series information and data
    public String SeriesId;          // unique identification for the series
    public String SeriesName;        // Name of the TV series
    public String SeriesAge;         // Age restriction for the series
    public String SeriesNumberOfEpisodes; // Total number of episodes in the series
}
// Main application class for managing TV series
public class Series {
    // ArrayList to store all series data in memory
    private ArrayList<SeriesModel> seriesList;
    // Scanner object to read user input from console
    private Scanner scanner;
    
    // Constructor to initialize the application
    public Series(){
        seriesList = new ArrayList<>();  // Create empty list for series storage
        scanner = new Scanner(System.in); // Initialize scanner for user input
    }
    
    // Getter method for unit testing - allows access to series list
    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }
    
    // Setter method for unit testing - allows setting series list for testing
    public void setSeriesList(ArrayList<SeriesModel> seriesList) {
        this.seriesList = seriesList;
    }
    // Main method - entry point of the application
    public static void main(String[] args) {
        Series app = new Series();  // Create application instance
        app.startApplication();     // Start the application
    }
    
    // Method to start the application and display initial prompt
    public void startApplication() {
        // Display application header
        System.out.println("LATEST SERIES - 2025");
        System.out.println("*********************************************");
        // Prompt user to launch menu or exit
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        
        // Read user input
        String input = scanner.nextLine();
        if (input.equals("1")) {
            displayMenu();  // Show main menu if user enters 1
        }else {
            ExitSeriesApplication();  // Exit application for any other input
        }
    }
    
    // Method to display and handle the main menu
    private void displayMenu() {
        // Continuous loop to keep showing menu until user exits
        while (true) {
            // Display all menu options
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series ");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            
            // Prompt user for menu choice
            System.out.print("Enter an option: ");
            String choice = scanner.nextLine();
            
            // Switch statement to handle user's menu choice
            switch (choice) {
                case "1":
                    CaptureSeries();  // Call method to capture new series
                    break;
                case "2":
                    SearchSeries();   // Call method to search for series
                    break;
                case "3":
                    UpdateSeries();   // Call method to update existing series
                    break;
                case "4":
                    DeleteSeries();   // Call method to delete series
                    break;
                case "5":
                    SeriesReport();   // Call method to generate report
                    break;
                case "6":
                    ExitSeriesApplication();  // Exit the application
                    return;  // Exit the loop and method
                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid option, Please try again.");
            }
        }
    }
    
    // Method to capture and store a new TV series
    public void CaptureSeries() {
        // Display capture screen header
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("************");
        
        // Create new SeriesModel object to store series data
        SeriesModel series = new SeriesModel();
        
        // Get series ID from user
        System.out.print("Enter the series ID: ");
        series.SeriesId = scanner.nextLine();
        
        // Get series name from user
        System.out.print("Enter the series name: ");
        series.SeriesName = scanner.nextLine();
        
        // Validate age restriction input with continuous loop until valid
        while (true) {
            System.out.print("Enter the series age restriction: ");
            String ageInput = scanner.nextLine();
            
            try {
                // Try to convert input to integer
                int age = Integer.parseInt(ageInput);
                // Check if age is within valid range (2-18)
                if (age >= 2 && age <= 18) {
                    series.SeriesAge = ageInput;  // Store valid age
                    break;  // Exit validation loop
                } else {
                    // Inform user of invalid age range
                    System.out.println("Incorrect series age restriction entered");
                    System.out.print("Please retry the series age restriction > ");
                }
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                System.out.println("Incorrect series age restriction entered");
                System.out.print("Please retry the series age restriction > ");
            }
        }
        
        // Get number of episodes from user
        System.out.print("Enter the number of episodes for " + series.SeriesName + ": ");
        series.SeriesNumberOfEpisodes = scanner.nextLine();
        
        // Add the completed series to the list
        seriesList.add(series);
        // Confirm successful capture to user
        System.out.println("Series captured successfully!");
        
        // Return to menu or exit
        returnToMenu();
    }
    
    // Method to search for series by ID (returns SeriesModel for testing)
    public SeriesModel SearchSeries(String seriesId) {
        // Loop through all series in the list
        for (SeriesModel series : seriesList) {
            // Check if current series matches search ID
            if (series.SeriesId.equals(seriesId)) {
                return series;  // Return found series
            }
        }
        return null;  // Return null if series not found
    }
    // Method to handle user interaction for searching series
    public void SearchSeries() {
        // Prompt user for series ID to search
        System.out.print("\nEnter the series ID to search: ");
        String searchId = scanner.nextLine();
        
        // Call search method to find series
        SeriesModel foundSeries = SearchSeries(searchId);
        
        // Check if series was found
        if (foundSeries != null) {
            // Display series details in formatted output
            System.out.println("---");
            System.out.println("SERIES ID: " + foundSeries.SeriesId);
            System.out.println("SERIES NAME: " + foundSeries.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + foundSeries.SeriesAge);
            System.out.println("SERIES NUMBER OF EPISODES: " + foundSeries.SeriesNumberOfEpisodes);
            System.out.println("---");
        } else {
            // Inform user that series was not found
            System.out.println("---");
            System.out.println("Series with Series ID: " + searchId + " was unfortunately not found");
            System.out.println("---");
        }
        
        // Return to menu or exit
        returnToMenu();
    }
    
    // Method to update series information (returns boolean for testing)
    public boolean UpdateSeries(String seriesId, String newName, String newAge, String newEpisodes) {
        // Search for series to update
        SeriesModel foundSeries = SearchSeries(seriesId);
        
        // Return false if series not found
        if (foundSeries == null) {
            return false;
        }
        
        // Update series information with new values
        foundSeries.SeriesName = newName;
        foundSeries.SeriesAge = newAge;
        foundSeries.SeriesNumberOfEpisodes = newEpisodes;
        return true;  // Return true indicating successful update
    }
    
    // Method to handle user interaction for updating series
    public void UpdateSeries() {
        // Prompt user for series ID to update
        System.out.print("\nEnter the series ID to update: ");
        String updateId = scanner.nextLine();
        
        // Search for the series
        SeriesModel foundSeries = SearchSeries(updateId);
        
        // Handle case where series is not found
        if (foundSeries == null) {
            System.out.println("Series with ID " + updateId + " was unfortunately not found");
            returnToMenu();
            return;
        }
        
        // Get new series name from user
        System.out.print("Enter the series name: ");
        String newName = scanner.nextLine();
        
        // Validate new age restriction input
        String newAge;
        while (true) {
            System.out.print("Enter the age restriction: ");
            String ageInput = scanner.nextLine();
            
            try {
                int age = Integer.parseInt(ageInput);
                // Check if age is within valid range
                if (age >= 2 && age <= 18) {
                    newAge = ageInput;  // Store valid age
                    break;  // Exit validation loop
                } else {
                    // Inform user of invalid age
                    System.out.println("Incorrect series age restriction entered");
                    System.out.print("Please retry the series age restriction > ");
                }
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                System.out.println("Incorrect series age entered");
                System.out.print("Please retry the series age restriction > ");
            }
        }
        
        // Get new number of episodes from user
        System.out.print("Enter the number of episodes: ");
        String newEpisodes = scanner.nextLine();
        
        // Call update method and check result
        boolean updated = UpdateSeries(updateId, newName, newAge, newEpisodes);
        if (updated) {
            System.out.println("Series updated successfully!");
        } else {
            System.out.println("Series update failed!");
        }
        
        // Return to menu or exit
        returnToMenu();
    }
    
    // Method to delete series by ID (returns boolean for testing)
    public boolean DeleteSeries(String seriesId) {
        // Search for series to delete
        SeriesModel foundSeries = SearchSeries(seriesId);
        
        // If series found, remove it from list
        if (foundSeries != null) {
            seriesList.remove(foundSeries);
            return true;  // Return true indicating successful deletion
        }
        return false;  // Return false if series not found
    }
    
    // Method to handle user interaction for deleting series
    public void DeleteSeries() {
        // Prompt user for series ID to delete
        System.out.print("\nEnter the series ID to delete: ");
        String deleteId = scanner.nextLine();
        
        // Call delete method and get result
        boolean deleted = DeleteSeries(deleteId);
        
        // Handle deletion result
        if (deleted) {
            // Confirm successful deletion
            System.out.println("---");
            System.out.println("Series with Series ID: " + deleteId + " was successfully deleted");
            System.out.println("---");
        } else {
            // Inform user that series was not found
            System.out.println("Series with ID " + deleteId + " was unfortunately not found");
        }
        
        // Return to menu or exit
        returnToMenu();
    }
    
    // Method to generate and display series report
    public void SeriesReport() {
        // Check if there are any series to report
        if (seriesList.isEmpty()) {
            System.out.println("No series data available!");
            returnToMenu();
            return;
        }
        
        // Loop through all series and display their information
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            // Display series information with formatting
            System.out.println("Series " + (i + 1));
            System.out.println("--- SERIES ID: " + series.SeriesId);
            System.out.println("SERIES NAME: " + series.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
            System.out.println("NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
            System.out.println("---");
        }
        
        // Return to menu or exit
        returnToMenu();
    }
    
    // Method to validate age restriction input (for unit testing)
    public boolean validateAgeRestriction(String ageInput) {
        try {
            // Try to parse input as integer
            int age = Integer.parseInt(ageInput);
            // Check if age is within valid range
            return age >= 2 && age <= 18;
        } catch (NumberFormatException e) {
            // Return false for non-numeric input
            return false;
        }
    }
    
    // Method to cleanly exit the application
    public void ExitSeriesApplication() {
        // Display farewell message
        System.out.println("Thank you for using the Series Management Application. Goodbye!");
        scanner.close();  // Close scanner to release resources
        System.exit(0);   // Terminate application
    }
    
    // Private helper method to return to main menu or exit
    private void returnToMenu() {
        // Prompt user for next action
        System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            displayMenu();  // Show main menu if user chooses
        } else {
            ExitSeriesApplication();  // Exit application otherwise
        }
    }
}