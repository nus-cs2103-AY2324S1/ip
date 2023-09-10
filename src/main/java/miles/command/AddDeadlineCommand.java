package miles.command;

import miles.MilesException;
import miles.Storage;
import miles.TaskList;
import miles.Ui;
import miles.task.Deadline;

public class AddDeadlineCommand extends Command {
    private String input;

    /**
     * Constructor to create a new deadline command.
     * @param input user input
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Deadline newDeadline = new Deadline(input);
            storage.saveWhenAddTask(newDeadline, taskList);
        
            int n = taskList.getSize();
            ui.printAddedTask(newDeadline, n);
        } catch (MilesException e) {
            ui.printErrorMsg(e.getMessage());
        }
    }
}
