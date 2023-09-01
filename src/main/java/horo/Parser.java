package horo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import horo.commands.AddDeadlineCommand;
import horo.commands.AddEventCommand;
import horo.commands.AddTodoCommand;
import horo.commands.Command;
import horo.commands.DeleteCommand;
import horo.commands.ExitCommand;
import horo.commands.FindCommand;
import horo.commands.ListCommand;
import horo.commands.MarkCommand;
import horo.commands.UnmarkCommand;
import horo.data.Deadline;
import horo.data.Event;
import horo.data.Task;
import horo.data.Todo;

/**
 * Abstract Parser class that has static methods to parse strings
 */
public abstract class Parser {

  private static final Pattern commandPattern = Pattern
      .compile("^(deadline|todo|event|bye|mark|unmark|list|delete|find)");

  /**
   * Returns a task by parsing string from data file
   * 
   * @param s Input string from data file
   * @return Task
   * @throws HoroException
   */
  public static Task parseDataString(String s) throws HoroException {
    String[] arguments = s.split(",");
    Task t;
    switch (arguments[0]) {
      case "T":
        t = new Todo(arguments[2]);
        break;
      case "D":
        t = new Deadline(arguments[2], arguments[3]);
        break;
      case "E":
        t = new Event(arguments[2], arguments[3], arguments[4]);
        break;
      default:
        throw new HoroException("Bad Command");
    }
    if (arguments[1].equals("1")) {
      t.markDone();
    }
    return t;
  }

  public static Command parse(String input) throws HoroException {
    Matcher m = commandPattern.matcher(input);

    if (!m.find()) {
      throw new HoroException("Invalid Command");
    }

    String s = m.group(1);

    Command c;

    switch (s) {
      case "bye":
        c = new ExitCommand();
        break;
      case "list":
        c = new ListCommand(input);
        break;
      case "mark":
        c = new MarkCommand(input);
        break;
      case "unmark":
        c = new UnmarkCommand(input);
        break;
      case "delete":
        c = new DeleteCommand(input);
        break;
      case "todo":
        c = new AddTodoCommand(input);
        break;
      case "deadline":
        c = new AddDeadlineCommand(input);
        break;
      case "event":
        c = new AddEventCommand(input);
        break;
      case "find":
        c = new FindCommand(input);
        break;
      default:
        throw new HoroException("Invalid Command");
    }

    return c;
  }
}
