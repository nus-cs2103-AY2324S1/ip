package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * A command to find tasks with the keyword specified.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private String input;

    /**
     * Constructor for the find command.
     *
     * @param taskList The taskList that stores the task.
     * @param ui The UI which will print the output.
     * @param input The keyword for searching.
     */
    public FindCommand(String input, TaskList taskList, Ui ui) throws DukeException {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * The function which executes the find function in UI class.
     *
     */
    @Override
    public void execute() throws DukeException {
        String[] inputList = this.input.split(" ");
        try {
            if (inputList.length == 1) {
                throw new DukeException("Your input is wrong. This is unaaceptable");
            }
            ui.findFilteredTasks(taskList, input.substring(5));
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }
}
