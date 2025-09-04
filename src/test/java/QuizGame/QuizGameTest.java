/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package QuizGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

// Test class for QuizGame application - contains comprehensive unit tests
public class QuizGameTest {
    // Test fixture variables - objects to be tested
    private QuizGame quizGame;                 // Main game instance to test
    private Question testQuestion;        // Generic question for basic tests
    private SportsQuestion sportsQuestion;   // Sports category question
    private ScienceQuestion scienceQuestion;    // Science category question
    private MovieQuestion movieQuestion;      // Movie category question
    
    // Setup method that runs before each test case - prepares test environment
    @BeforeEach
    public void setUp() {
        quizGame = new QuizGame();  // Create new QuizGame instance for testing
        
        // Create test questions for each type with sample data
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
        testQuestion = new Question("Test question?", options, 2);          // Generic question
        sportsQuestion = new SportsQuestion("Sports question?", options, 1); // Sports question
        scienceQuestion = new ScienceQuestion("Science question?", options, 3); // Science question
        movieQuestion = new MovieQuestion("Movie question?", options, 4);    // Movie question
    }
    // Test 1: Basic Question functionality - tests core Question class methods
    @Test
    public void TestQuestionCreationAndFunctionality() {
        // Verify question text is stored and retrieved correctly
        assertEquals("Test question?", testQuestion.getQuestionText());
        // Verify options array has correct length (4 options)
        assertEquals(4, testQuestion.getOptions().length);
        // Verify correct answer index is stored properly
        assertEquals(2, testQuestion.getCorrectAnswer());
        // Test that correct answer returns true
        assertTrue(testQuestion.isCorrect(2));
        // Test that incorrect answer returns false
        assertFalse(testQuestion.isCorrect(1));
    }
    
    // Test 2: Inheritance hierarchy - tests proper inheritance structure
    @Test
    public void TestInheritanceHierarchy() {
        // Test that all specialized questions are instances of Question (inheritance test)
        assertTrue(sportsQuestion instanceof Question);   // SportsQuestion is a Question
        assertTrue(scienceQuestion instanceof Question);  // ScienceQuestion is a Question
        assertTrue(movieQuestion instanceof Question);    // MovieQuestion is a Question
        
        // Test that they are instances of their specific types (polymorphism test)
        assertTrue(sportsQuestion instanceof SportsQuestion);   // Verify exact type
        assertTrue(scienceQuestion instanceof ScienceQuestion); // Verify exact type
        assertTrue(movieQuestion instanceof MovieQuestion);     // Verify exact type
    }
    // Test 3: Information hiding (using getters) - tests encapsulation principle
    @Test
    public void TestInformationHiding() {
        // Test that we use getters instead of direct field access (information hiding)
        assertEquals("Sports question?", sportsQuestion.getQuestionText());  // Getter access
        assertEquals("Science question?", scienceQuestion.getQuestionText()); // Getter access
        assertEquals("Movie question?", movieQuestion.getQuestionText());    // Getter access
        
        // Test that options arrays are accessible through getters
        assertNotNull(sportsQuestion.getOptions());   // Options should not be null
        assertNotNull(scienceQuestion.getOptions());  // Options should not be null
        assertNotNull(movieQuestion.getOptions());    // Options should not be null
    }
    
    // Test 4: Question initialization in QuizGame - tests game setup functionality
    @Test
    public void TestQuizGameQuestionsInitialization() {
        // Get questions list from quiz game
        ArrayList<Question> questions = quizGame.getQuestions();
        assertNotNull(questions);  // Questions list should not be null
        assertTrue(questions.size() >= 15); // Should have all 15 questions (5 per category)
        
        // Count questions by type to verify proper initialization
        int sportsCount = 0, scienceCount = 0, movieCount = 0;
        
        // Iterate through all questions and categorize them
        for (Question q : questions) {
            if (q instanceof SportsQuestion) sportsCount++;       // Count sports questions
            else if (q instanceof ScienceQuestion) scienceCount++; // Count science questions
            else if (q instanceof MovieQuestion) movieCount++;    // Count movie questions
        }
        
        // Verify we have questions from all categories (positive counts)
        assertTrue(sportsCount > 0, "Should have sports questions");    // At least 1 sports question
        assertTrue(scienceCount > 0, "Should have science questions");  // At least 1 science question
        assertTrue(movieCount > 0, "Should have movie questions");      // At least 1 movie question
        
        // Verify exact counts (5 each category) - comprehensive initialization test
        assertEquals(5, sportsCount, "Should have 5 sports questions");   // Exactly 5 sports questions
        assertEquals(5, scienceCount, "Should have 5 science questions"); // Exactly 5 science questions
        assertEquals(5, movieCount, "Should have 5 movie questions");     // Exactly 5 movie questions
    }
    
    // Test 5: Correct answer validation - tests answer checking functionality
    @Test
    public void TestCorrectAnswerValidation() {
        // Test sports question answer validation
        assertTrue(sportsQuestion.isCorrect(1));   // Answer 1 should be correct
        assertFalse(sportsQuestion.isCorrect(2));  // Answer 2 should be incorrect
        
        // Test science question answer validation
        assertTrue(scienceQuestion.isCorrect(3));  // Answer 3 should be correct
        assertFalse(scienceQuestion.isCorrect(1)); // Answer 1 should be incorrect
        
        // Test movie question answer validation
        assertTrue(movieQuestion.isCorrect(4));    // Answer 4 should be correct
        assertFalse(movieQuestion.isCorrect(2));   // Answer 2 should be incorrect
    }
    
    // Test 6: Score initialization - tests that score starts at zero
    @Test
    public void TestScoreInitialization() {
        assertEquals(0, quizGame.getScore(), "Score should start at 0");  // Initial score should be 0
    }
    
    // Test 7: Display methods (basic test) - tests that display methods don't crash
    @Test
    public void TestQuestionDisplay() {
        // This tests that the display method doesn't crash when called
        // We can't easily test console output, but we can test that methods execute without throwing exceptions
        assertDoesNotThrow(() -> testQuestion.displayQuestion());      // Generic question display
        assertDoesNotThrow(() -> sportsQuestion.displayQuestion());    // Sports question display
        assertDoesNotThrow(() -> scienceQuestion.displayQuestion());   // Science question display
        assertDoesNotThrow(() -> movieQuestion.displayQuestion());     // Movie question display
    }
     // Test 8: Constructor functionality - tests that constructors work properly for all question types
    @Test
    public void TestConstructors() {
        // Test that constructors work properly for all question types with new data
        String[] newOptions = {"A", "B", "C", "D"};  // New options for testing
        
        // Create new questions using constructors
        SportsQuestion newSports = new SportsQuestion("New sports?", newOptions, 2);
        ScienceQuestion newScience = new ScienceQuestion("New science?", newOptions, 3);
        MovieQuestion newMovie = new MovieQuestion("New movie?", newOptions, 1);
        
        // Verify that constructors properly set question text
        assertEquals("New sports?", newSports.getQuestionText());   // Sports question text
        assertEquals("New science?", newScience.getQuestionText()); // Science question text
        assertEquals("New movie?", newMovie.getQuestionText());     // Movie question text
        
        // Verify that constructors properly set correct answers
        assertEquals(2, newSports.getCorrectAnswer());   // Sports correct answer
        assertEquals(3, newScience.getCorrectAnswer());  // Science correct answer
        assertEquals(1, newMovie.getCorrectAnswer());    // Movie correct answer
    }
}