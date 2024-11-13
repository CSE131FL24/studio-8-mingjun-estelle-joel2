package studio8;

public class Question {
    private String prompt;
    private String answer; 
    private int points;    

    public Question(String prompt, String answer, int points) {
        this.prompt = prompt;
        this.answer = answer;
        this.points = points;
    }

    
    public void displayPrompt() {
        System.out.println(this.prompt + " (" + this.points + " points)");
    }

    public int checkAnswer(String givenAnswer) {
        if (answer.equals(givenAnswer)) { 
            return this.points;  
        } else {
            return 0;  
        }
    }


    public int getPoints() {
        return this.points;
    }

    public String getAnswer() {
        return this.answer;
    }

    public static void main(String[] args) {
       
        Question q = new Question("What number studio is this?", "8", 2);
        
     
        q.displayPrompt();
        
     
        System.out.println(q.checkAnswer("hi"));  

        System.out.println(q.checkAnswer("8")); 
        
      
        Question q2 = new Question("What is the capital of France?", "Paris", 5);
        q2.displayPrompt();
        System.out.println(q2.checkAnswer("Paris")); 
        System.out.println(q2.checkAnswer("London")); 
    }
}
