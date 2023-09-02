package arona.commands;

import arona.exception.IllegalArgumentAronaException;
import arona.parser.Parser;
import arona.storage.Storage;
import arona.task.DeadlineTask;
import arona.task.TaskList;
import arona.ui.Ui;

public class DeadlineCommand extends Command {
    private Storage storage;
    private DeadlineTask deadlineTask;

    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String[] descriptions) throws IllegalArgumentAronaException {
        super(taskList, ui);
        this.storage = storage;
        this.deadlineTask = new DeadlineTask(descriptions[0], Parser.parseDate(descriptions[1]));
    }
    @Override
    public void execute() {
        taskList.getTasks().add(deadlineTask);
        storage.saveTask(deadlineTask);
        ui.showTaskAdded(deadlineTask, taskList.getTasks().size());
    }
}
