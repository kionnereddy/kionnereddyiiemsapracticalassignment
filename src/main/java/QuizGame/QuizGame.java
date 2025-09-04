/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuizGame;

/**
 *
 * @author kionn
 */
import java.util.ArrayList;
import java.util.Scanner;

// Base class for all questions using inheritance - demonstrates OOP principles
class Question {
    // Private fields for information hiding - encapsulation principle
    private String questionText;   // Stores the actual question text
    private String[] options;        // Array to store multiple choice options
    private int correctAnswer;        // Index of the correct answer (1-4)
    
    // Constructor to initialize question object with all required data
    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;     // Set the question text
        this.options = options;               // Set the answer options array
        this.correctAnswer = correctAnswer;   // Set the index of correct answer
    }
    // Getter methods for information hiding - provide controlled access to private fields
    public String getQuestionText() {
        return questionText;  // Return the question text
    }
    
    public String[] getOptions() {
        return options;       // Return the options array
    }
    
    public int getCorrectAnswer() {
        return correctAnswer; // Return the correct answer index
    }
    
    // Method to display question and options to the user
    public void displayQuestion() {
        System.out.println("\n" + questionText);  // Print question with spacing
        // Loop through all options and display them numbered
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);  // Display option with number
        }
    }
    // Method to check if user's answer is correct
    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAnswer;  // Compare user answer with correct answer
    }
}

// Inherited class for Sports questions - demonstrates inheritance
class SportsQuestion extends Question {
    // Constructor that calls parent constructor using super()
    public SportsQuestion(String questionText, String[] options, int correctAnswer) {
        super(questionText, options, correctAnswer); // Call parent constructor
    }
}

// Inherited class for Science questions - demonstrates inheritance
class ScienceQuestion extends Question {
    // Constructor that calls parent constructor using super()
    public ScienceQuestion(String questionText, String[] options, int correctAnswer) {
        super(questionText, options, correctAnswer); // Call parent constructor
    }
}

// Inherited class for Movie questions - demonstrates inheritance
class MovieQuestion extends Question {
    // Constructor that calls parent constructor using super()
    public MovieQuestion(String questionText, String[] options, int correctAnswer) {
        super(questionText, options, correctAnswer); // Call parent constructor
    }
}

// Main application class - manages the quiz game functionality
public class QuizGame {
    private ArrayList<Question> questions; // Using ArrayList to store all questions dynamically
    private Scanner scanner;               // Scanner object for user input
    private int score;                     // Variable to track user's current score
    
    // Constructor to initialize the quiz game
    public QuizGame() {
        questions = new ArrayList<>();  // Initialize empty questions list
        scanner = new Scanner(System.in); // Initialize scanner for user input
        score = 0;                      // Initialize score to zero
        initializeQuestions();          // Load all questions into the game
    }
    
    // Main method - entry point of the application
    public static void main(String[] args) {
        QuizGame game = new QuizGame();  // Create game instance
        game.startGame();                // Start the game
    }
    
    // Method to start the game and display welcome message
    public void startGame() {
        System.out.println(" WELCOME TO THE QUIZ GAME! ");  // Welcome message
        System.out.println("********************************");  // Decorative line
        
        displayMainMenu();  // Show the main menu to user
    }
    
    // Main menu display with navigation options
    private void displayMainMenu() {
        // Continuous loop to keep showing menu until user exits
        while (true) {
            System.out.println("\n=== MAIN MENU ===");  // Menu header
            // Display all available quiz categories and options
            System.out.println("1. Start Sports Quiz");
            System.out.println("2. Start Science Quiz");
            System.out.println("3. Start Movies Quiz");
            System.out.println("4. View High Scores");
            System.out.println("5. Exit Game");
            
            System.out.print("Enter your choice (1-5): ");  // Prompt for user input
            int choice = getIntInput();  // Get validated integer input
            
            // Switch statement to handle user's menu choice
            switch (choice) {
                case 1:
                    startQuiz("Sports");  // Start sports quiz
                    break;
                case 2:
                    startQuiz("Science"); // Start science quiz
                    break;
                case 3:
                    startQuiz("Movies");  // Start movies quiz
                    break;
                case 4:
                    viewHighScores();     // Show high scores
                    break;
                case 5:
                    System.out.println("Thanks for playing! Goodbye! ");  // Exit message
                    return;  // Exit the method and program
                default:
                    System.out.println("Invalid choice! Please enter 1-5.");  // Error message
            }
        }
    }
    
    // Method to start a specific category quiz
    private void startQuiz(String category) {
        score = 0; // Reset score for new quiz session
        System.out.println("\n=== " + category.toUpperCase() + " QUIZ ===");  // Quiz header
        System.out.println("Get ready for 5 questions! Good luck! ");       // Encouragement message
        
        // Loop through all questions to find ones from selected category
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);  // Get current question
            
            // Check if question belongs to selected category using instanceof
            boolean isCorrectCategory = false;
            if (category.equals("Sports") && question instanceof SportsQuestion) {
                isCorrectCategory = true;  // It's a sports question
            } else if (category.equals("Science") && question instanceof ScienceQuestion) {
                isCorrectCategory = true;  // It's a science question
            } else if (category.equals("Movies") && question instanceof MovieQuestion) {
                isCorrectCategory = true;  // It's a movie question
            }
            
