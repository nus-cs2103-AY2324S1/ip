package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidDatetimeFormatException;
import duke.helper.DatetimeHelper;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Event extends Task {
	private LocalDateTime from;
	private LocalDateTime to;

	public Event(String description, boolean mark, String from, String to) {
		super(description, mark, 'E');

		if (from.isEmpty()) {
			throw new DukeException("The from of a event cannot be empty.");
		}
		try {
			this.from = DatetimeHelper.parse(from);
		} catch (DateTimeParseException e) {
			throw new InvalidDatetimeFormatException("from", "event");
		}

		if (to.isEmpty()) {
			throw new DukeException("The to of a event cannot be empty.");
		}
		try {
			this.to = DatetimeHelper.parse(to);
		} catch (DateTimeParseException e) {
			throw new InvalidDatetimeFormatException("to", "event");
		}

		if (this.from.isAfter(this.to)) {
			throw new DukeException("The from of an event must be before its to");
		}
	}

	@Override
	public String toString() {
		return String.format(
				"%s (from: %s to: %s)",
				super.toString(), DatetimeHelper.format(from), DatetimeHelper.format(to));
	}

	@Override
	public String toCommand() {
		return String.format(
				"%s /from %s /to %s",
				super.toCommand(), DatetimeHelper.commandFormat(from), DatetimeHelper.commandFormat(to));
	}

	@Override
	public boolean filter(Optional<LocalDateTime> before) {
		return before
				.filter((datetime) -> datetime.isBefore(from))
				.isEmpty(); // if is empty return true
	}
}
