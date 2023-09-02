package duke;

import java.util.ArrayList;

import javafx.scene.control.TextArea;


//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
public class Ui {
    // Assuming you have a TextArea in your JavaFX application layout
    private TextArea messageTextArea;


    /**
     * Print the message when the chatbot first starts
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Muddy\n" + "What can I do for you?");
    }


    /**
     * Print the message when the chatbot exits
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    /**
     * Print the error message
     * @param errorMessage error message to be printer
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Print the given message
     *
     * @param message message to be printed
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints the list of messages
     * @param message the ArrayList containing the message to be printed
     */
    public void print(ArrayList<String> message) {
        for (String s : message) {
            System.out.println(s);
        }
    }
}

