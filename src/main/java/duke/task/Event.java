package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidDatetimeFormatException;
import duke.helper.DatetimeHelper;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Represents a Event task, with an additional from and to datetime in addition
 * to Task arguments.
 */
public class Event extends Task {
	private LocalDateTime from;
	private LocalDateTime to;

	/**
	 * Returns an Event
	 *
	 * @param description the task description
	 * @param mark        if the task is marked
	 * @param from        the from deadline
	 * @param to          the to deadline
	 * @return the constructed Event
	 * @throws DukeException                  if from or to is empty, or from is
	 *                                        after to
	 * @throws InvalidDatetimeFormatException if from or to is not valid datetime
	 */
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

	/**
	 * Returns a string representation of Event. Adds from and to argument to the
	 * task string.
	 *
	 * @return a string representation of Event
	 */
	@Override
	public String toString() {
		return String.format(
				"%s (from: %s to: %s)",
				super.toString(), DatetimeHelper.format(from), DatetimeHelper.format(to));
	}

	/**
	 * Returns a string representation of the command used to construct Event
	 *
	 * @return a string of the command used.
	 */
	@Override
	public String toCommand() {
		return String.format(
				"%s /from %s /to %s",
				super.toCommand(), DatetimeHelper.commandFormat(from), DatetimeHelper.commandFormat(to));
	}

	/**
	 * Returns if the Event should be filtered
	 *
	 * @param before the comparison date
	 * @return returns if the event from is before the argument
	 */
	@Override
	public boolean filter(Optional<LocalDateTime> before) {
		return before
				.filter((datetime) -> datetime.isBefore(from))
				.isEmpty(); // if is empty return true
	}
}
