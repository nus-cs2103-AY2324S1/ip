package commands;

import exception.IllegalArgumentDukeException;
import parser.Parser;
import storage.Storage;
import task.DeadlineTask;
import task.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    private Storage storage;
    private DeadlineTask deadlineTask;

    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String[] descriptions) throws IllegalArgumentDukeException {
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
