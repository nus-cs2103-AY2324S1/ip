package duke.ui;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Collection;


/**
 * The main class that manages the Duke application.
 * This class handles the initialization, execution, and termination of the application.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    public Duke() throws DukeException{
        ui = new UI();
        storage = new Storage("data/duke.txt");
        try {
            Collection<Task> stoTasks = storage.load();
            tasks = new TaskList(stoTasks);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try{
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
