package duke;

import exception.DukeException;

/**
 * Main class to run Duke chatbot.
 *
 * @author syamfarh
 */
public class Duke {

    /** name of ChatBot */
    private final String name = "Ken";

    /** store user Input in task.Task array */
    private TaskList tasks;

    /** Ui class that display out to user based on userInput */
    private Ui ui;

    /** storage class that handle fetch/saving task list from file */
    private Storage storage;

    /**
     * Initialize Duke and fetch task list from duke.txt file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Process the input from user and return the response result.
     *
     * @param input User input type String.
     * @return output String type result that is written on the UI.
     */
    public String getResponse(String input) {
        try {
            String result = Parser.replyUser(input, tasks, this.ui);
            storage.saveTasksToFile(tasks.getTasks());
            return result;
        } catch (DukeException e) {
            return ui.showErrorMsg(e);
        }
    }

}
