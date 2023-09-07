package chatbot.commands;

import chatbot.exceptions.InvalidIndexException;
import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Command that deletes an item from the TaskList, and prints a message.
 */
public class DeleteItem extends Command{

    public DeleteItem(String input, CommandType commandType) {
        super(input, commandType);
    }

    /**
     * {@inheritDoc}
     * 
     * Deletes item from the TaskList and saves it.
     * Prints a message on the UI.
     * 
     * @throws InvalidIndexEception Thrown when no tasks matches the index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) 
        throws InvalidIndexException {
        try {
        int index = Integer.parseInt(input);
        Task deletedTask = tasks.getTask(index);
        tasks.delete(Integer.parseInt(input));
        storage.saveTasks(tasks);

        return ui.print(new String[] {"I knew you couldn't finish it. Or maybe you did. I don't care. Deleted:",
            deletedTask.toString(),
            "Now you have an overwhelming "+ (tasks.getLength() - 1) +
            " things to do"});
        } catch (NumberFormatException e) {
            return ui.showError(new InvalidIndexException("Are you stupid? That's not a number."));
        } catch (IndexOutOfBoundsException e) {
            return ui.showError(new InvalidIndexException("Are you stupid? That's not a number on the list."));
        }
    }
}
