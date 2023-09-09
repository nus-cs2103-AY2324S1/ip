package duke.commands;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `ToDoCommand` class represents a command to add a new "To-Do" task.
 * It is responsible for parsing the user input, creating a new task, adding it to the task list,
 * updating the storage, and providing a response message.
 */
public class ToDoCommand extends Command {

    private String userInput;

    /**
     * Constructs a new `ToDoCommand` object with the given user input.
     *
     * @param userInput The user input string containing the "To-Do" command.
     */
    public ToDoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the "To-Do" command. It attempts to create a new "To-Do" task based on the user input,
     * adds it to the task list, updates the storage, and returns a response message indicating the result.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of the "To-Do" task addition.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.addTask(Parser.parseStringToTask(userInput, "todo"));
            storage.updateStorage(taskList);
            return ui.addTaskReply(userInput, taskList);
        } catch (InvalidDescriptionException e) {
            return ui.invalidDescriptionExceptionReply();
        } catch (InvalidDateTimeException e) {
            return ui.invalidDateTimeExceptionReply();
        }
    }
}
