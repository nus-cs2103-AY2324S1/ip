package cyrus.commands;

import java.time.LocalDate;
import java.util.HashMap;

import cyrus.parser.ParseInfo;
import cyrus.tasks.Deadline;
import cyrus.tasks.Task;
import cyrus.tasks.TaskList;
import cyrus.ui.CliUi;
import cyrus.utility.DateUtility;

/**
 * Command to add a {@code Deadline} to the given {@code TaskList}.
 */
public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    /**
     * To add a {@code Deadline}, must ensure that the deadline has an argument (i.e. name of the
     * deadline) and it contains the {@code by} option which corresponds to a valid {@code
     * LocalDate} format which is {@code dd/MM/yyyy}.
     *
     * @throws CommandError if no arguments are present, no {@code by} option is provided, or date
     *                      format is invalid.
     */
    @Override
    public void execute() throws CommandError {
        if (this.parseInfo.hasNoArgument()) {
            throw new CommandError("Deadline is missing a body!");
        }

        HashMap<String, String> options = this.parseInfo.getOptions();
        if (!options.containsKey("by")) {
            throw new CommandError("Invalid deadline format: missing /by");
        }

        String deadlineName = this.parseInfo.getArgument();
        String deadlineBy = this.parseInfo.getOptions().get("by");
        LocalDate deadlineDate = DateUtility.parse(deadlineBy);
        if (deadlineDate == null) {
            throw new CommandError("Invalid deadline format: invalid by string, must be format dd/MM/yyyy");
        }

        Task deadline = new Deadline(deadlineName, deadlineDate);
        this.taskList.addTask(deadline);
        CliUi.printAddTask(deadline, this.taskList.size());
    }
}
