package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;

public class TodoCommand extends Command {
    private String input;
    public TodoCommand(String input) {
        super();
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) throws DukeException {
        return list.addTask(Parser.CommandType.TODO, this.input);
    }
}
