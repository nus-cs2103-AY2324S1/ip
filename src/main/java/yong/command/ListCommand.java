package yong.command;

import yong.tasklist.TaskList;

/**
 * Represents a command for listing all tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     */
    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Prints out all tasks in the task list in the specified format.
     *
     * @return A formatted list of tasks or a message if there are no tasks.
     */
    public String execute() {
        outputString = taskList.list();
        if (outputString.length() == 0) {
            return "There are no current tasks!";
        } else {
            return outputString;
        }
    }
}

