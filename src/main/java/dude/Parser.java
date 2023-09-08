package dude;

import dude.command.AddTaskCommand;
import dude.command.DeleteTaskCommand;
import dude.command.DudeCommand;
import dude.command.DudeCommandId;
import dude.command.ExitCommand;
import dude.command.FindTaskCommand;
import dude.command.ListTasksCommand;
import dude.command.MarkTaskCommand;
import dude.command.UnmarkTaskCommand;
import dude.exception.DudeException;
import dude.exception.EventEndMissingException;
import dude.exception.EventStartMissingException;
import dude.exception.InvalidCommandException;
import dude.exception.InvalidDateTimeArgumentException;
import dude.exception.InvalidTaskIndexException;
import dude.exception.SearchStringMissingException;
import dude.exception.TaskDeadlineMissingException;
import dude.exception.TaskDescriptionMissingException;
import dude.exception.TaskIndexMissingException;
import dude.task.DeadlineTask;
import dude.task.EventTask;
import dude.task.Task;
import dude.task.ToDoTask;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Handles parsing of user input.
 */
public class Parser {
  /**
   * Converts datetime argument in task command into a LocalDateTime object.
   *
   * @param dateTimeString String argument for date and time.
   * @return LocalDateTime object parsed from string.
   * @throws InvalidDateTimeArgumentException If datetime cannot be parsed from the string.
   */
  public static LocalDateTime parseDateTime(String dateTimeString) throws InvalidDateTimeArgumentException {
    String[] splitDateTime = dateTimeString.split(" ", 2);
    try {
      String dateString = splitDateTime[0];
      String timeString = splitDateTime[1];
      String[] splitDate = dateString.split("/", 3);
      int date = Integer.parseInt(splitDate[0]);
      int month = Integer.parseInt(splitDate[1]);
      int year = Integer.parseInt(splitDate[2]);
      int hour = Integer.parseInt(timeString.substring(0, 2));
      int minute = Integer.parseInt(timeString.substring(2));

      return LocalDateTime.of(year, month, date, hour, minute);
    } catch (IndexOutOfBoundsException | NumberFormatException | DateTimeException e) {
      throw new InvalidDateTimeArgumentException();
    }
  }

  /**
   * Parses command with task number with format `{cmd} {index}`, getting corresponding task.
   *
   * @param input Input command.
   * @return Index of task specified in command.
   * @throws InvalidTaskIndexException If index is invalid.
   * @throws TaskIndexMissingException If index is not specified.
   */
  public static int parseTaskIndex(String input) throws InvalidTaskIndexException, TaskIndexMissingException {
    String[] splitInput = input.split(" ", 3);
    if (splitInput.length < 2) {
      throw new TaskIndexMissingException();
    } else {
      String specifiedTask = splitInput[1];
      try {
        return Integer.parseInt(specifiedTask);
      } catch (IndexOutOfBoundsException e) {
        // task number not specified
        throw new TaskIndexMissingException();
      } catch (NumberFormatException e) {
        // cannot parse number from input
        throw new InvalidTaskIndexException(specifiedTask);
      }
    }
  }

  /**
   * Parses todo task command.
   *
   * @param input Input command.
   * @return Add command for todo task.
   * @throws TaskDescriptionMissingException If task description is missing.
   */
  public static AddTaskCommand parseTodo(String input) throws TaskDescriptionMissingException {
    String[] splitInput = input.split(" ", 2);
    if (splitInput.length < 2) {
      // task description not specified
      throw new TaskDescriptionMissingException();
    }
    String description = splitInput[1].trim();
    Task task = new ToDoTask(description);
    return new AddTaskCommand(task);

  }

