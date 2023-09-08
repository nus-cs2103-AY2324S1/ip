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


    public Duke(String saveLocation) {
        ui = new Ui();
        storage = new Storage(saveLocation, ui);
        try {
            tasks = new TaskList(storage.loadSavedTasks(), ui);
            parser = new Parser(ui, tasks);
        } catch (DukeNoExistingTasksException e) {
            tasks = new TaskList(ui);
            parser = new Parser(ui, tasks);
        } catch (DukeLoadTasksException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList(ui);
            parser = new Parser(ui, tasks);
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
//        ui.showWelcome();
//        String cmd = ui.readCmd();
//        while (parser.parseCommand(cmd)) {
//            cmd = ui.readCmd();
//            storage.saveTasks(tasks);
//        }
//        ui.closeScanner();
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
        String response = parser.parseCommand(input);
        return response;
    }
}
