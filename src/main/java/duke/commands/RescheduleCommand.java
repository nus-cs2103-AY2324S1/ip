package duke.commands;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidRescheduleException;
import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `RescheduleCommand` class represents a command to reschedule a scheduled task.
 */
public class RescheduleCommand extends Command {
    private String userInput;

    /**
     * Constructs a new `RescheduleCommand` object with the given user input.
     *
     * @param userInput The user input string containing the "reschedule" command.
     */
    public RescheduleCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the "reschedule" command. It attempts to reschedule the specified task, updates the storage,
     * and returns a response message indicating the result.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of the "reschedule" operation.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            Task task = taskList.getTask(Parser.taskIndexToReschedule(userInput, taskList));
            task.reschedule(userInput);
            storage.updateStorage(taskList);
            return Ui.rescheduleReply(task);
        } catch (InvalidRescheduleException e) {
            return Ui.invalidRescheduleExceptionReply();
        } catch (InvalidDateTimeException e) {
            return Ui.invalidDateTimeExceptionReply();
        } catch (MissingTaskIndexException e) {
            return ui.missingTaskIndexExceptionReply();
        } catch (InvalidTaskIndexException e) {
            return ui.invalidTaskIndexExceptionReply(taskList);
        }
    }


}
