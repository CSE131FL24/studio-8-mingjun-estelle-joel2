package studio8;

public class SelectAllQuestion extends MultipleChoiceQuestion {

    // Constructor
    public SelectAllQuestion(String prompt, String answer, String[] choices) {
        super(prompt, answer, choices.length, choices);  // Call the superclass constructor with the appropriate arguments
    }
    
    // Override the checkAnswer method to handle partial credit
    @Override
    public int checkAnswer(String givenAnswer) {
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        
        // Calculate missing correct answers (correct answers the user didn't select)
        int missingCorrectAnswers = findMissingCorrectAnswers(givenAnswer);
        
        // Calculate incorrect answers (choices the user selected that are not correct)
        int incorrectGivenAnswers = findIncorrectGivenAnswers(givenAnswer);
        
        // Calculate correct answers by subtracting missing correct answers
        correctAnswers = this.getAnswer().length() - missingCorrectAnswers;
        
        // The user gets one point for each correct answer they selected and loses one point for each incorrect answer
        int pointsEarned = correctAnswers - incorrectGivenAnswers - missingCorrectAnswers;
        
        // Make sure points can't be negative (minimum is 0 points)
        return Math.max(0, pointsEarned);
    }

    // Find how many correct answers the user missed (i.e., the correct choices they didn't select)
    private int findMissingCorrectAnswers(String givenAnswer) {
        String correctAnswer = this.getAnswer();
        // Use findMissingCharacters to see how many correct answers the user missed
        return findMissingCharacters(correctAnswer, givenAnswer);
    }

    // Find how many incorrect answers the user selected
    private int findIncorrectGivenAnswers(String givenAnswer) {
        String correctAnswer = this.getAnswer();
        // Use findMissingCharacters to see how many incorrect answers the user selected
        return findMissingCharacters(givenAnswer, correctAnswer);
    }

    // Helper method to calculate the number of characters that are in the baseString but not in toCheck
    private static int findMissingCharacters(String baseString, String toCheck) {
        int missingValues = 0;
        for (int i = 0; i < toCheck.length(); i++) {
            char characterToLocate = toCheck.charAt(i);
            if (baseString.indexOf(characterToLocate) == -1) { // not in baseString
                missingValues++;
            }
        }
        return missingValues;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Define the choices for the multiple-choice question
        String[] choices = {"instance variables", "git", "methods", "eclipse"};
        
        // Create an instance of SelectAllQuestion
        // The correct answer is "13" (choices 1 and 3 are correct), and the question is worth 1 point per choice
        Question selectAll = new SelectAllQuestion("Select all of the following that can be found within a class:", "13", choices);
        
        // Display the question and choices
        selectAll.displayPrompt();
        
        // Test checkAnswer method with various answers:
        System.out.println(selectAll.checkAnswer("hi"));   // no credit (should print 0)
        System.out.println(selectAll.checkAnswer("2"));    // 1 point (choice 2 is correct, but choice 1 and 3 are missing)
        System.out.println(selectAll.checkAnswer("13"));   // full credit (should print 2)
        System.out.println(selectAll.checkAnswer("31"));   // full credit (should print 2)
        System.out.println(selectAll.checkAnswer("1"));    // 3 points (should print 3 because choice 1 is correct, 2 and 3 are missing)
        System.out.println(selectAll.checkAnswer("3"));    // 3 points (should print 3 because choice 3 is correct, 1 and 2 are missing)
        System.out.println(selectAll.checkAnswer("23"));   // 2 points (should print 2 because choice 2 is correct, 1 and 3 are missing)
        System.out.println(selectAll.checkAnswer("34"));   // 2 points (should print 2 because choice 3 is correct, 1 and 2 are missing)
        System.out.println(selectAll.checkAnswer("4"));    // 1 point (should print 1 because choice 4 is incorrect, 1 and 3 are missing)
        System.out.println(selectAll.checkAnswer("124"));  // 1 point (should print 1 because only choice 1 is correct)
        System.out.println(selectAll.checkAnswer("24"));   // 0 points (should print 0 because choices 2 and 4 are incorrect)
    }
}
