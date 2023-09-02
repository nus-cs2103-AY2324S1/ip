package arona.commands;

import arona.storage.Storage;
import arona.task.EventTask;
import arona.task.TaskList;
import arona.ui.Ui;

public class EventCommand extends Command {
    private Storage storage;
    private EventTask eventTask;

    public EventCommand(TaskList taskList, Ui ui, Storage storage, String[] descriptions) {
        super(taskList, ui);
        this.storage = storage;
        this.eventTask = new EventTask(descriptions[0], descriptions[1], descriptions[2]);
    }

    @Override
    public void execute() {
        taskList.getTasks().add(eventTask);
        storage.saveTask(eventTask);
        ui.showTaskAdded(eventTask, taskList.getTasks().size());
    }
}
