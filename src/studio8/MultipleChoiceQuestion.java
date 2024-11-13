package studio8;

public class MultipleChoiceQuestion extends Question {
    private String[] choices;  
    
    
    public MultipleChoiceQuestion(String prompt, String answer, int points, String[] choices) {
        super(prompt, answer, points);  
        this.choices = choices; 
    }
    
    @Override
    public void displayPrompt() {
        super.displayPrompt();  
        
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);  
          
        }
    }
    
   
    public static void main(String[] args) {
        
        String[] choices = {"seven", "nine", "eight", "six"};
        
        
        Question multipleChoice = new MultipleChoiceQuestion("What studio is this?", "3", 1, choices);
        
       
        multipleChoice.displayPrompt();
        
  
        System.out.println(multipleChoice.checkAnswer("hi"));  
        System.out.println(multipleChoice.checkAnswer("1"));   
        System.out.println(multipleChoice.checkAnswer("3"));   
    }
}
