package yong.command;

import yong.tasklist.TaskList;

/**
 * Represents a command for exiting the chatbot.
 * The user input is "bye".
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     */
    public ExitCommand(TaskList taskList) {
        super(taskList);
        isExitCommand = true;
    }

    /**
     * Prints out the farewell message of the chatbot.
     *
     * @return A farewell message.
     */
    public String execute() {
        outputString = "Thank you and have a good day!";
        return outputString;
    }
}
