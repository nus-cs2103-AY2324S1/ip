package chatter.command;

import chatter.Storage;
import chatter.TaskList;
import chatter.Ui;

/**
 * Represents a chatter.command.Command class that is responsible for marking / unmarking a task as done.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class MarkCommand extends Command {
    /** chatter.task.Task number to be marked as done. */
    private int taskNumber;
    /** Boolean containing whether user wants to mark or unmark a task as done. */
    private boolean isDone;


    /**
     * Constructor for the chatter.command.MarkCommand class for users to mark a task as done.
     */
    public MarkCommand(boolean isDone, int taskNumber) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    /**
     * Marks the specified task as done.
     *
     * @param tasks chatter.TaskList class storing an ArrayList of chatter.task.Task objects.
     * @param ui chatter.Ui class to handle user interactions.
     * @param storage chatter.Storage class to read and store tasks by the user.
     * @return Message letting the user know that the task has been marked.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (isDone) {
            tasks.markTaskAsDone(taskNumber);
            storage.saveFile(tasks.toStorageString());
            return ui.showMarkedTask(tasks.getTask(taskNumber - 1));
        } else {
            tasks.markTaskAsNotDone(taskNumber);
            storage.saveFile(tasks.toStorageString());
            return ui.showUnmarkedTask(tasks.getTask(taskNumber - 1));
        }
    }
}
