package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;
import miles.task.Deadline;

public class AddDeadlineCommand extends Command {
    private String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(input);
        storage.saveWhenAddTask(newDeadline, taskList);
        int n = taskList.getSize();
        ui.printAddedTask(newDeadline, n);
    }
}
