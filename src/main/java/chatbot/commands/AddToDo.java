package chatbot.commands;

import chatbot.exceptions.InvalidDescriptionException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.task.TaskType;
import chatbot.ui.Printer;

/**
 * Adds a To Do task to the TaskList
 *
 * @author Owen Yeo
 */
public class AddToDo extends Command {

    public AddToDo(String input, CommandType commandType) {
        super(input, commandType);
    }

    /**
     * {@inheritDoc}
     *
     * Adds a todo to the TaskList of the chatbot and saves it.
     * Prints a message on the UI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        try {
            tasks.addTask(input, TaskType.TODO);
            storage.saveTasks(tasks);

            String[] output = new String[5];

            if (tasks.detectDuplicate(tasks.getTask(tasks.getLength()).getLabel())) {
                output[0] = "You already have this task in your list. Stop adding duplicates.";
            }

            output[1] = "What? You ain't finishing it. Added: ";
            output[2] = tasks.getTask(tasks.getLength()).toString();
            output[3] = "Now you have an overwhelming " + tasks.getLength() + " things to do.";

            return ui.print(output);
        } catch (InvalidDescriptionException e) {
            return ui.showError(e);
        }
    }
}