            // If question matches category, ask it
            if (isCorrectCategory) {
                askQuestion(question, i + 1);  // Ask the question with number
            }
        }
        
        displayFinalScore();  // Show final score after quiz completion
    }
    
    // Method to handle asking a single question and processing answer
    private void askQuestion(Question question, int questionNumber) {
        question.displayQuestion();  // Display the question and options
        System.out.print("Your answer (1-4): ");  // Prompt for answer
        
        int userAnswer = getIntInput();  // Get user's answer
        
        // Validate input to ensure it's between 1-4
        while (userAnswer < 1 || userAnswer > 4) {
            System.out.print("Invalid choice! Enter 1-4: ");  // Error message
            userAnswer = getIntInput();  // Get new input
        }
        
        // Check if answer is correct and provide feedback
        if (question.isCorrect(userAnswer)) {
            System.out.println(" Correct! Well done!");  // Positive feedback
            score += 10; // Add 10 points for correct answer
        } else {
            System.out.println(" Wrong! The correct answer was: " + question.getCorrectAnswer());  // Correct answer
        }
        
        System.out.println("Current score: " + score);  // Show updated score
    }
    
    // Method to display final score and performance feedback
    private void displayFinalScore() {
        System.out.println("\n QUIZ COMPLETE! ");  // Completion message
        System.out.println("====================");     // Separator
        System.out.println("Your final score: " + score + "/50");  // Final score display
        
        // Provide personalized feedback based on score
        if (score >= 40) {
            System.out.println(" Excellent! You're a quiz master!");  // Top performance
        } else if (score >= 25) {
            System.out.println(" Good job! Keep learning!");         // Good performance
        } else {
            System.out.println(" Keep practicing! You'll get better!");  // Encouragement
        }
        System.out.println("====================");  // Separator
    }
    
    // Method to display high scores (simplified static version)
    private void viewHighScores() {
        System.out.println("\n=== HIGH SCORES ===");  // High scores header
        // Display sample high scores
        System.out.println("1. Quiz Master - 50 points");
        System.out.println("2. Smart Player - 40 points");
        System.out.println("3. Good Try - 30 points");
        System.out.println("===================");
        System.out.println("Your best: " + score + " points");  // Show user's current best
    }
    
    // Method to initialize all quiz questions with answers
    private void initializeQuestions() {
        // Sports questions section - 5 questions
        questions.add(new SportsQuestion("Which country won the FIFA World Cup in 2018?",
            new String[]{"Brazil", "Germany", "France", "Argentina"}, 3));  // France is correct
        
        questions.add(new SportsQuestion("How many players are in a rugby team?",
            new String[]{"11", "13", "15", "17"}, 3));  // 15 is correct
        
        questions.add(new SportsQuestion("Which sport uses a shuttlecock?",
            new String[]{"Tennis", "Badminton", "Squash", "Table Tennis"}, 2));  // Badminton is correct
        
        questions.add(new SportsQuestion("In basketball, how many points is a free throw worth?",
            new String[]{"1", "2", "3", "4"}, 1));  // 1 point is correct
        
        questions.add(new SportsQuestion("Which athlete is known as 'The Lightning Bolt'?",
            new String[]{"Michael Phelps", "Usain Bolt", "Cristiano Ronaldo", "LeBron James"}, 2));  // Usain Bolt is correct
        
        // Science questions section - 5 questions
        questions.add(new ScienceQuestion("What is the chemical symbol for gold?",
            new String[]{"Go", "Gd", "Au", "Ag"}, 3));  // Au is correct
        
        questions.add(new ScienceQuestion("Which planet is known as the Red Planet?",
            new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 2));  // Mars is correct
        
        questions.add(new ScienceQuestion("What is the largest organ in the human body?",
            new String[]{"Liver", "Brain", "Skin", "Heart"}, 3));  // Skin is correct
        
        questions.add(new ScienceQuestion("What is H2O commonly known as?",
            new String[]{"Oxygen", "Hydrogen", "Water", "Carbon Dioxide"}, 3));  // Water is correct
        
        questions.add(new ScienceQuestion("Which gas do plants absorb from the atmosphere?",
            new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"}, 3));  // CO2 is correct
        
        // Movie questions section - 5 questions
        questions.add(new MovieQuestion("Who directed the movie 'Titanic'?",
            new String[]{"Steven Spielberg", "James Cameron", "Christopher Nolan", "George Lucas"}, 2));  // James Cameron is correct
        
        questions.add(new MovieQuestion("Which actor played Iron Man in the Marvel movies?",
            new String[]{"Chris Evans", "Chris Hemsworth", "Robert Downey Jr.", "Mark Ruffalo"}, 3));  // RDJ is correct
        
        questions.add(new MovieQuestion("What year was the first Harry Potter movie released?",
            new String[]{"1999", "2001", "2003", "2005"}, 2));  // 2001 is correct
        
        questions.add(new MovieQuestion("Which movie won Best Picture at the 2020 Oscars?",
            new String[]{"1917", "Joker", "Parasite", "Once Upon a Time in Hollywood"}, 3));  // Parasite is correct
        
        questions.add(new MovieQuestion("Who played the Joker in the 2019 film?",
            new String[]{"Heath Ledger", "Joaquin Phoenix", "Jack Nicholson", "Jared Leto"}, 2));  // Joaquin Phoenix is correct
    }
    // Helper method for safe integer input with validation
    private int getIntInput() {
        // Continuous loop until valid integer is provided
        while (true) {
            try {
                return scanner.nextInt();  // Try to read integer input
            } catch (Exception e) {
                System.out.print("Invalid input! Please enter a number: ");  // Error message
                scanner.next(); // Clear invalid input from scanner buffer
            }
        }
    }
    
    // Getter methods for unit testing purposes
    public ArrayList<Question> getQuestions() {
        return questions;  // Return the questions list for testing
    }
    
    public int getScore() {
        return score;      // Return current score for testing
    }
}