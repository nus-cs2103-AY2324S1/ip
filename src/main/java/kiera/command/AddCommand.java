package kiera.command;

import kiera.*;
import kiera.exception.KieraException;
import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;
import kiera.task.Todo;
import kiera.tasktype.TaskType;

public class AddCommand extends Command {

    public AddCommand(TaskType t, String desc) {
        setDescription(desc);
        setTaskType(t);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KieraException {
       TaskType t = this.getTaskType();
       String desc = this.getDescription();
       Task task;
        switch (t) {
            case TODO:
                task = new Todo(desc);
                tasks.add(task);
                ui.showAddNotice(task, t, tasks.getSize());
                break;
            case DEADLINE:
                task = new Deadline(desc);
                tasks.add(task);
                ui.showAddNotice(task, t, tasks.getSize());
                break;
            case EVENT:
                task = new Event(desc);
                tasks.add(task);
                ui.showAddNotice(task, t, tasks.getSize());
                break;
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
