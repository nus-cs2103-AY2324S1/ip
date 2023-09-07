package chatbot.commands;

import chatbot.exceptions.InvalidDescriptionException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.task.TaskType;
import chatbot.ui.Printer;

/**
 * Add an event task to the TaskList
 * 
 * @author Owen Yeo
 */
public class AddEvent extends Command {

    public AddEvent(String input, CommandType commandType) {
        super(input, commandType);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Adds an Event to the TaskList of the chatbot and saves it. 
     * Prints a message on the UI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        try {
            tasks.addTask(input, TaskType.EVENT);
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
