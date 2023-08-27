import java.util.Map;

public class CommandFactory {

  private final Printer out;
  private final TaskList taskList;
  private final SaveFile saveFile;

  public CommandFactory(Printer out, TaskList taskList, SaveFile saveFile) {
    this.out = out;
    this.taskList = taskList;
    this.saveFile = saveFile;
  }

  // COMMAND NAME /ARUGMENT_NAME ARGUMENT
  // command, arguement name cannot have spaces
  // name and arugment can have spaces
  public Command parse(String line) throws DukeException {
    CommandStructure cs = CommandStructure.parse(line);

    CommandBuilder cb =
        new CommandBuilder(cs.command, cs.name, cs.arguments, out, taskList, saveFile);

    switch (cs.command) {
    case Command.LIST:
      return cb.list();
    case Command.MARK:
      return cb.mark();
    case Command.UNMARK:
      return cb.unmark();
		case Command.TODO:
		case Command.DEADLINE:
		case Command.EVENT:
			return cb.task();
		case Command.DELETE:
			return cb.delete();
		default:
			throw new DukeException(DukeException.UNIDENTIFIED_COMMAND);
    }
  }
}

class CommandBuilder {
  private String command;
  private String name;
  private Map<String, String> arguments;
  private Printer out;
  private TaskList taskList;
  private SaveFile saveFile;

  CommandBuilder(
      String command,
      String name,
      Map<String, String> arguments,
      Printer out,
      TaskList taskList,
      SaveFile saveFile) {
    this.command = command;
    this.name = name;
    this.arguments = arguments;
    this.out = out;
    this.taskList = taskList;
    this.saveFile = saveFile;
  }

  ListCommand list() {
    return new ListCommand(out, taskList);
  }

  MarkCommand mark() {
    return new MarkCommand(out, taskList, name, saveFile);
  }

  UnmarkCommand unmark() {
    return new UnmarkCommand(out, taskList, name, saveFile);
  }

  AddTaskCommand task() {
    return new AddTaskCommand(out, taskList, Task.createTask(command, name, arguments), saveFile);
  }

  DeleteCommand delete() {
    return new DeleteCommand(out, taskList, name, saveFile);
  }
}
