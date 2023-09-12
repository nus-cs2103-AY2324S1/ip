package horo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import horo.commands.Command;
import horo.commands.ExitCommand;
import horo.commands.expenses.AddExpenseCommand;
import horo.commands.expenses.ListExpenseCommand;
import horo.commands.tasks.AddDeadlineCommand;
import horo.commands.tasks.AddEventCommand;
import horo.commands.tasks.AddTodoCommand;
import horo.commands.tasks.DeleteCommand;
import horo.commands.tasks.FindCommand;
import horo.commands.tasks.ListTaskCommand;
import horo.commands.tasks.MarkCommand;
import horo.commands.tasks.UnmarkCommand;
import horo.data.expenses.Expense;
import horo.data.tasks.Deadline;
import horo.data.tasks.Event;
import horo.data.tasks.Task;
import horo.data.tasks.Todo;

/**
 * Abstract Parser class that has static methods to parse strings
 */
public abstract class Parser {

  /**
   * Pattern to check availiable commands
   */
  private static final Pattern commandPattern = Pattern
      .compile("^(deadline|todo|event|bye|mark|unmark|list task|delete|find|expense|list expense)");

  /**
   * Returns a task by parsing string from data file
   * 
   * @param s Input string from data file
   * @return Task
   * @throws HoroException
   */
  public static Task parseTaskDataString(String s) throws HoroException {
    assert s != "";

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

  /**
   * Returns a expense by parsing string from data file
   * 
   * @param s Input string from data file
   * @return Expense
   * @throws HoroException
   */
  public static Expense parseExpenseDataString(String s) throws HoroException {
    assert s != "";

    String[] arguments = s.split(",");
    Expense e;
    switch (arguments[0]) {
      case "E":
        e = new Expense(Integer.parseInt(arguments[1]), arguments[2]);
        break;
      default:
        throw new HoroException("Bad Command");
    }

    return e;
  }

  /**
   * Returns the Command associated with the string the user inputs
   * 
   * @param input Input string from user
   * @return Command
   * @throws HoroException
   */
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
      case "list task":
        c = new ListTaskCommand(input);
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
      case "expense":
        c = new AddExpenseCommand(input);
        break;
      case "list expense":
        c = new ListExpenseCommand(input);
        break;
      default:
        throw new HoroException("Invalid Command");
    }

    return c;
  }
}
