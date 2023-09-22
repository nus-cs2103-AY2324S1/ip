package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks.
 * This command, when executed, will search for tasks that match the given keyword.
 */
public class FindCommand extends Command{

    private final String command;

    /**
     * Initializes a new instance of the FindCommand class.
     *
     * @param command The entire command string input by the user.
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the find command.
     * This method will parse the command string, search for tasks that match the given keyword,
     * and return a string representing the list of matching tasks. If no keyword is provided, it will
     * return an error message. If no tasks match the keyword, it will return a message indicating no results.
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the result of executing the command.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {

        String[] commandInputs = this.command.split("find ");

        List<String> listOfTasks = new ArrayList<>();

        if (commandInputs.length == 1) {
            System.out.println("No keyword given!");
        } else {
            listOfTasks = tasks.searchByKeyword(commandInputs[1]);
        }

        return executeHelper(listOfTasks);
    }

    /**
     * Constructs the response string for the list of matching tasks.
     * This method constructs a formatted string of tasks, representing each task
     * in the provided list of tasks. If the list is empty, a message indicating no
     * matching tasks will be included in the returned string.
     *
     * @param listOfTasks A list of strings representing the tasks matching a search keyword.
     * @return A string representing the matched tasks or a message indicating no matches.
     */
    private String executeHelper(List<String> listOfTasks) {

        assert command != null : "Should have an input!";
        StringBuilder result = new StringBuilder();

        if (listOfTasks.size() == 0) {

            result.append("No results with such keyword!");
            return result.toString();

        } else {
            int lastTask = listOfTasks.size();
            int i = 1;
            for (String t: listOfTasks) {
                if (i == lastTask) {
                    result.append(i).append(".").append(t);
                } else {
                    result.append(i).append(".").append(t).append("\n");
                }
                i++;
            }
        }
        String MATCHING_STRING = "Here are the matching tasks in your list:";
        return MATCHING_STRING + "\n" + result;
    }
}
