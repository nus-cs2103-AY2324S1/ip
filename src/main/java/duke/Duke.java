package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Main;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Duke {
    /**
     * The task list
     */
    private final TaskList taskList;
    /**
     * The ui object
     */
    private final Ui ui;
    /**
     * The storage object
     */
    private final Storage storage;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for Duke
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.taskList = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /**
     * Initialises the program
     *
     * @param args Input args.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException exc) {
            return exc.getMessage();
        }
    }

}
