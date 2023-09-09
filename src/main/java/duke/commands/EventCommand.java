package duke.commands;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 * Parses the user input to create an event task and adds it to the list.
 */
public class EventCommand extends Command {
    private String userInput;

    /**
     * Constructs a new EventCommand with the user input.
     *
     * @param userInput The user input string containing details of the event task.
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the EventCommand. Parses the user input to create an event task,
     * adds it to the task list, and updates the storage.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of adding the event task.
     * @throws InvalidDescriptionException If the task description is missing or invalid.
     * @throws InvalidDateTimeException    If the event date and time are missing or in an invalid format.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.addTask(Parser.parseStringToTask(userInput, "event"));
            storage.updateStorage(taskList);
            return ui.addTaskReply(userInput, taskList);
        } catch (InvalidDescriptionException e) {
            return ui.invalidDescriptionExceptionReply();
        } catch (InvalidDateTimeException e) {
            return ui.invalidDateTimeExceptionReply();
        }
    }
}
