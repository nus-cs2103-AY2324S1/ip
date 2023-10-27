package yong.command;

import yong.tasklist.TaskList;

/**
 * Represents a command for initiating a conversation with the chatbot.
 */
public class StartCommand extends Command {

    /**
     * Constructs a StartCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     */
    public StartCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Prints out the chatbot's name and introduces itself.
     *
     * @return A greeting message from the chatbot.
     */
    @Override
    public String execute() {
        String logo = "YONG";
        outputString = "Hello, I'm " + logo + "\n"
                + "What can I do for you?\n";

        return outputString;
    }
}
