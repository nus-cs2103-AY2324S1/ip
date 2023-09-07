package duke;

import java.io.*;

import java.util.ArrayList;

import static duke.Parser.EXIT_PHRASE;
import static duke.Storage.loadTasksFromFile;

import duke.gui.DialogBox;
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
 * The main class for the Duke application.
 */
public class Duke {
    static Storage storage = new Storage();
    static Ui ui = new Ui();
    static ArrayList<Task> taskList = new TaskList();
    static int index = 0;

    public String getResponse(String input) throws DukeException {
        index = taskList.size();
        return Parser.parse(taskList, index, ui, storage, input);
    }
}
