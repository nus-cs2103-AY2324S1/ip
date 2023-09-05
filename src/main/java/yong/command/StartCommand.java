package yong.command;

import yong.tasklist.TaskList;

/**
 * Represents the actions needed when the user says Hi.
 */
public class StartCommand extends Command {

    /**
     * Constructor for the start command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     */
    public StartCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Method to be executed when this command is called.
     * Prints out the chatbot name and introduces itself.
     */
    @Override
    public String execute() {
        String logo = "YONG";
        outputString = "Hello I'm " + logo + "\n" +
                "What can I do for you? \n";

        return outputString;
    }
}
