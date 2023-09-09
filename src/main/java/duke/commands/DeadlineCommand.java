package duke.commands;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 * Parses the user input to create a deadline task and adds it to the list.
 */
public class DeadlineCommand extends Command {
    private String userInput;

    /**
     * Constructs a new DeadlineCommand with the user input.
     *
     * @param userInput The user input string for creating the deadline task.
     */
    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the DeadlineCommand. Parses the user input to create a deadline task,
     * adds it to the task list, and updates the storage.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of the add task operation.
     * @throws InvalidDescriptionException If the task description is invalid.
     * @throws InvalidDateTimeException    If the task date and time are invalid.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.addTask(Parser.parseStringToTask(userInput, "deadline"));
            storage.updateStorage(taskList);
            return ui.addTaskReply(userInput, taskList);
        } catch (InvalidDescriptionException e) {
            return ui.invalidDescriptionExceptionReply();
        } catch (InvalidDateTimeException e) {
            return ui.invalidDateTimeExceptionReply();
        }
    }
}
