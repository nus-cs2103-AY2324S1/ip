package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;
import duke.task.Task;

/**
 * Find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    /**
     * Stores the search query
     */
    private String query;

    /**
     * Constructor of the todo command
     *
     * @param query - the desc of the command
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @return the reply of Quack
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        String find = this.query.toUpperCase();

        // filter out relevant
        List<Task> matches = Arrays.stream(taskList.getAllTask())
                .filter(task -> task.getTask().toUpperCase().contains(find))
                .collect(Collectors.toList());

        // Handles no matches
        int size = matches.size();
        if (size == 0) {
            return "Quack has not found any task matching " + this.query + ", did you spell it correctly?";
        }
        String ret = "Quack has found " + matches.size() + " matching tasks in your list:";
        for (int i = 0; i < size; i++) {
            ret += "\n" + (i + 1) + ". " + matches.get(i).toString();
        }
        return ret;
    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if it is the exact same command
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof FindCommand) {
            FindCommand command = (FindCommand) other;
            return this.query.equals(command.query);
        }
        return false;
    }
}
