package command;

import java.util.HashMap;

public abstract class Command {
    public static Command parse(String line) {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("Cannot parse command from empty string");
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
                return new ListCommand();
            }
            case "todo": {
                return new TodoCommand(argument);
            }
            case "deadline": {
                String by = getFlagValue(flags, "by");
                return new DeadlineCommand(argument, by);
            }
            case "event": {
                String from = getFlagValue(flags, "from");
                String to = getFlagValue(flags, "to");
                return new EventCommand(argument, from, to);
            }
            case "mark": {
                int taskId = parseIntArgument(argument);
                return new MarkCommand(taskId);
            }
            case "unmark": {
                int taskId = parseIntArgument(argument);
                return new UnmarkCommand(taskId);
            }
            case "bye": {
                return new ByeCommand();
            }
            default:
                throw new ParseException("Unrecognised command");
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
            throw new ParseException("Cannot parse component from empty string");
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
            throw new ParseException("Invalid argument format, expected integer");
        }
    }

    private static String getFlagValue(HashMap<String, String> flags, String name) {
        String value = flags.get(name);
        if (value == null) {
            throw new ParseException("Missing flag: " + name);
        }
        return value;
    }
}