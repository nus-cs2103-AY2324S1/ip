package grumpygordon;

import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.exceptions.GrumpyGordonInitialisationException;
import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * GrumpyGordon Chatbot
 */
public class GrumpyGordon extends Application {
    /**
     * Storage for GrumpyGordon.
     */
    private Storage storage;

    /**
     * List of tasks for GrumpyGordon.
     */
    private TaskList tasks;

    /**
     * User interface for GrumpyGordon.
     */
    private Ui ui;

    /**
     * Constructor for GrumpyGordon.
     * @throws GrumpyGordonException If GrumpyGordon fails to initialise
     */
    public GrumpyGordon() throws GrumpyGordonException {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadTasks();
        } catch (GrumpyGordonInitialisationException e) {
            this.tasks = new TaskList();
        }
        this.ui = new Ui(tasks, storage);
    }

    /**
     * Runs GrumpyGordon.
     */
    public void run() {
        this.ui.run();
    }

    /**
     * Main loop for GrumpyGordon.
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        try {
            new GrumpyGordon().run();
        } catch (GrumpyGordonException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
