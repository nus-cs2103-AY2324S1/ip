package duke;

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


/**
 * A class that represents a ChatBot.
 * Interacts with the user based on the input received.
 */
public class Duke {
    private TaskList taskList;

    /**
     * Public Constructor for Duke.
     */
    public Duke() {
        this.taskList = Storage.load("data/duke.txt");
    }

    /**
     * Runs the program.
     * Reads the user inputs.
     */
    public String getResponse(String input) {
        return Parser.handleInput(input, taskList, false);
    }
}
