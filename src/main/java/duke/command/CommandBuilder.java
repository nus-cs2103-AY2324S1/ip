package duke.command;

import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class to make building Commands faster. Includes helpers like
 * parsing input
 */
public class CommandBuilder {
	private String command;
	private String name;
	private Map<String, String> arguments;

	private CommandBuilder(String command, String name, Map<String, String> arguments) {
		this.command = command;
		this.name = name;
		this.arguments = arguments;
	}

	/**
	 * Returns the command type
	 *
	 * @return command type
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Returns the name of the argument (second argument)
	 *
	 * @return command name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the arguments of the command. Arguments are key value pairs (/key
	 * value)
	 *
	 * @return map containing argument key value pairs
	 */
	public Map<String, String> getArguments() {
		return arguments;
	}

	/**
	 * Returns a command builder object.
	 *
	 * @param line The command
	 * @return A command builder containing information about the command
	 */
	public static CommandBuilder parse(String line) {
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
				if (argument.length == 2) {
					value = argument[1].trim();
				}
				arguments.put(argument[0].trim(), value);
			}
		}

		return new CommandBuilder(command, name, arguments);
	}

  public Command toCommand(Printer out, TaskList taskList, FileIO savefile) {
    switch (command) {
      case Command.LIST:
        return new ListCommand(out, taskList, savefile, arguments.getOrDefault("before", ""));
      case Command.MARK:
        return new MarkCommand(out, taskList, savefile, name);
      case Command.UNMARK:
        return new UnmarkCommand(out, taskList, savefile, name);
      case Command.TODO:
      case Command.DEADLINE:
      case Command.EVENT:
        return new AddTaskCommand(out, taskList, savefile, command, name, arguments);
      case Command.DELETE:
        return new DeleteCommand(out, taskList, savefile, name);
			case Command.FIND:
				return new FindCommand(out, taskList, savefile, name);
      default:
        return new UnidentifiedCommand(out, taskList, savefile);
    }
  }
}
