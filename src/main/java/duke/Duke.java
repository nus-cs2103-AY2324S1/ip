package duke;

import java.io.IOException;

import command.Commandable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import dukeexception.CorruptedFileException;
import dukeexception.FailureInExecuteException;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;
/**
 * Duke represents a chatbot that parses user inputs and commands, stores tasks given to it in memory and on a file,
 * and provides a user interface for easier correspondence.
 */
public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final String LOGO = " _           _        \n"
            + "| |    _   _| | _____ \n"
            + "| |   | | | | |/ / _ \\\n"
            + "| |___| |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private TaskList list;
    private Storage storage;
    private Parser parser;
    private UserInterface ui;

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        storage = new Storage();
        list = new TaskList(storage);
        parser = new Parser();
        ui = new UserInterface();
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(ui.sceneMaker());
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println(loader.getResource(""));
    }

    /**
     * Initializes the storage, list and outputs a greeting.
     * @throws IOException when unable to read from disk.
     * @throws CorruptedFileException when unable to interpret file.
     */
    public void startDuke() throws IOException, CorruptedFileException {
        ui.output("Hi, I'm \n" + LOGO);
        storage.init(FILE_PATH);
        list.loadFromDisk();
    }
    public void closeDuke() {
        ui.output("Goodbye!");
    }

    /**
     * Handler is called when a corrupted file is detected, allowing user to decide how to proceed, such as
     * clearing the file or shutting down.
     * @return whether the handler decides to call for a shutdown of the Duke instance.
     */
    public boolean corruptedFileHandler() {
        ui.output("File not properly formatted;\n"
                + "Clear corrupted file Y/N?");
        while (true) {
            String input = ui.input();
            ui.output(input);
            if (input.equals("Y")) {
                try {
                    storage.clear();
                    ui.output("File cleared.");
                } catch (IOException e) {
                    ui.output("Error in clearing! Shutting down.");
                    return true;
                }
                return false;
            } else if (input.equals("N")) {
                ui.output("Understood. Shutting down.");
                return true;
            } else {
                ui.output("Bad input. Input Y/N");
            }
        }
    }

    public void run() {
        boolean isShuttingDown = false;
        try {
            startDuke();
        } catch (IOException e) {
            ui.output("Could not read from file");
            isShuttingDown = true;
        } catch (CorruptedFileException f) {
            isShuttingDown = corruptedFileHandler();
        }
        while (!isShuttingDown) {
            String input = ui.input();
            try {
                Commandable command = parser.parse(input);
                isShuttingDown = command.execute(list, ui);
            } catch (InvalidCommandException e) {
                ui.output("Unknown command given; " + e.getMessage());
            } catch (InvalidVarException e) {
                ui.output("Invalid input; " + e.getMessage());
            } catch (FailureInExecuteException e) {
                ui.output("Failure to execute command; " + e.getMessage());
            }
        }
        closeDuke();
    }

    public static void main(String[] args) {
        Duke luke = new Duke();
        luke.run();
    }
}
