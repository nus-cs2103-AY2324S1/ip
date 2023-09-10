package yong.command;

import yong.exception.DukeException;
import yong.tasklist.TaskList;

/**
 * Represents the actions needed if the user inputs Sort command
 */
public class SortCommand extends Command {

    private String inp;

    /**
     * Constructor for the Sort command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param inp      Line of input from the CLI
     */
    public SortCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inp = inp;
    }

    /**
     * Method to be executed when this command is called.
     * Sort functionalities supports: Chronologically, Alphabetically
     * E.g. sort deadlines chronologically, sort all alphabetically
     * Sorts the list according to the input field
     */
    @Override
    public String execute() {
        try {
            String[] parts = inp.split(" ", 3);
            String sortTaskType = parts[1];
            String sortBy = parts[2];

            taskList.sort(sortTaskType, sortBy);

            outputString = "The array has been sorted " + sortBy;

            return outputString;
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for the find command");
        }

    }
}
