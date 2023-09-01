package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class TodoCommand extends TaskCommand {

    public TodoCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void load(TaskList tasklist) {
        tasklist.add(description, isDone);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        String todoString = tasklist.add(description, isDone);
        ui.print(String.format("I've added this task:\n%s\nNow you have %d tasks in the list.", todoString,
                tasklist.getSize()));
    }
}
