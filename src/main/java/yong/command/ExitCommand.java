package yong.command;

import yong.tasklist.TaskList;

/**
 * Represents the actions needed if the user inputs an Exit command.
 * User input is "bye"
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the deadline command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     */
    public ExitCommand(TaskList taskList) {
        super(taskList);
        isExitCommand = true;
    }

    /**
     * Method to be executed when this command is called.
     * Prints out the end statement of the chatbot.
     */
    public String execute() {
        outputString = "Thank you and have a good day!";

        return outputString;
    }
}
