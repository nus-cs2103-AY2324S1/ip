package bert;

import bert.commands.Command;
import bert.commands.CommandResult;
import bert.common.Messages;
import bert.exceptions.BertException;
import bert.parser.Parser;
import bert.storage.Storage;
import bert.tasks.TaskList;
import bert.ui.Launcher;

import java.io.FileNotFoundException;

/**
 * A chatbot named Bert that takes in user input and keeps track of a task list and storage.
 */
public class Bert {
    private static final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an instance of the chatbot and loads the file at the specified file path
     * into the chatbot's task list.
     */
    public Bert() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Takes in an input, parses it into a command, then executes the command
     * on the task list and storage, and returns a CommandResult instance which
     * contains a message to the user about the results of the command.
     *
     * @param fullCommand An input in the form of a command word and its arguments.
     * @return A CommandResult instance containing the message about the results of the command.
     */
    public CommandResult handleInput(String fullCommand) {
        assert fullCommand != null : "Input passed into Bert should not be null";
        try {
            Command c = new Parser().parse(fullCommand);
            return c.execute(tasks, storage);
        } catch (BertException e) {
            return new CommandResult(String.format(Messages.MESSAGE_ERROR, e.getMessage()));
        }
    }

    /**
     * Launches the GUI. Upon launching, the GUI then starts the Bert chatbot.
     *
     * @param args Command-line arguments which are not used.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
