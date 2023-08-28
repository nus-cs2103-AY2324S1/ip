import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
			throw new DukeException(String.format(DukeException.DATETIME_FORMAT_INVALID, "from", "event"));
		}

    if (to.isEmpty()) {
      throw new DukeException("The to of a event cannot be empty.");
    }
		try {
			this.to = DatetimeHelper.parse(to);
		} catch (DateTimeParseException e) {
			throw new DukeException(String.format(DukeException.DATETIME_FORMAT_INVALID, "to", "event"));
		}
  }

  @Override
  public String toString() {
    return String.format("%s (from: %s to: %s)", super.toString(), DatetimeHelper.format(from), DatetimeHelper.format(to));
  }

	@Override
	public String toCommand() {
		return String.format("%s /from %s /to %s", super.toCommand(), DatetimeHelper.commandFormat(from), DatetimeHelper.commandFormat(to));
	}
}
