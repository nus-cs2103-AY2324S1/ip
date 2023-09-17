package yong.command;

import yong.exception.DukeException;
import yong.tasklist.TaskList;

/**
 * Represents a command for finding tasks containing a specified keyword.
 */
public class FindCommand extends Command {

    private String inputString;

    /**
     * Constructs a FindCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     * @param inp The input line from the command-line interface.
     */
    public FindCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inputString = inp;
    }

    /**
     * Prints out the tasks that contain the specified keyword.
     *
     * @return A list of tasks containing the keyword.
     * @throws DukeException If the input is invalid.
     */
    @Override
    public String execute() {
        try {
            String[] parts = inputString.split(" ", 2);
            String lookupString = parts[1];

            outputString = taskList.find(lookupString);

            return outputString;
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for the find command");
        }
    }
}

