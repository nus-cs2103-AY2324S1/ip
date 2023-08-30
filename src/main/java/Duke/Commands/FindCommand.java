package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class FindCommand extends Command{
    TaskList taskList;
    Ui ui;
    String input;

    /**
     * Constructor for the find command
     *
     * @param taskList The taskList that stores the task
     * @param ui The UI which will print the output
     * @param input The keyword for searching
     */
    public FindCommand(String input, TaskList taskList, Ui ui) throws DukeException {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * The function which executes the find function in UI class
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
