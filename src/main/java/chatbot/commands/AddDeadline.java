package chatbot.commands;

import chatbot.exceptions.InvalidDescriptionException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.task.TaskType;
import chatbot.ui.Printer;

/**
 * Adds a deadline task to the TaskList
 * 
 * @author Owen Yeo
 */
public class AddDeadline extends Command {

    /**
     * {@inheritDoc}
     * @param input
     * @param commandType
     */
    public AddDeadline(String input, CommandType commandType) {
        super(input, commandType);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Adds a Deadline to the TaskList of the chatbot and saves it. 
     * Prints a message on the UI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        try {
            tasks.addTask(input, TaskType.DEADLINE);
            storage.saveTasks(tasks);

            return ui.print(new String[] {
                "What? You ain't finishing it. Added: ",
                tasks.getTask(tasks.getLength()).toString(),
                "Now you have an overwhelming " + tasks.getLength() + " things to do."
            });
        } catch (InvalidDescriptionException e) {
            return ui.showError(e);
        }
    }
}
