package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;




/**
 * The main class for the Ding Chatbot application.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new instance of the duke.Duke class.
     * Initializes the storage and task list.
     */
    public Duke() {
        storage = new Storage("./data", "./data/ding.txt");
        tasks = storage.getTasks();
    }

    /**
     * Gets a response from the chatbot for the given user input.
     *
     * @param userInput The user's input.
     * @return The chatbot's response.
     */
    public String getResponse(String userInput) {
        String output;
        Command command = Parser.getCommand(userInput, storage, tasks, ui);
        output = command.execute(storage, tasks, ui);
        return output;
    }
}
