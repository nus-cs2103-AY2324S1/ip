package Forgotten.GUI;

import Forgotten.Forgotten;

public class ForgottenGUI {

    Forgotten forgotten;

    public ForgottenGUI() {
        this.forgotten = new Forgotten("src/main/java/Database.txt");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userCommand) {
        return forgotten.generateResponse(userCommand);
    }

    /**
     * This method prints the greeting message.
     */
    public String printGreetMessage() {
        return " Hello! I'm Forgotten\nWhat can I do for you? ";
    }

    /**
     * This method prints the bye message.
     */
    public String printByeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

}