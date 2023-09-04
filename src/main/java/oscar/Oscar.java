package oscar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Scanner;

import oscar.command.Command;
import oscar.essential.Parser;
import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.ui.Ui;
import oscar.exception.OscarException;

/**
 * Chatbot named Oscar that can respond to user input.
 */
public class Oscar extends Application {
    static final String FILE_PATH = "./data/tasklist";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Instantiates Oscar with saved data.
     *
     * @param filePath Location of saved task list.
     */
    public Oscar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (OscarException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs Oscar and allows for user input.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        ui.greet();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (OscarException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        scanner.close();
    }

    /**
     * Programme flow to run Oscar.
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        new Oscar(FILE_PATH).run();
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
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
