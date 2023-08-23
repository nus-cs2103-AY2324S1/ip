import java.util.HashMap;
import java.util.Map;

public abstract class Command {

	static final String LIST_COMMAND = "list";
	static final String MARK_COMMAND = "mark";
	static final String UNMARK_COMMAND = "unmark";
	static final String TODO_COMMAND = "todo";
	static final String DEADLINE_COMMAND = "deadline";
	static final String EVENT_COMMAND = "event";
	static final String DELETE_COMMAND = "delete";

	// COMMAND NAME /ARUGMENT_NAME ARGUMENT
	// command, arguement name cannot have spaces
	// name and arugment can have spaces
	static Command parse(String line) {
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
				if (argument.length == 2)
					value = argument[1].trim();
				arguments.put(argument[0].trim(), value);
			}
		}

		CommandBuilder cb = new CommandBuilder(name, arguments);

		switch (command) {
			case LIST_COMMAND:
				return cb.list();
			case MARK_COMMAND:
				return cb.mark();
			case UNMARK_COMMAND:
				return cb.unmark();
			case TODO_COMMAND:
				return cb.todo();
			case DEADLINE_COMMAND:
				return cb.deadline();
			case EVENT_COMMAND:
				return cb.event();
			case "delete":
				return cb.delete();
			default:
				throw new DukeException(DukeException.UNIDENTIFIED_COMMAND);
		}
	}

	public abstract void execute(Printer out);
}

class CommandBuilder {
	String name;
	Map<String, String> arguments;

	CommandBuilder(String name, Map<String, String> arguments) {
		this.name = name;
		this.arguments = arguments;
	}

	ListCommand list() {
		return new ListCommand();
	}

	MarkCommand mark() {
		return new MarkCommand(name);
	}

	UnmarkCommand unmark() {
		return new UnmarkCommand(name);
	}

	AddTaskCommand todo() {
		return AddTaskCommand.AddTodoCommand(name);
	}

	AddTaskCommand deadline() {
		return AddTaskCommand.AddDeadlineCommand(name, arguments.getOrDefault("by", ""));
	}

	AddTaskCommand event() {
		return AddTaskCommand.AddEventCommand(
				name, arguments.getOrDefault("from", ""), arguments.getOrDefault("to", ""));
	}

	DeleteCommand delete() {
		return new DeleteCommand(name);
	}
}
