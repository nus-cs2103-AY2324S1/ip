package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;

/**
 * Displays the existing list when executed
 * 
 * @author Owen Yeo
 */
public class DisplayList extends Command{

    public DisplayList(String input, CommandType commandType) {
        super(input, commandType);
    }
    
    /**
     * {@inheritDoc}
     * 
     * Displays the current list on the UI when executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        String[] taskStrings = new String[100] ; 

        for (int i = 1; i < tasks.getLength() + 1; i++) {
            String listString = (i) + ". " + tasks.getTask(i).toString();
            taskStrings[i - 1] = listString;
        }

        return ui.print(taskStrings);
    }
}
