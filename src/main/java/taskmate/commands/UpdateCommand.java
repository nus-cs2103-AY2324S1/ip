package taskmate.commands;

import java.util.HashMap;

import taskmate.exceptions.*;
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
     * UpdateCommand constructor that allows the developer to update the attributes of an existing task
     * @param updateIndex a one-indexed int that represents the task to be updated
     * @param changes a Hashmap that represents the changes to be made to the task
     */
    public UpdateCommand(int updateIndex, HashMap<String, String> changes) {
        this.updateIndex = updateIndex;
        this.changes = changes;
    }

    /**
     * @param tasks   TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui      Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToUpdate = tasks.getTask(this.updateIndex);
            tasks.updateTask(taskToUpdate, changes);

            // print message after updating
            ui.printSuccessfulUpdateResponse(updateIndex, changes);

        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
        } catch (InvalidTodoUpdateException e) {
            ui.printInvalidTodoUpdateException();
        } catch (InvalidDeadlineUpdateException e) {
            ui.printInvalidDeadlineUpdateException();
        } catch (InvalidEventUpdateException e) {
            ui.printInvalidEventUpdateException();
        }
    }
}
