package commands;

import client.Rock;
import io.Parser;
import tasks.Task;
import tasks.TaskList;

/**
 * Representation of a command
 * to delete a task from the task list.
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskDelete extends Command {
    /**
     * Constructor to create the
     * delete command
     * @param client Chatbot object
     */
    public CommandTaskDelete(Rock client) {
        super(client);
    }
    /**
     * Removes task from task list
     * @param input Contains data with index of removed task.
     * @throws IllegalArgumentException Thrown when invalid index is given.
     */
    @Override
    public void accept(Parser input) {
        String inputString = input.getDefaultString();
        TaskList taskList = this.client.getTaskList();
        try {
            int taskIdx = Integer.parseInt(inputString);
            if (taskIdx < 1 || taskIdx > taskList.size()) {
                throw new IllegalArgumentException("Invalid index given!");
            } else {
                Task removedTask = taskList.removeTask(taskIdx - 1);
                this.client.getUi().respond("Task successfully removed!\n" + removedTask);
                this.client.getStorage().saveSaveFile();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index given!");
        }
    }
}
