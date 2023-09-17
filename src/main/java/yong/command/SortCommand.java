package yong.command;

import yong.exception.DukeException;
import yong.tasklist.TaskList;

/**
 * Represents a command for sorting tasks.
 */
public class SortCommand extends Command {

    private String inputString;

    /**
     * Constructs a SortCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     * @param inp      The input line from the command-line interface.
     */
    public SortCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inputString = inp;
    }

    /**
     * Sorts the list of tasks based on the specified criteria.
     *
     * @return A message indicating that the list has been sorted.
     * @throws DukeException If the input is invalid.
     */
    @Override
    public String execute() {
        try {
            String[] parts = inputString.split(" ", 3);
            String sortTaskType = parts[1];
            String sortBy = parts[2];

            taskList.sort(sortTaskType, sortBy);

            outputString = "The list has been sorted " + sortBy;

            return outputString;
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for the sort command");
        }
    }
}
