package duke;

import duke.exceptions.DukeException;
import duke.gui.Main;
import duke.logic.Executor;
import duke.logic.Storage;
import duke.logic.Ui;
import duke.tasks.TaskList;
import javafx.application.Application;

/**
 * Class for the ChatBot
 */
public class Duke {
    public static final String TEXTFILE = "duke.txt";
    public static final String DATAFILE = "data.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Executor executor;

    /**
     * Constructor for the Duke class.
     * @param filePath Specifies the name of the text file
     */
    public Duke(String filePath) {
        this.storage = new Storage(TEXTFILE, DATAFILE);
        this.ui = new Ui();
        this.executor = new Executor();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Echos the user's input.
     * @param input String that user inpt into the chat bot
     * @return An echo of what the user said
     */
    public String getResponse(String input) {
        return executor.execute(input, tasks, storage, ui);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
