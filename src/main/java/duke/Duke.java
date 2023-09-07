package duke; // Use lowercase for package names

import duke.exceptions.DukeException;
import duke.filehandler.Storage;
import duke.parsers.InputParser;
import duke.tasks.Task;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;




public class Duke extends Application{

    private static ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        //this(DEFAULT_FILE_PATH);
    }

    public Duke(String filePath) {
        ui = new Ui();
        ui.printGreeting();
        storage = new Storage(filePath);
        try {
            tasks = storage.readTasks();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new ArrayList<>();
        }
    }

    public void run() {
        InputParser.getUserInputs(tasks);
        ui.printEnding();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
