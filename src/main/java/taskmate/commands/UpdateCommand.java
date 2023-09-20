package taskmate.commands;

import java.util.HashMap;

import taskmate.exceptions.InvalidByException;
import taskmate.exceptions.InvalidDeadlineUpdateException;
import taskmate.exceptions.InvalidEventUpdateException;
import taskmate.exceptions.InvalidFromException;
import taskmate.exceptions.InvalidToException;
import taskmate.exceptions.InvalidTodoUpdateException;
import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Task;

/**
 * The UpdateCommand class is a child class of the Command class. It represents the "update `int` `tag` `new_value`"
 * command which updates task attributes in the task list.
 */
public class UpdateCommand extends Command {

    private final HashMap<String, String> changes;
    private final int updateIndex;

    /**
     * Constructs a UpdateCommand object that allows the developer to update the attributes of an existing task
     * @param updateIndex a one-indexed int that represents the task to be updated
     * @param changes a Hashmap that represents the changes to be made to the task
     */
    public UpdateCommand(int updateIndex, HashMap<String, String> changes) {
        this.updateIndex = updateIndex;
        this.changes = changes;
    }

    /**
     * Executes the `update` command by updating the task attributes defined by the user
     * @param tasks   TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui      Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToUpdate = tasks.getTask(this.updateIndex);
            HashMap<String, String> successfulChanges = tasks.updateTask(taskToUpdate, changes);

            // print message after updating
            int zerothUpdateIndex = updateIndex + 1; // convert from one-index to zero-index
            ui.printSuccessfulUpdateResponse(zerothUpdateIndex, successfulChanges);

        } catch (Exception e) {
            handleException(e, ui);
        }
    }

    private void handleException(Exception e, Ui ui) {
        if (e instanceof TaskNotFoundException) {
            ui.printTaskNotFoundExceptionResponse();
        } else if (e instanceof InvalidTodoUpdateException) {
            ui.printInvalidTodoUpdateException();
        } else if (e instanceof InvalidDeadlineUpdateException) {
            ui.printInvalidDeadlineUpdateException();
        } else if (e instanceof InvalidEventUpdateException) {
            ui.printInvalidEventUpdateException();
        } else if (e instanceof InvalidToException) {
            ui.printInvalidToExceptionResponse();
        } else if (e instanceof InvalidByException) {
            ui.printInvalidByExceptionResponse();
        } else if (e instanceof InvalidFromException) {
            ui.printInvalidFromExceptionResponse();
        }
    }
}
