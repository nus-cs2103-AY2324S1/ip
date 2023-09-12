package duke.command;

import duke.util.Keyword;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Time;
import duke.util.Ui;
import duke.exception.DukeException;
import duke.exception.PrintDateException;

/**
 * Represents a print date command. A <code>PrintDateCommand</code> object
 * corresponds to an executable command that prints out all tasks that
 * are happening on a certain date when executed.
 */
public class PrintDateCommand extends Command {

    private String commandBody;

    /**
     * Constructs a <code>PrintDateCommand</code> object.
     *
     * @param commandBody The body of the command.
     */
    public PrintDateCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }

    /**
     * Prints out all tasks that are happening on a certain date.
     *
     * @param taskList The task list that contains the tasks.
     * @param ui       The user interface to print out the tasks.
     * @param storage  The storage.
     * @return
     * @throws DukeException If the command is invalid, or if there is
     *                       nothing in the list or if nothing is happening on that date.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String err = "OOPS!!! The command for print_date task is invalid.";
        String[] printTask = commandBody.split(" /on ");
        if (printTask.length != 2) {
            throw new PrintDateException(err);
        }
        if (!printTask[0].equals("deadline") && !printTask[0].equals("event")) {
            throw new PrintDateException(err);
        }

        return taskList.printDateTask(
                Keyword.valueOf(printTask[0].toUpperCase()),
                Time.parseDate(printTask[1]),
                ui);
    }
}
