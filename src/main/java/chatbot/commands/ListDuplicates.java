package chatbot.commands;

import chatbot.task.Task;
import chatbot.task.TaskList;

import java.util.ArrayList;

import chatbot.storage.Storage;
import chatbot.ui.Printer;

/**
 * Lists the duplicate tasks in the list.
 */
public class ListDuplicates extends Command {
    
    /**
     * Constructor for a ListDuplicates instance.
     * 
     * @param input String containing task label and other info.
     * @param commandType type of command instance
     */
    public ListDuplicates(String input, CommandType commandType) {
        super(input, commandType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        try {
            String[] duplicateTaskStrings = new String[100];

            duplicateTaskStrings[0] = "Here are the duplicate tasks:";
            ArrayList<Task> duplicateTasks = tasks.getDuplicateTasks();

            for (int i = 1; i < duplicateTasks.size() + 1; i++) {
                String listString = duplicateTasks.get(i - 1).toString();
                duplicateTaskStrings[i] = listString;
            }
            return ui.print(duplicateTaskStrings);
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
}
