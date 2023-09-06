package duke;

import duke.task.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



/**
 * Duke is a chatbot that helps the user to keep track of Tasks. Users
 * can add, delete, mark tasks as done.
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Duke Object. It will attempt to load saved data
     * and continue to read/write tasks to the saved data.
     * Ui, Storage and the TaskLists are initialized here.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.tasks = new TaskList(this.storage.readTasks());
        } catch (DukeException e) {
            this.ui.loadingErrorMessage();
            this.tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks);
    }


    /**
     * Parses the user's input and gives a response based on input.
     *
     * @param input to be taken in from user.
     * @return The response based on input parsed.
     */
    public String getResponse(String input) {
        String response = "Invalid";
        try {
            response = parser.parse(input);
        } catch (DukeException e) {
            response = e.toString();
        }
        return response;
    }

}
