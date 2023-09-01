package chatbot.commands;

import chatbot.exceptions.InvalidIndexException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * Marks item as completed in the list
 * 
 * @author Owen Yeo
 */
public class MarkItem extends Command{

    public MarkItem(String input) {
        super(input);
    }
    
    /**
     * {@inheritDoc}
     * 
     * 
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) 
        throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input);
            tasks.mark(index);
            ui.print(new String[] {
            "Impossible! You must have cheated. Horrible.",
            tasks.getTask(index).toString()
        });

        storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("Are you stupid? That's not a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("That's not even a number on the list, idiot.");
        }
    }
}
