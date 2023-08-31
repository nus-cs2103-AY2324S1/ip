package helpbuddy.command;

import java.util.ArrayList;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

/**
 * A FindCommand class that finds a list of possible task from the keyword that user entered and calls Ui to
 * print out the list.
 */
public class FindCommand extends Command {
    private String taskDescription;

    /**
     * Constructs a FindCommand Object with the String taskDescription that user inputs into HelpBuddy chatbot.
     * @param taskDescription the String representation of Task description user is finding.
     * @throws HelpBuddyException if the taskDescription is empty.
     */
    public FindCommand(String taskDescription) throws HelpBuddyException {
        if (taskDescription.isBlank()) {
            throw new HelpBuddyException("Please enter the task you are trying to find.\n");
        }
        this.taskDescription = taskDescription;
    }

    /**
     * Finds an ArrayList<Task> of Task objects in taskList that has taskDescription and calls ui to print the list.
     * @param taskList the taskList that stores Task objects.
     * @param ui the ui that prints the corresponding response from HelpBuddy chatbot.
     * @param storage the storage that loads and saves data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> commonTaskList = taskList.findCommonTask(taskDescription);
        ui.printFindTaskMessage(commonTaskList);
    }
}
