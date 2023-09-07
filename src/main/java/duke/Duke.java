package duke;

import java.io.IOException;

import commands.Command;
import data.TaskList;
import data.exception.DukeException;
import parser.Parser;
import storage.Storage;
import ui.UiCli;
import ui.UiMessage;

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
     * Handles displaying messages and errors on command line.
     */
    private UiCli uiCli;

    /**
     * Constructor method of the Duke chatbot. Initializes its main components:
     * {@link TaskList}, {@link Storage}, {@link Parser} and {@link UiCli}.
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
        this.uiCli = new UiCli();
    }

    public Duke() {
        this.storage = new Storage(
            "./save/data.txt",
            "./save"
        );
        this.tasks = new TaskList(this.storage.load());
        this.parser = new Parser();
        this.uiCli = new UiCli();
    }

    /**
     * Starts the Duke chatbot and the main event loop which will await
     * for user commands.
     * 
     * @throws IOException Thrown when there's an issue with
     *                     reading user input.
     */
    public void run() throws IOException {
        uiCli.displayIntro();

        // Begin chatbot's main event loop
        String input = "";
        boolean isExit = false;
        while (!isExit) {
            uiCli.displayInputStart();
            input = uiCli.readInput();
            try {
                Command c = parser.parse(input);
                UiMessage result = c.execute(tasks, storage, uiCli);
                uiCli.displayMsg(result.getRawStringArr());
                isExit = c.isExit();
            } catch (DukeException e) {
                uiCli.displayError(e.toString());
            }
        }
    }

    public String startIntroduction() {
        return "Hi. I'm Bryan\n"
                + "What can I do for you?";
    }

    public String getResponse(String input) throws DukeException {
        Command c = parser.parse(input);
        UiMessage result = c.execute(tasks, storage, uiCli);
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        Duke chatbot = new Duke(
            "./save/data.txt",
            "./save"
        );
        chatbot.run();
    }
}
