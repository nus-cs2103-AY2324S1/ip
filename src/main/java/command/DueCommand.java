package command;

import java.time.LocalDate;

import helper.Storage;
import helper.Ui;
import task.TaskList;

/**
 * Represents a Command specifically returns Tasks due on a LocalDate.
 */
public class DueCommand extends Command {
    /** Command the user starts with to activate the DueCommand. */
    public static final String COMMAND_WORD = "due";
    /** A LocalDate a Deadline is supposed to be due on. */
    private LocalDate dueDate;

    /**
     * Constructs a DueCommand with an Index and a DueDate.
     * @param index
     * @param dueDate
     */
    public DueCommand(int index, LocalDate dueDate) {
        super(index);
        this.dueDate = dueDate;
    }

    /**
     * Prints out all Tasks that are due on the LocalDate specified by the User.
     * @param list
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String message = list.dueOn(dueDate);
        //        ui.print(message);
        return message;
    }
}
