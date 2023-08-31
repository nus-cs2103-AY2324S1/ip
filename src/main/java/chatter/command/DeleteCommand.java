package chatter.command;

import chatter.Storage;
import chatter.TaskList;
import chatter.Ui;

/**
 * Represents a Command class that is responsible for deleting a Task object from TaskList.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class DeleteCommand extends Command {
    /** Index of chatter.task.Task to be deleted from the list of Tasks. */
    private int taskNumber;

    /**
     * Constructor for the chatter.command.DeleteCommand class to delete a chatter.task.Task from the list of Tasks.
     *
     * @param taskNumber Index of chatter.task.Task object to be deleted from the list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the list and prints deleted task message for user.
     *
     * @param tasks chatter.TaskList class storing an ArrayList of chatter.task.Task objects.
     * @param ui chatter.Ui class to handle user interactions.
     * @param storage chatter.Storage class to read and store tasks by the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showDeletedTask(tasks.getTask(taskNumber - 1), tasks.getNumOfTasks() - 1);
        tasks.delete(taskNumber);
        storage.saveFile(tasks.toStorageString());
    }
}
