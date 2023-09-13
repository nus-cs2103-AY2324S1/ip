package duke;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

/**
 * The Duke class represents the TaskMate bot that allows users to
 * manage tasks by adding, deleting, marking and un-marking tasks.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class Duke {
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;
    private static Ui ui;
    public static boolean isDone = false;

    /**
     * Constructs a new instance of the task bot with the specified file path.
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        ui = new Ui();
        try {
            taskList = new TaskList(storage.load());
            parser = new Parser(taskList, ui, storage);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public Ui getUi() {
        return ui;
    }

    public static String getResponse(String userInput) {
        return parser.parseUserInput(userInput);
    }

    /**
     * The main entry point for the task bot.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        new Duke();
        while (!isDone) {
            System.out.println(getResponse(ui.getUserInput()));
        }
    }
}