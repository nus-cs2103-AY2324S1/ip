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
 * A chatbot named Bert that interacts with the user and keeps track of a task list.
 */
public class Bert {
    private static final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an instance of the chatbot and loads the file at the specified filePath
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

    public CommandResult handleInput(String fullCommand) {
        assert fullCommand != null : "Input passed into Bert should not be null";
        try {
            Command c = new Parser().parse(fullCommand);
            return c.execute(tasks, storage);
        } catch (BertException e) {
            return new CommandResult(String.format(Messages.MESSAGE_ERROR, e.getMessage()));
        }
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
