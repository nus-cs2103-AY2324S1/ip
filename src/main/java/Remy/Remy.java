package remy;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import remy.command.Command;
import remy.task.TaskList;

/**
 * A Chatbot named Remy that functions as a task manager.
 * It accepts input from users via CLI commands.
 */
// CS2103T Website Increment description-reused
// Reused the example code from the website.
public class Remy extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Remy() {

    }


    /**
     * Sets up a Remy chatbot for use.
     * @param filePath location to store tasklist after ending each session.
     */
    public Remy(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatbotException e) {
            Ui.printLongSandwich(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Parses user input and executes tasks based on input.
     * Will end session if user types "exit".
     */
    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatbotException e) {
                Ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Starts the Remy Chatbot and provides it with the location to store data.
     * @param args
     */
    public static void main(String[] args) {
        Path filePath = Paths.get(".", "data", "remy.ser");
        new Remy(filePath).run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
