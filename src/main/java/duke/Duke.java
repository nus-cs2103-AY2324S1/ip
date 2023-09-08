package duke;

import java.time.format.DateTimeFormatter;

import command.Commands;
import dukeExceptions.DukeException;
import parser.Parser;
import storage.Storage;
import task.ListOfTask;
import ui.Ui;

/**
 * This is the file that outlines the logic in the chat bot.
 */
public class Duke {

    /**
     * This is the format for all date and time input.
     */
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static ListOfTask taskList = new ListOfTask();
    private static Ui ui = new Ui();
    private static boolean isExit = false;

    /**
     * This starts the Duke chatbot.
     *
     * @param args Does nothing
     */
    public static void main(String[] args) {
        greet();
    }

    public static String greet() {
        String str = Storage.load(taskList, 1, null);
        return ui.greet() + ((str == null) ? "" : str);
    }

    public static String nextCommand(String command) {
        try {
            Parser cmd = new Parser(command);
            Commands action = cmd.parse();
            return action.execute(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
