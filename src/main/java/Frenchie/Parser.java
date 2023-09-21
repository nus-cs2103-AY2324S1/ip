package Frenchie;

public class Parser {
    public static Command parseCommand(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];

        switch (command) {
            case "list":
                return Command.list;
            case "mark":
                return Command.mark;
            case "unmark":
                return Command.unmark;
            case "todo":
                return Command.todo;
            case "deadline":
                return Command.deadline;
            case "event":
                return Command.event;
            case "bye":
                return Command.bye;
            case "delete":
                return Command.delete;
            default:
                return Command.invalid;

        }
    }

    public static String parseDetails(String input) {
        String[] parts = input.split(" ", 2);
        return (parts.length > 1) ? parts[1] : "";
    }
}
