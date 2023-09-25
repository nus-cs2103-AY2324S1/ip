package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Deletes duplicate tasks from the list.
 */
public class DeleteDuplicates extends Command {
   
    /**
     * Constructs a DeleteDuplicates instance with a given type and input.
     * @param input String containing task label and other info.
     * @param commandType type of command instance
     */
    public DeleteDuplicates(String input, CommandType commandType) {
        super(input, commandType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        try {
            tasks.deleteDuplicates();
            return ui.print(new String[] {"Duplicates deleted."});
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
}
