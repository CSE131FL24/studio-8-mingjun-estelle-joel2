package studio8;

import java.util.Scanner;

public class Quiz {

    private Question[] questions;

    // Constructor to initialize the quiz with an array of questions
    public Quiz(Question[] questions) {
        this.questions = questions;
    }

    // Method to get the user's answer
    private String getUserAnswer() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your answer: ");
        String out = in.next();
        return out;
    }

    // Method to calculate the total points for the quiz (sum of all question points)
    public int getTotalPoints() {
        int sum = 0;
        for (Question q : questions) {
            sum += q.getPoints();
        }
        return sum;
    }

    // Method to handle taking the quiz
    public void takeQuiz() {
        int totalEarnedPoints = 0;

        // Loop through all questions
        for (Question question : questions) {
            // Display the prompt for the question
            question.displayPrompt();

            // Get the user's answer for the question
            String userAnswer = getUserAnswer();

            // Check the user's answer and get the points earned for that question
            int pointsEarned = question.checkAnswer(userAnswer);

            // Display the points earned for this question
            System.out.println("Points earned for this question: " + pointsEarned);

            // Add the points earned to the total
            totalEarnedPoints += pointsEarned;
        }

        // Display the total points earned and the total points available
        System.out.println("Total points earned: " + totalEarnedPoints);
        System.out.println("Total points available: " + getTotalPoints());
    }

    public static void main(String[] args) {
        // Define some sample questions
        Question q = new Question("What number studio is this?", "8", 2);

        String[] choices = {"seven", "nine", "eight", "six"};
        Question multipleChoice = new MultipleChoiceQuestion("What studio is this?", "3", 1, choices);

        choices = new String[] {"instance variables", "git", "methods", "eclipse"};
        Question selectAll = new SelectAllQuestion("Select all of the following that can be found within a class:", "13", choices);

        // Add the questions to an array
        Question[] questions = {q, multipleChoice, selectAll}; 

        // Create the quiz
        Quiz studio8quiz = new Quiz(questions);
        
        // Take the quiz
        studio8quiz.takeQuiz();
    }
}
