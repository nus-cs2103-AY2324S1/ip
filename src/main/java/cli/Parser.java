package cli;

import java.util.HashMap;

import action.Action;
import action.ByeAction;
import action.CreateDeadlineAction;
import action.CreateEventAction;
import action.CreateTodoAction;
import action.DeleteAction;
import action.ListAction;
import action.MarkAction;
import action.UnmarkAction;
import error.ParseException;

public final class Parser {
    public static Action parseAction(String line) {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("Empty line passed to Command.parse");
        }

        String[] componentStrings = line.split("\\\\");

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
                return new ListAction();
            }
            case "todo": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "The description of a todo cannot be empty.");
                }
                return new CreateTodoAction(argument);
            }
            case "deadline": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "The description of a deadline cannot be empty.");
                }
                String by = getFlagValue(line, flags, "by");
                return new CreateDeadlineAction(argument, by);
            }
            case "event": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "The description of an event cannot be empty.");
                }
                String from = getFlagValue(line, flags, "from");
                String to = getFlagValue(line, flags, "to");
                return new CreateEventAction(argument, from, to);
            }
            case "mark": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "A task identifier must be provided.");
                }
                int taskId = parseIntArgument(argument);
                return new MarkAction(taskId);
            }
            case "unmark": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "A task identifier must be provided.");
                }
                int taskId = parseIntArgument(argument);
                return new UnmarkAction(taskId);
            }
            case "delete": {
                if (argument.isEmpty()) {
                    throw new ParseException(line, "A task identifier must be provided.");
                }
                int taskId = parseIntArgument(argument);
                return new DeleteAction(taskId);
            }
            case "bye": {
                return new ByeAction();
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
            throw new ParseException(argument, "Expected an integer but received a string");
        }
    }

    private static String getFlagValue(String line, HashMap<String, String> flags, String name) {
        String value = flags.get(name);
        if (value == null) {
            throw new ParseException(line, "Missing an option: \\" + name);
        }
        return value;
    }
}
