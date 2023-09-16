package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Add an EventTask into the list.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Creates an EventCommand that creates an EventTask given the specified input.
     * @param input The user input with the details of the EventTask.
     */
    public EventCommand(String input) {
        super();
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) throws DukeException {
        return list.addTask(Parser.CommandType.EVENT, input);
    }
}
