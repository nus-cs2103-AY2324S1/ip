package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * A bot that allows creation, deletion of tasks.
 * It also can mark and unmarked tasks as completed.
 */
public class Duke {

    private UI ui = new UI();

    private Storage storage = new Storage("./data/Duke.txt");

    private CommandParser parser = new CommandParser();

    private TaskList taskList = new TaskList(storage.loadFile());

    public void greet(Pane pane, Image dukeImage) {
        ui.greet(pane, dukeImage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public void addResponse(String input, Pane pane, Image dukeImage) {
        try {
            Action action = parser.parseCommand(input, dukeImage);
            action.execute(taskList, storage, pane);
        } catch (DukeException e) {
            pane.getChildren().add(DialogBox.getDukeDialog(e.toString(), dukeImage));
        }
    }
}
