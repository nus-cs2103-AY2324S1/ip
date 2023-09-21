package duke;

import java.time.format.DateTimeFormatter;

import command.Commands;
import dukeexceptions.DukeException;
import parser.Parser;
import storage.Storage;
import task.ListOfTask;
import ui.Ui;

/**
 * This is the file that outlines the logic in the chatbot.
 */
public class Duke {

    /**
     * This is the format for all date and time input.
     */
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final ListOfTask taskList = new ListOfTask();

    /**
     * This starts the Duke chatbot.
     *
     * @param args Does nothing
     */
    public static void main(String[] args) {
        greet();
    }

    /**
     * Greets the user and loads the duke from the save file.
     *
     * @return Returns the greeting as well as the list of errors while loading, if any.
     */
    public static String greet() {
        String str = Storage.load(taskList);
        return Ui.greet() + str;
    }

    /**
     * Takes the user input as the command and executes it.
     *
     * @param command The user input string.
     * @return The string show the completion of a command or an error to show the command has an error.
     */
    public static String nextCommand(String command) {
        try {
            Parser cmd = new Parser(command);
            Commands action = cmd.parse();
            String dukeResponse = action.execute(taskList);
            assert(dukeResponse != null);
            return dukeResponse;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
