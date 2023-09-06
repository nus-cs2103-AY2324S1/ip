package rayshawn.chatbot;

import javafx.application.Application;

/**
 * Entry point of the chatbot application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the program.
     *
     * @param args user keyed in commands
     */
    public void run(String[] args) {
        Application.launch(MainApp.class, args);
    }

}
