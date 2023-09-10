import commands.Command;
import commands.ExitCommand;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The Main class for the Duke application.
 * This class initialises a new Duke instance that handles the
 * control and flow of the application.
 */
public class Duke {

    public static final String VERSION = "NotCrazy ─ a CS2103T iP ─ Week 4 Update";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/joemama.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/kanye.jpeg"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs a new Duke object, along with a Storage object and an Ui object.
     */
    public Duke() {

        storage = new Storage();
        this.tasks = new TaskList(storage.load());

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c;
        Parser parser = new Parser();
        c = parser.parse(input);

        String response = c.execute(tasks);
        storage.save(tasks.getTaskList());
        return response;
    }
}



