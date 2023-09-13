package duke;

import duke.gui.MainWindow;
import duke.task.TaskList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents an intelligent chat robot that helps a person to keep track of various things with encouraging quotes.
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Stage stage;
    public String SAVING_ERROR_MSG = "⚠ Oops! Something wrong when closing:(";
    public String BYE_MSG = "Bye!\n\"Beware the barrenness of a busy life.\"";

    /**
     * Initializes the chat robot. Establishes task list and parser.
     */
    public Duke(Stage stage) {
        this.stage = stage;
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    public String getResponse(String input) {
        try {
            String output = parser.parse(input);
            if (!parser.isRunning()) {
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return SAVING_ERROR_MSG;
                }
                return BYE_MSG;
            }
            return output;
        } catch (DukeException e) {
            return handleException(e);
        }
    }

    public void close(){
        this.stage.close();
    }

    private String handleException(DukeException e) {
        String message = e.getMessage();
        String warning;
        switch (message) {
        case "todo error":
            warning = "⚠ Oops! Need description for the todo:(";
            break;
        case "deadline error":
            warning = "⚠ Oops! Need description and formatted by date for the deadline:(";
            break;
        case "event error":
            warning = "⚠ Oops! Need description, from and to date for the event:(";
            break;
        case "task not found":
            warning = "⚠ Oops! Cannot find task:(";
            break;
        case "undefined":
            warning = "⚠ Sorry! I am not able to understand you. Try another language:D";
            break;
        default:
            warning = "⚠ Oops! Something went wrong:(";
            break;
        }
        return warning;
    }

}

