package ekud.ui.cli;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import ekud.command.ByeCommand;
import ekud.command.Command;
import ekud.command.CreateDeadlineCommand;
import ekud.command.CreateEventCommand;
import ekud.command.CreateTodoCommand;
import ekud.command.DeleteCommand;
import ekud.command.FindCommand;
import ekud.command.ListCommand;
import ekud.command.MarkCommand;
import ekud.command.UnmarkCommand;
import ekud.error.ParseException;
import ekud.util.DateTime;

public final class Parser {
    public static Command parseCommand(String line) {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("Empty line passed to Command.parse");
        }

        String[] componentStrings = line.split("\\/");
        Component[] components = new Component[componentStrings.length];

        components[0] = parseComponent(componentStrings[0]);
        String name = components[0].getName();
        String argument = components[0].getArgument();

        HashMap<String, String> flags = new HashMap<>();
        for (int index = 1; index < componentStrings.length; index++) {
            Component component = parseComponent(componentStrings[index]);
            flags.put(component.getName(), component.getArgument());
        }

        switch (name) {
            case "list": {
                return new ListCommand();
            }
            case "todo": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "The description of a todo cannot be empty.");
                }
                return new CreateTodoCommand(argument);
            }
            case "deadline": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "The description of a deadline cannot be empty.");
                }
                DateTime by = parseDateTimeArgument(getFlagValue(line, flags, "by"));
                return new CreateDeadlineCommand(argument, by);
            }
            case "event": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "The description of an event cannot be empty.");
                }
                DateTime from = parseDateTimeArgument(getFlagValue(line, flags, "from"));
                DateTime to = parseDateTimeArgument(getFlagValue(line, flags, "to"));
                return new CreateEventCommand(argument, from, to);
            }
            case "mark": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "A task identifier must be provided.");
                }
                int taskId = parseIntArgument(argument);
                return new MarkCommand(taskId);
            }
            case "unmark": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "A task identifier must be provided.");
                }
                int taskId = parseIntArgument(argument);
                return new UnmarkCommand(taskId);
            }
            case "delete": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "A task identifier must be provided.");
                }
                int taskId = parseIntArgument(argument);
                return new DeleteCommand(taskId);
            }
            case "find": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "A search query must be provided.");
                }
                return new FindCommand(argument);
            }
            case "bye": {
                return new ByeCommand();
            }
            default:
                throw new ParseException(line, "I'm sorry, but I don't know what that means :-(");
        }

    }

    private static class Component {
        private String name;
        private String argument;

        public Component(String name, String argument) {
            this.name = name;
            this.argument = argument;
        }

        public String getName() {
            return name;
        }

        public String getArgument() {
            return argument;
        }

    }

    private static Component parseComponent(String component) {
        if (component.isEmpty()) {
            throw new IllegalArgumentException("Empty string passed to Command.parseComponent");
        }

        String trimmedComponent = component.trim();
        String[] components = trimmedComponent.split("\\s+");

        int nameLength = components[0].length();
        String name = trimmedComponent.substring(0, nameLength).trim();
        String argument = trimmedComponent.substring(nameLength).trim();

        return new Component(name, argument);
    }

    private static int parseIntArgument(String argument) {
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException error) {
            throw new ParseException(argument, "Expected an integer.");
        }
    }

    private static DateTime parseDateTimeArgument(String argument) {
        try {
            return DateTime.parse(argument);
        } catch (DateTimeParseException error) {
            throw new ParseException(argument, "Expected a date, time or both.");
        }
    }

    private static String getFlagValue(String line, HashMap<String, String> flags, String name) {
        String value = flags.get(name);
        if (value == null) {
            throw new ParseException(line, "Missing an option: /" + name);
        }
        return value;
    }
}
