package duke;

import java.io.IOException;
import commands.Command;
import data.TaskList;
import data.exception.DukeException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

/**
 * The Duke class. Entry point for the Duke chatbot to start.
 */
public class Duke {
    /**
     * Handles all tasks created by the user.
     */
    private TaskList tasks;

    /**
     * Loads task from a persistent file and 
     * updates it when new tasks are created.
     */
    private Storage storage;

    /**
     * Parses the user command.
     */
    private Parser parser;

    /**
     * Handles displaying messages and errors.
     */
    private Ui ui;

    /**
     * Constructor method of the Duke chatbot. Initializes its main components:
     * {@link TaskList}, {@link Storage}, {@link Parser} and {@link Ui}.
     * Additionally, it loads the tasks from a file stored on disk if the user 
     * has used the chatbot previously.
     * 
     * @param filePath The path of the file storing the tasks from previous sessions.
     * @param fileDir  The directory that contains the file. Used by {@link Storage}
     *                 to create an empty folder when none is detected.
     */
    public Duke(String filePath, String fileDir) {
        this.storage = new Storage(filePath, fileDir);
        this.tasks = new TaskList(this.storage.load());
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * Starts the Duke chatbot and the main event loop which will await
     * for user commands.
     * 
     * @throws IOException
     */
    public void start() throws IOException {
        ui.displayIntro();

        // Begin chatbot's main event loop
        String input = "";
        boolean isExit = false;
        while (!isExit) {
            ui.displayInputStart();
            input = ui.readInput();
            try {
                Command c = parser.parse(input);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayError(e.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Duke chatbot = new Duke(
            "./save/data.txt", 
            "./save"
        );
        chatbot.start();
    }
}
