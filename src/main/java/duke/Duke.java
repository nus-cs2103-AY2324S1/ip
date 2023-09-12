package duke;
import duke.command.Command;
import javafx.application.Platform;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UiManager uiManager;
    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Constructor of Duke class.
     *
     * @param filePath File path of the save file.
     */
    public Duke(String filePath) {
        uiManager = new UiManager();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }


    /**
     * Gets the response according to the input.
     *
     * @param input String input by user.
     * @return Output string according to the input string.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input.trim());
            return c.execute(tasks, uiManager, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