  /**
   * Parses deadline task command.
   *
   * @param input Input command.
   * @return Add command for deadline task.
   * @throws TaskDescriptionMissingException  If task description is missing.
   * @throws TaskDeadlineMissingException     If task deadline is missing.
   * @throws InvalidDateTimeArgumentException If datetime cannot be parsed from the string argument.
   */
  public static AddTaskCommand parseDeadline(String input)
    throws TaskDescriptionMissingException, TaskDeadlineMissingException,
    InvalidDateTimeArgumentException {
    String[] splitInput = input.split(" ", 2);
    if (splitInput.length < 2) {
      // task description not specified
      throw new TaskDescriptionMissingException();
    }
    String[] splitDeadline = splitInput[1].split("/by", 2);
    if (splitDeadline.length < 2) {
      // deadline not specified
      throw new TaskDeadlineMissingException();
    }
    String description = splitDeadline[0].trim();
    String deadlineArg = splitDeadline[1].trim();
    LocalDateTime deadline = parseDateTime(deadlineArg);
    Task task = new DeadlineTask(description, deadline);
    return new AddTaskCommand(task);
  }

  /**
   * Parses event task command.
   *
   * @param input Input command.
   * @return Add command for event task.
   * @throws TaskDescriptionMissingException If task description is missing.
   * @throws EventStartMissingException      If event start is missing.
   * @throws EventEndMissingException        If event end is missing.
   */
  public static AddTaskCommand parseEvent(String input)
    throws TaskDescriptionMissingException, EventStartMissingException, EventEndMissingException,
    InvalidDateTimeArgumentException {
    String[] splitInput = input.split(" ", 2);
    if (splitInput.length < 2) {
      // task description not specified
      throw new TaskDescriptionMissingException();
    }
    String[] splitStart = splitInput[1].split("/from", 2);
    if (splitStart.length < 2) {
      // start date not specified
      throw new EventStartMissingException();
    }
    String[] splitEnd = splitStart[1].split("/to", 2);
    if (splitEnd.length < 2) {
      // end date not specified
      throw new EventEndMissingException();
    }
    String description = splitStart[0].trim();
    String startArg = splitEnd[0].trim();
    LocalDateTime start = parseDateTime(startArg);
    String endArg = splitEnd[1].trim();
    LocalDateTime end = parseDateTime(endArg);
    Task task = new EventTask(description, start, end);
    return new AddTaskCommand(task);
  }

  /**
   * Parses find command.
   *
   * @param input Input command.
   * @return Find command.
   * @throws SearchStringMissingException If substring to search is missing.
   */
  public static FindTaskCommand parseFind(String input)
    throws SearchStringMissingException {
    String[] splitInput = input.split(" ", 2);
    if (splitInput.length < 2) {
      // search string not specified
      throw new SearchStringMissingException();
    }
    String searchString = splitInput[1];
    return new FindTaskCommand(searchString);
  }

  /**
   * Parses text input and returns dude.command.DudeCommand instance.
   *
   * @param input Input to parse.
   * @return dude.command.DudeCommand instance parsed from input.
   * @throws DudeException If command cannot be parsed.
   */
  public static DudeCommand parse(String input) throws DudeException {
    // extract command (strip leading and trailing whitespace, take first word)
    String[] splitInput = input.split(" ", 2);
    String cmdString = splitInput[0].toLowerCase();
    try {
      DudeCommandId cmd = DudeCommandId.valueOf(cmdString);
      switch (cmd) {
        case bye:
          return new ExitCommand();
        case list:
          return new ListTasksCommand();
        case mark:
          return new MarkTaskCommand(parseTaskIndex(input));
        case unmark:
          return new UnmarkTaskCommand(parseTaskIndex(input));
        case delete:
          // Fallthrough - alias
        case remove:
          return new DeleteTaskCommand(parseTaskIndex(input));
        case todo:
          return parseTodo(input);
        case deadline:
          return parseDeadline(input);
        case event:
          return parseEvent(input);
        case find:
          return parseFind(input);
        default:
          throw new InvalidCommandException();
      }
    } catch (IllegalArgumentException e) {
      // invalid command entered
      throw new InvalidCommandException();
    }
  }
}

