package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String fullCommand) {
        super(fullCommand);
    }
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) {
        String[] words = this.fullCommand.split(" ", 2);
        if(words.length < 2) {
            throw new InvalidArgumentException("todo");
        } else {
            Todo t = new Todo(words[1]);
            tasks.addTask(t);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
