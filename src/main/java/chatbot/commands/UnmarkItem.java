package chatbot.commands;

import chatbot.exceptions.InvalidIndexException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

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
    public void execute(TaskList tasks, Storage storage, Ui ui) 
        throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input);
            tasks.unmark(index);
            ui.print(new String[] {
            "You incompetent child. I've unmarked the task. Please get it together.",
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
