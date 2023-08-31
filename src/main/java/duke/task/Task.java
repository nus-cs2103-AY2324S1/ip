package duke.task;

import duke.command.Command;
import duke.exception.DukeException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/** 
 * Represents a task object, with as mark, description and type. 
 */
public abstract class Task {
  private boolean mark;
  private final String description;
  private final char type;

  /**
   * Returns a Task
   *
   * @param description the task description
   * @param mark if the task is marked
   * @param type the type of task
   * @return the constructed Task
   * @throws DukeException if the description is empty
   */
  protected Task(String description, boolean mark, char type) throws DukeException {
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

  /**
   * Returns a Task created from the arguments.
   *
   * @param type the type of task
   * @param name the description of task
   * @param arguments the argument key value pairs
   * @return the created Task
   * @throws DukeException if type is not a task type
   */
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

  /** 
	 * Marks the task. 
	 */
  public void mark() {
    mark = true;
  }

  /**
	 * Unmarks the task 
	 */
  public void unmark() {
    mark = false;
  }

  /**
   * Returns a string representation of the Task.
   *
   * @return a string of the Task
   */
  @Override
  public String toString() {
    return String.format("  [%c][%c] %s", type, mark ? 'X' : ' ', description);
  }

  /**
   * Returns the command used to create the task.
   *
   * @return the string command
   */
  public String toCommand() {
    return String.format(
        "%s %s%s",
        this.getClass().getSimpleName().toLowerCase(), description, mark ? " /mark" : "");
  }

  /**
   * Returns if the Task should be filtered
   *
   * @param before the datetime the Task should be before
   * @return if the Task should be filtered.
   */
  public boolean filter(Optional<LocalDateTime> before) {
    return before.isEmpty();
  }

  /**
   * Returns if find is in task description
   *
   * @param find The string to search in description.
   * @return if the Task should be filtered
   */
  public boolean filter(String find) {
    return description.contains(find);
  }
}
