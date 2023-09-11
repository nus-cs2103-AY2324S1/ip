package qi.command;

import java.io.IOException;
import java.time.LocalDate;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.task.Deadline;
import qi.task.Event;
import qi.task.Task;
import qi.task.Todo;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Represents the execution of adding a task into the list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Receives information of a Todo.
     *
     * @param taskDescription String description of a Todo.
     */
    public AddCommand(String taskDescription) {
        super(false);
        this.task = new Todo(taskDescription);
    }

    /**
     * Receives information of a Deadline.
     *
     * @param taskDescription String description of a Deadline.
     * @param deadline LocalDate representing the deadline of the task.
     */
    public AddCommand(String taskDescription, LocalDate deadline) {
        super(false);
        this.task = new Deadline(taskDescription, deadline);
    }

    /**
     * Receives information of an Event.
     *
     * @param taskDescription String description of an Event.
     * @param startTime String representation of the event start time.
     * @param endTime String representation of the event end time.
     */
    public AddCommand(String taskDescription, String startTime, String endTime) {
        super(false);
        this.task = new Event(taskDescription, startTime, endTime);
    }


    /**
     * Adds the task into the list and then updates the data on hard disk.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @throws QiException If the file storing date cannot be written.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        tasks.addTask(this.task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            throw new QiException("Cannot write to file!");
        }
        return ui.showTaskAdded(this.task, tasks);
    }
}
