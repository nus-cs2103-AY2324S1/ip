import commands.*;
import functions.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for running the CR7 task management application.
 */
public class CR7 {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Runs the CR7 application by displaying a welcome message, processing user commands,
     * and executing corresponding actions.
     */
    public void run() {
        ui.showWelcome();
    }

    /**
     * Constructs a CR7 object with the provided file path for data storage.
     *
     * @param filePath The file path for storing task data.
     */
    public CR7(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFiles());
        } catch (FileNotFoundException e) {
            ui.showErrorMsg("Error: Task data file not found.");
            tasks = new TaskList();
        }
    }

    public CR7() {
        ui = new Ui();
    }

    /**
     * The main method to start the CR7 application.
     *
     * @param args The command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        new CR7("src/main/data/CR7.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return "Cristiano Ronaldo will always be the GOAT. And here is why: \n" + response;
        } catch (IOException e) {
            return ui.showErrorMsg("An error occurred while processing the command.");
        }
    }
}
