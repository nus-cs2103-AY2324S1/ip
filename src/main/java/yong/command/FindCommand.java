package yong.command;

import yong.exception.DukeException;
import yong.tasklist.TaskList;

/**
 * Represents the actions needed if the user inputs a deadline task
 */
public class FindCommand extends Command {

    String inp;

    /**
     * Constructor for the Find command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param inp      Line of input from the CLI
     */
    public FindCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inp = inp;
    }

    /**
     * Method to be executed when this command is called.
     * Prints out the tasks that contain the keyword.
     */
    @Override
    public String execute() {
        try {
            String[] parts = inp.split(" ", 2);
            String lookupString = parts[1];

            outputString = taskList.find(lookupString);

            return outputString;
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for the find command");
        }

    }
}