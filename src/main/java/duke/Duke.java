package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * A chatbot that helps a person to keep track of a list of tasks.
 *
 * @author Qin Yan Er
 */
public class Duke {

    private static final String FILE_PATH = "./data/main.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Creates a new duke.Duke instance.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println("Loading Error");
            tasks = new TaskList();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            if (input.isEmpty()) {
                throw new DukeException("OOPS!!! The description cannot be empty.");
            }
            return Parser.parse(input, tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }



}
