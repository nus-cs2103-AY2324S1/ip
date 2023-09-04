package chatbot.commands;

import chatbot.exceptions.InvalidDescriptionException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.task.TaskType;
import chatbot.ui.Ui;

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
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            tasks.addTask(input, TaskType.DEADLINE);

            ui.print(new String[] {
                "What? You ain't finishing it. Added: ",
                tasks.getTask(tasks.getLength()).toString(),
                "Now you have an overwhelming " + tasks.getLength() + " things to do."
            });

            storage.saveTasks(tasks);
        } catch (InvalidDescriptionException e) {
            ui.showError(e);
        }
    }
}
