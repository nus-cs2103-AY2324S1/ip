package sisyphus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import sisyphus.parser.Parser;
import sisyphus.storage.Storage;
import sisyphus.task.TaskList;
import sisyphus.ui.Ui;

/**
 * Class for Sisyphus chatbot.
 */
public class Sisyphus extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for the Sisyphus chatBot.
     */
    public Sisyphus() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.loadData();
        parser = new Parser();
    }

    /**
     * Driver function to run all components.
     */
    public void run() {
        ui.greet();
        boolean isChatting = true;
        while (isChatting) {
            try {
                String fullCommand = ui.readLine();
                parser.runCommand(fullCommand, tasks, storage, ui);
                isChatting = parser.getActiveStatus();
            } catch (SisyphusException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.exit();
    }
    public static void main(String[] args) {
        Sisyphus sisyphus = new Sisyphus();
        sisyphus.run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
