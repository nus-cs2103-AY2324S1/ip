package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Add a TodoTask into the list.
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * Creates a TodoCommand that creates an TodoTask given the specified input.
     * @param input The user input with the details of the TodoTask.
     */
    public TodoCommand(String input) {
        super();
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) throws DukeException {
        return list.addTask(Parser.CommandType.TODO, this.input);
    }
}
