package commands;
import components.DukeException;
import components.Parser;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class ToDoCommand extends Command {
    private String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        list.addTask(Parser.createToDoTask(command), storage);
    }
}
