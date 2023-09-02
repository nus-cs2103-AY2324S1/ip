package jeeves.ui;

public class Ui {
    
    public Ui() {
        
    }
    
    public void printGreeting() {
        System.out.println("Greetings, Master. Jeeves at your service");
        System.out.println("How may I serve you today?\n");
    }
    
    public void printFarewell() {
        System.out.println("I bid you farewell, Master");
    }
    
    public void printInvalidCommand() {
        System.out.println("Apologies Master, I am unable to understand that command.\n"
                + "I will improve myself to better serve you in the future.\n");
    }
}
