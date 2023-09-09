package duke;

import duke.command.Command;
import duke.exception.DukeDatabaseException;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.task.TaskList;

/**
 * Represents the chatbot.
 */
public class Duke {
    private static final String DATA_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The file path for storing task data.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.loadData());
        } catch (DukeDatabaseException e) {
            this.ui.showDukeException(e);
        }
    }

    public Duke() {
        this(DATA_PATH);
    }

    /**
     * Processes user input and returns a response string.
     *
     * @param userInput The user's input.
     * @return The response to the user as a string.
     */
    public String getResponse(String userInput) {
        try {
            if (userInput.trim().isEmpty()) {
                throw new EmptyCommandException();
            }

            Command command = Parser.parseUserInput(userInput);
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return ui.showArrayIndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_PATH);
    }

}
