package duke.command;

import duke.exception.InvalidDatetimeFormatException;
import duke.helper.DatetimeHelper;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a list command. Has an optional /before argument to return tasks
 * before a date.
 */
public class ListCommand extends Command {
	private String before;

	/**
	 * Returns a ListCommand
	 *
	 * @param out      Printer to print output to
	 * @param tasklist Tasklist to read
	 * @param file     File to save tasks to
	 * @param before   the optional date argument to filter tasks before date
	 * @return a ListCommand
	 */
	public ListCommand(Printer out, TaskList taskList, FileIO file, String before) {
		super(out, taskList, file);
		this.before = before;
	}

	/**
	 * The actions to take during the command
	 *
	 * @throws DateTimeParseException when before is not "" or a valid datetime
	 */
	@Override
	public void action() {
		Optional<LocalDateTime> beforeTime = Optional.empty();
		if (!before.equals("")) {
			try {
				beforeTime = Optional.of(DatetimeHelper.parse(before));
			} catch (DateTimeParseException e) {
				throw new InvalidDatetimeFormatException("before", "list");
			}
		}

		out.print(taskList.filter(beforeTime));
  }
}
