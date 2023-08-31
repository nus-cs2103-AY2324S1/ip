package main;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * The default constructor for the Ui class
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Updates the user input (read the next user input)
     */
    public void update() {
        String input = this.scanner.nextLine();
        Main.getInstance().getParser().executeCommand(input);
    }

    /**
     * Outputs a message to the user
     *
     * @param content The message content
     */
    public void say(String content) {
        this.say(content, true,true);
    }

    /**
     * Outputs a message to the user
     *
     * @param content The message content
     * @param outputUpperLine Set to true to output a split line above the message
     * @param outputLowerLine Set to true to output a split line under the message
     */
    public void say(String content, boolean outputUpperLine, boolean outputLowerLine) {
        if(outputUpperLine){
            System.out.println("    ____________________________________________________________");
        }
        System.out.println("    " + content);
        if(outputLowerLine){
            System.out.println("    ____________________________________________________________");
        }

    }

    /**
     * Call this method when the Ui instance is no longer being used.
     */
    public void dispose(){
        this.scanner.close();
    }
}
