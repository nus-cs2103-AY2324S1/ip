package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

import command.Command;
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

/**
 * A chatbot. Able to read user input and perform a series of tasks.
 */
public class Duke {
    private static final String NAME = "DEREK";
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Constructor for Duke class.
     *
     * @param filePath Relative path of file where data is to be stored.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI(NAME);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.printLoadingErrorMessage();
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.storage = new Storage("./data/state.txt");
        this.ui = new UI(NAME);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.printLoadingErrorMessage();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.printWelcomeMessage();
        String input;

        while (true) {
            input = ui.readCommand();
            try {
                Command command = Parser.parseUserInput(input);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/state.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
