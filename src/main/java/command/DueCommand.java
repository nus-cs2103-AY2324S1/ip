package command;

import java.time.LocalDate;

import helper.Storage;
import helper.Ui;
import task.TaskList;

/**
 * Represents a Command specifically returns Tasks due on a LocalDate.
 */
public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    private LocalDate dueDate;

    public DueCommand(int index, LocalDate dueDate) {
        super(index);
        this.dueDate = dueDate;
    }

    /**
     * Prints out all Tasks that are due on the LocalDate specified by the User.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        String message = list.dueOn(dueDate);
        ui.print(message);
    }
}
