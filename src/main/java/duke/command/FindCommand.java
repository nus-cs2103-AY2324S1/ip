package duke.command;

import java.util.ArrayList;

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
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        ArrayList<Task> matches = new ArrayList<>();
        String find = this.query.toUpperCase();

        // filter out relevant
        for (Task t : taskList.getAllTask()) {
            if (t.getTask().toUpperCase().contains(find)) {
                matches.add(t);
            }
        }

        // Handles no matches
        int size = matches.size();
        if (size == 0) {
            ui.println("Quack has not found any task matching " + this.query + ", did you spell it correctly?");
            return;
        }

        ui.println("Quack has found" + matches.size() + "matching tasks in your list:");
        for (int i = 0; i < size; i++) {
            ui.println((i + 1) + "." + matches.get(i).toString());
        }
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
