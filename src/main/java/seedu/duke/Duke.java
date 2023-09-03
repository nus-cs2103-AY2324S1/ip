package seedu.duke;

import seedu.duke.exception.InvalidCommandException;
import seedu.duke.util.TaskList;
import seedu.duke.util.Ui;
import seedu.duke.util.Parser;
import seedu.duke.util.Storage;

import java.io.IOException;

/**
 * The Duke class represents the TaskMate bot that allows users to
 * manage tasks by adding, deleting, marking and un-marking tasks.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    public static boolean isDone = false;

    /**
     * Constructs a new instance of the task bot with the specified file path.
     *
     * @param filePath The file path that stores the task data in text format.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            parser = new Parser();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Runs the task bot, displaying a welcome message and processing user input.
     */
    public void run() {
        ui.showWelcome();
        while (!isDone) {
            parser.parseUserInput(ui.getUserInput(), taskList, ui, storage);
        }
    }

    /**
     * The main entry point for the task bot.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}