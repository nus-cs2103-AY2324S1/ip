package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidDatetimeFormatException;
import duke.helper.DatetimeHelper;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Deadline extends Task {
	private LocalDateTime by;

	public Deadline(String description, boolean mark, String by) {
		super(description, mark, 'D');

		if (by.isEmpty()) {
			throw new DukeException("The by of a deadline cannot be empty.");
		}
		try {
			this.by = DatetimeHelper.parse(by);
		} catch (DateTimeParseException e) {
			throw new InvalidDatetimeFormatException("by", "deadline");
		}
	}

	@Override
	public String toString() {
		return String.format("%s (by: %s)", super.toString(), DatetimeHelper.format(by));
	}

	@Override
	public String toCommand() {
		return String.format("%s /by %s", super.toCommand(), DatetimeHelper.commandFormat(by));
	}

	@Override
	public boolean filter(Optional<LocalDateTime> before) {
		return before.filter((datetime) -> datetime.isBefore(by)).isEmpty(); // if is empty return true
	}
}
