package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import command.AddTaskExecutable;
import command.ClearExecutable;
import command.DeleteExecutable;
import command.Executable;
import command.FindExecutable;
import command.HelpExecutable;
import command.ListExecutable;
import command.MarkExecutable;
import command.ShutdownExecutable;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;


/**
 * Parses inputs passed via the user interface into commands if possible.
 */

public class Parser {
    private final HashMap<String, Executable> stringToCommand;

    /**
     * Initializes the parser.
     */
    public Parser() {
        stringToCommand = new HashMap<>();
        init();
    }

    /**
     * Initializes the hashmap.
     */
    private void init() {
        stringToCommand.put("bye", new ShutdownExecutable());
        stringToCommand.put("help", new HelpExecutable());
        stringToCommand.put("list", new ListExecutable());
        stringToCommand.put("todo", new AddTaskExecutable());
        stringToCommand.put("deadline", new AddTaskExecutable());
        stringToCommand.put("event", new AddTaskExecutable());
        stringToCommand.put("delete", new DeleteExecutable());
        stringToCommand.put("mark", new MarkExecutable(true));
        stringToCommand.put("unmark", new MarkExecutable(false));
        stringToCommand.put("find", new FindExecutable());
        stringToCommand.put("clear", new ClearExecutable());
    }

    /**
     * Parses the string passed to it and produces the corresponding command.
     * @param input the string passed to the parser that is meant to be interpreted.
     * @return the command that the string represents.
     * @throws InvalidCommandException if the command cannot be identified.
     * @throws InvalidVarException if the command is identifiable but the parameters are incorrect.
     */
    public Executable parseToExecutable(String input) throws InvalidCommandException, InvalidVarException {
        // TODO Split up this method.
        String execIdentifier = input.split(" ")[0];
        Executable executable = stringToCommand.get(execIdentifier);
        if (executable instanceof ShutdownExecutable
            || executable instanceof HelpExecutable
            || executable instanceof ListExecutable
            || executable instanceof ClearExecutable) {
            if (!input.equals(execIdentifier)) {
                throw new InvalidVarException("This command has no variables!");
            }
        }
        if (executable instanceof AddTaskExecutable) {
            if (input.equals(execIdentifier)) {
                throw new InvalidCommandException("No parameters");
            }
            Task taskToAdd = null;
            String name;
            switch(execIdentifier) {
            case ("todo"):
                if (input.length() < 6) {
                    throw new InvalidVarException("No name!");
                }
                name = input.substring(5);
                if (name.isBlank()) {
                    throw new InvalidVarException("Blank name!");
                }
                taskToAdd = new ToDo(name);
                break;
            case ("deadline"):
                int split = input.indexOf("/by");
                if (split == -1) {
                    throw new InvalidCommandException("Deadline missing");
                }
                if (split < 10 || input.length() < split + 4) {
                    throw new InvalidVarException("Blank parameters!");
                }
                name = input.substring(9, split - 1);
                LocalDate deadline;
                try {
                    deadline = LocalDate.parse(input.substring(split + 4));
                } catch (DateTimeParseException e) {
                    throw new InvalidVarException("Could not parse dates!");
                }
                if (name.isBlank()) {
                    throw new InvalidVarException("Blank parameters!");
                }
                taskToAdd = new Deadline(name, deadline);
                break;
            case ("event"):
                int split1 = input.indexOf("/from");
                int split2 = input.indexOf("/to");
                if (split1 == -1 || split2 == -1) {
                    throw new InvalidCommandException("Some parameters missing");
                }
                if (split1 < 7 || split2 < split1 + 5 || input.length() < split2 + 4) {
                    throw new InvalidVarException("Blank parameters!");
                }
                name = input.substring(6, split1 - 1);
                LocalDate start;
                LocalDate end;
                try {
                    start = LocalDate.parse(input.substring(split1 + 6, split2 - 1));
                    end = LocalDate.parse(input.substring(split2 + 4));
                } catch (DateTimeParseException e) {
                    throw new InvalidVarException("Could not parse dates!");
                }
                if (name.isBlank()) {
                    throw new InvalidVarException("Blank parameters!");
                }
                taskToAdd = new Event(name, start, end);
                break;
            default:
                assert false;
                // This means a task type was added without a proper handler.
            }
            ((AddTaskExecutable) executable).setTask(taskToAdd);
        } else if (executable instanceof DeleteExecutable) {
            if (execIdentifier.equals(input)) {
                throw new InvalidCommandException("No parameter");
            }
            int number;
            try {
                number = Integer.parseInt(input.substring(7)) - 1;
            } catch (Exception e) {
                throw new InvalidVarException("Task number could not be read");
            } ((DeleteExecutable) executable).setDelete(number);
        } else if (executable instanceof MarkExecutable) {
            if (execIdentifier.equals(input)) {
                throw new InvalidCommandException("No parameter");
            }
            int number;
            try {
                number = Integer.parseInt(input.substring(execIdentifier.length() + 1)) - 1;
            } catch (Exception e) {
                throw new InvalidVarException("Task number could not be read");
            } ((MarkExecutable) executable).setMarkTarget(number);
        } else if (executable instanceof FindExecutable) {
            if (input.length() < 6) {
                throw new InvalidVarException("No keyword!");
            }
            String keyword = input.substring(5);
            if (keyword.isBlank()) {
                throw new InvalidVarException("Blank keyword!");
            } (
                (FindExecutable) executable).setSearch(keyword);
        } else if (executable == null) {
            throw new InvalidCommandException("Unrecognized command");
        } else {
            assert false;
            // We should never reach this step; if so, means the parser is not handling a recognized
            // command type.
        }
        return executable;
    }
}
