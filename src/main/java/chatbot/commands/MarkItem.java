package chatbot.commands;

import chatbot.exceptions.InvalidIndexException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Marks item as completed in the list
 * 
 * @author Owen Yeo
 */
public class MarkItem extends Command{

    public MarkItem(String input, CommandType commandType) {
        super(input, commandType);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Marks an item on the tasklist as done, and prints a message on the UI.
     * Saves the change on the text file.
     * 
     * @throws InvalidIndexEception Thrown when no tasks matches the index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) 
        throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input);
            tasks.mark(index);
            storage.saveTasks(tasks);
            
            return ui.print(new String[] {
            "Impossible! You must have cheated. Horrible.",
            tasks.getTask(index).toString()
        });
        } catch (NumberFormatException e) {
            return ui.showError(new InvalidIndexException("Are you stupid? That's not a number."));
        } catch (IndexOutOfBoundsException e) {
            return ui.showError(new InvalidIndexException("Are you stupid? That's not a number on the list."));
        }
    }
}
