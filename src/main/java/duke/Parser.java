package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import command.AddTaskCommand;
import command.ClearCommand;
import command.Executable;
import command.DeleteCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ShutdownCommand;
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
    private void init() {
        stringToCommand.put("bye", new ShutdownCommand());
        stringToCommand.put("help", new HelpCommand());
        stringToCommand.put("list", new ListCommand());
        stringToCommand.put("todo", new AddTaskCommand());
        stringToCommand.put("deadline", new AddTaskCommand());
        stringToCommand.put("event", new AddTaskCommand());
        stringToCommand.put("delete", new DeleteCommand());
        stringToCommand.put("mark", new MarkCommand(true));
        stringToCommand.put("unmark", new MarkCommand(false));
        stringToCommand.put("find", new FindCommand());
        stringToCommand.put("clear", new ClearCommand());
    }

    /**
     * Parses the string passed to it and produces the corresponding command.
     * @param input the string passed to the parser that is meant to be interpreted.
     * @return the command that the string represents.
     * @throws InvalidCommandException if the command cannot be identified.
     * @throws InvalidVarException if the command is identifiable but the parameters are incorrect.
     * TODO Split up this method.
     */
    public Executable parse(String input) throws InvalidCommandException, InvalidVarException {
        String commandIdentifier = input.split(" ")[0];
        Executable command = stringToCommand.get(commandIdentifier);
        if (command instanceof ShutdownCommand
            || command instanceof HelpCommand
            || command instanceof ListCommand
            || command instanceof ClearCommand) {
            if (!input.equals(commandIdentifier)) {
                throw new InvalidVarException("This command has no variables!");
            }
        }
        if (command instanceof AddTaskCommand) {
            if (input.equals(commandIdentifier)) {
                throw new InvalidCommandException("No parameters");
            }
            Task taskToAdd;
            String name;
            switch(commandIdentifier) {
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
                throw new InvalidVarException("Blank parameters! This should never happen; "
                        + "likely a task type was added without its respective parser.");
            } ((AddTaskCommand) command).setTask(taskToAdd);
        } else if (command instanceof DeleteCommand) {
            if (commandIdentifier.equals(input)) {
                throw new InvalidCommandException("No parameter");
            }
            int number;
            try {
                number = Integer.parseInt(input.substring(7)) - 1;
            } catch (Exception e) {
                throw new InvalidVarException("Task number could not be read");
            } (
                (DeleteCommand) command).setDelete(number);
        } else if (command instanceof MarkCommand) {
            if (commandIdentifier.equals(input)) {
                throw new InvalidCommandException("No parameter");
            }
            int number;
            try {
                number = Integer.parseInt(input.substring(commandIdentifier.length() + 1)) - 1;
            } catch (Exception e) {
                throw new InvalidVarException("Task number could not be read");
            } (
                (MarkCommand) command).setMark(number);
        }
        else if (command instanceof FindCommand) {
            if (input.length() < 6) {
                throw new InvalidVarException("No keyword!");
            }
            String keyword = input.substring(5);
            if (keyword.isBlank()) {
                throw new InvalidVarException("Blank keyword!");
            }
            ((FindCommand) command).setSearch(keyword);
        } else if (command == null) {
            throw new InvalidCommandException("Unrecognized command");
        }
        return command;
    }
}
