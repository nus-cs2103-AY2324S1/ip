package kiera.command;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;
import kiera.exception.KieraException;
import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;
import kiera.task.Todo;
import kiera.tasktype.TaskType;

/**
 * Command to add a task to the taskList and storage.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand.
     * @param t Type of task added.
     * @param desc Description of task added.
     */
    public AddCommand(TaskType t, String desc) {
        setDescription(desc);
        setTaskType(t);
    }

    /**
     * @inheritDoc
     *
     * @throws KieraException If there is an error with the task type or storage operation.
     */
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
        default:
            System.out.println("task type does not exist");
        }
        storage.save(tasks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
