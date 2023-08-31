package duke.task;

import duke.command.Command;
import duke.exception.DukeException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public abstract class Task {
  private boolean mark;
  private final String description;
  private final char type;

  public Task(String description, boolean mark, char type) throws DukeException {
    this.description = description;
    this.mark = mark;
    this.type = type;

    if (description.isEmpty()) {
      throw new DukeException(
          String.format(
              "The description of a %s cannot be empty.",
              this.getClass().getSimpleName().toLowerCase()));
    }
  }

  public static Task createTask(String type, String name, Map<String, String> arguments) {
    switch (type) {
      case Command.TODO:
        return new Todo(name, arguments.containsKey("mark"));
      case Command.DEADLINE:
        return new Deadline(name, arguments.containsKey("mark"), arguments.getOrDefault("by", ""));
      case Command.EVENT:
        return new Event(
            name,
            arguments.containsKey("mark"),
            arguments.getOrDefault("from", ""),
            arguments.getOrDefault("to", ""));
    }
    throw new DukeException(String.format("%s is not an task type", type));
  }

  public void mark() {
    mark = true;
  }

  public void unmark() {
    mark = false;
  }

  @Override
  public String toString() {
    return String.format("  [%c][%c] %s", type, mark ? 'X' : ' ', description);
  }

  public String toCommand() {
    return String.format(
        "%s %s %s",
        this.getClass().getSimpleName().toLowerCase(), description, mark ? "/mark" : "");
  }

  public boolean filter(Optional<LocalDateTime> before) {
    return before.isEmpty();
  }
	
	public boolean filter(String find) {
		return description.contains(find);
	}
}
