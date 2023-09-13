package duke;

import java.util.Scanner;

import duke.DukeException.DukeException;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;
/**
 * Chatbot that can list tasks.
 */
public class Duke {
    /**
     * Variable to store the list of tasks.
     */
    private TaskList tasks;
    /**
     * Variable to get the reply.
     */
    private Ui ui;
    /**
     * Variable to get the input from the user.
     */
    private Scanner sc;
    /**
     * Variable to access the file where alll of the task is stored.
     */
    private Storage storage;

    /**
     * Constructs the instance of the duke.Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.sc = new Scanner(System.in);
        try {
            this.storage.createStorage();
            this.tasks = this.storage.getStorage();
        } catch (DukeException e) {
            this.ui.error(e);
        }
    }

    /**
     * Get the response for the chatbot.
     * @param input Command input by the user.
     * @return the response
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, ui, storage, tasks);
        } catch (DukeException e) {
            this.ui.error(e);
        }
        return "Cannot reach here";
    }

    public String intro() {
        return this.ui.hello();
    }
}