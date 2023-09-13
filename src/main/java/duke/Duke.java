package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * Personal assistant chatbot that can help you manage a task list.
 * This is the main class of the program.
 *
 * @author Wu Jingya
 */
public class Duke extends Application {
    public static Duke duke;
    private static String filePath = "./data/duke.txt";

    private String name = "Moira";
    /** Whether the chatbot is currently accepting user input */
    private boolean isReceivingInput = false;
    private TaskList tasks;
    private Scanner scanner;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object with no parameters.
     */
    public Duke() {
        scanner = new Scanner(System.in);
        tasks = new TaskList();
        storage = new Storage(tasks, filePath);
        ui = new Ui(name);
        parser = new Parser(ui);
        Duke.duke = this;
    }

    private void exit() {
        isReceivingInput = false;
        storage.saveData();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Stops the chatbot from receiving user input.
     */
    public void stopReceivingInput() {
        isReceivingInput = false;
    }

    /**
     * Returns the TaskList stored by the chatbot.
     *
     * @return The TaskList stored by the chatbot.
     * @see TaskList
     */
    public TaskList getTaskList() {
        return tasks;
    }

    //For testing purposes
    /**
     * Changes the path of the file storing TaskList data to the specified path.
     * This method should only be used for testing purposes.
     *
     * @param path The intended path of the data file as a string.
     */
    public static void changeFilePath(String path) {
        filePath = path;
    }

    //For testing purposes
    public static boolean getIsReceivingInput() {
        return duke.isReceivingInput;
    }

    //For testing purposes
    public static void setIsReceivingInput(boolean isReceivingInput) {
        Duke.duke.isReceivingInput = isReceivingInput;
    }

    public void exitApplication() {
        exit();
        Launcher.exit();
    }
}
