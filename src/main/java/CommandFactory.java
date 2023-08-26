import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {

  private final Printer out;
  private final TaskList taskList;

  public CommandFactory(Printer out, TaskList taskList) {
    this.out = out;
    this.taskList = taskList;
  }

  // COMMAND NAME /ARUGMENT_NAME ARGUMENT
  // command, arguement name cannot have spaces
  // name and arugment can have spaces
  public Command parse(String line) {
    String[] input = line.split(" ", 2);
    String command = input[0];
    String name = "";
    Map<String, String> arguments = new HashMap<>();

    if (input.length == 2) {
      String[] arr = input[1].split("/");
      name = arr[0].trim();
      for (int i = 1; i < arr.length; ++i) {
        String[] argument = arr[i].split(" ", 2);
        String value = "";
        if (argument.length == 2) value = argument[1].trim();
        arguments.put(argument[0].trim(), value);
      }
    }

    CommandBuilder cb = new CommandBuilder(name, arguments, out, taskList);

    switch (command) {
      case Command.LIST:
        return cb.list();
      case Command.MARK:
        return cb.mark();
      case Command.UNMARK:
        return cb.unmark();
      case Command.TODO:
        return cb.todo();
      case Command.DEADLINE:
        return cb.deadline();
      case Command.EVENT:
        return cb.event();
      case Command.DELETE:
        return cb.delete();
      default:
        throw new DukeException(DukeException.UNIDENTIFIED_COMMAND);
    }
  }
}

class CommandBuilder {
  private String name;
  private Map<String, String> arguments;
  private Printer out;
  private TaskList taskList;

  CommandBuilder(String name, Map<String, String> arguments, Printer out, TaskList taskList) {
    this.name = name;
    this.arguments = arguments;
    this.out = out;
    this.taskList = taskList;
  }

  ListCommand list() {
    return new ListCommand(out, taskList);
  }

  MarkCommand mark() {
    return new MarkCommand(out, taskList, name);
  }

  UnmarkCommand unmark() {
    return new UnmarkCommand(out, taskList, name);
  }

  AddTaskCommand todo() {
    return AddTaskCommand.AddTodoCommand(out, taskList, name);
  }

  AddTaskCommand deadline() {
    return AddTaskCommand.AddDeadlineCommand(out, taskList, name, arguments.getOrDefault("by", ""));
  }

  AddTaskCommand event() {
    return AddTaskCommand.AddEventCommand(
        out, taskList, name, arguments.getOrDefault("from", ""), arguments.getOrDefault("to", ""));
  }

  DeleteCommand delete() {
    return new DeleteCommand(out, taskList, name);
  }
}
