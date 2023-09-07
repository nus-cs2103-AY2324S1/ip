package chatbot.commands;


import chatbot.task.TaskList;
import chatbot.ui.Printer;
import chatbot.storage.Storage;

/**
 * Finds the items in the list that match the query.
 */
public class FindTask extends Command{

    public FindTask(String input, CommandType commandType) {
        super(input, commandType);
    }

    /**
     * {@inheritDoc}
     * 
     * Finds the tasks that contain the words found in the input.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Printer ui) {
        String[] validStrings = new String[100];
        validStrings[0] = "Here are items that match your search:";

        int index = 1;
        for (int i = 1; i < tasks.getLength() + 1; i++) {
            String taskString = tasks.getTask(i).toString();
            if (taskString.indexOf(input) != -1) {
                validStrings[index] = index + ". " + taskString;
                index++;
            }
        }

        return ui.print(validStrings);
    }

    
}
