package cyrus.parser;

import cyrus.commands.*;
import cyrus.tasks.TaskList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
  public Command dispatchCommand(TaskList taskList, ParseInfo parseInfo) {
    switch (parseInfo.commandType) {
      case ADD_TODO:
        return new AddTodoCommand(taskList, parseInfo);
      case ADD_EVENT:
        return new AddEventCommand(taskList, parseInfo);
      case ADD_DEADLINE:
        return new AddDeadlineCommand(taskList, parseInfo);
      case DELETE_TASK:
        return new DeleteTaskCommand(taskList, parseInfo);
      case LIST_TASKS:
        return new ListTasksCommand(taskList, parseInfo);
      case MARK_TASK:
        return new MarkTaskCommand(taskList, parseInfo);
      case UNMARK_TASK:
        return new UnmarkTaskCommand(taskList, parseInfo);
      case UNKNOWN:
        return new UnknownCommand(taskList, parseInfo);
    }
    return new UnknownCommand(taskList, parseInfo);
  }

  /**
   * Perform simple parsing on the user input where we read tokens in while no /[word] is
   * encountered.
   *
   * @param line Line to parse
   */
  public ParseInfo parse(String line) {
    String input = line.trim();
    if (line.equals("")) return ParseInfo.EMPTY;

    String[] parts = input.split("\\s+");
    List<String> argumentParts = new ArrayList<>();
    // Parse the argument first
    int i = 1;
    while (i < parts.length) {
      if (parts[i].startsWith("/")) break;
      argumentParts.add(parts[i++]);
    }

    String argument = String.join(" ", argumentParts);

    HashMap<String, String> options = new HashMap<>();
    while (i < parts.length) {
      String key = "";
      StringBuilder acc = new StringBuilder();
      if (parts[i].startsWith("/")) {
        key = parts[i].substring(1);
      }

      while (++i < parts.length && !parts[i].startsWith("/")) {
        acc.append(parts[i]);
      }

      options.put(key, acc.toString());
    }

    return new ParseInfo(CommandType.fromString(parts[0]), argument, options);
  }
}
