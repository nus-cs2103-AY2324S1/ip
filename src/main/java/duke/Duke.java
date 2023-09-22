package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private Parser parser;


    /**
     * Constructs a new Duke instance with the specified save location.
     *
     * @param saveLocation The file path where Duke will save its task data.
     * @throws DukeNoExistingTasksException If there are no existing tasks in the save file.
     * @throws DukeLoadTasksException      If an error occurs while loading tasks from the save file.
     */
    public Duke(String saveLocation) {
        ui = new Ui();
        storage = new Storage(saveLocation);
        try {
            tasks = new TaskList(storage.loadSavedTasks());
            parser = new Parser(ui, tasks, storage);
        } catch (DukeNoExistingTasksException e) {
            tasks = new TaskList();
            parser = new Parser(ui, tasks, storage);
        } catch (DukeLoadTasksException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
            parser = new Parser(ui, tasks, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt");
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        primaryStage.setScene(scene); // Setting the stage to show our screen
        primaryStage.show(); // Render the stage.
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return parser.parseCommand(input);
    }
}
