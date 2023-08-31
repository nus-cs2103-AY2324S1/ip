package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void execute() {
        try {
            String[] parts = inp.split(" ", 2);
            String lookupString = parts[1];

            taskList.find(lookupString);
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for the find command");
        }

    }
}