package chatbot.commands;

import chatbot.exceptions.InvalidIndexException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Unmarks a completed item as undone.
 * 
 * @author Owen Yeo
 */
public class UnmarkItem extends Command{

    public UnmarkItem(String input, CommandType commandType) {
        super(input, commandType);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Unmarks a task on the TaskList as undone, and prints a message.
     * Saves the change on a data file.
     * 
     * @throws InvalidIndexEception Thrown when no tasks matches the index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) 
        throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input);
            tasks.unmark(index);

            storage.saveTasks(tasks);

            return ui.print(new String[] {
            "You incompetent child. I've unmarked the task. Please get it together.",
            tasks.getTask(index).toString()
        });
        } catch (NumberFormatException e) {
            return ui.showError(new InvalidIndexException("Are you stupid? That's not a number."));
        } catch (IndexOutOfBoundsException e) {
            return ui.showError(new InvalidIndexException("Are you stupid? That's not a number on the list."));
        }
    }
}
