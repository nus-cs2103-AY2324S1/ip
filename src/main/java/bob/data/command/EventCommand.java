package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;

public class EventCommand extends Command {
    private String input;

    public EventCommand(String input) {
        super();
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) throws DukeException {
        return list.addTask(Parser.CommandType.EVENT, input);
    }
}
