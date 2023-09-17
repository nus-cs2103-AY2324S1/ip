package duke;

public class Parser {
    /**
     * Parses a user command and creates a task object.
     *
     * @param userCommand  The user command.
     * @return  A Task object.
     */
    public static Task parse (String userCommand) {
        Task task = new Task(userCommand);
        String[] commandParts = userCommand.split(" ", 2);
        String taskType = commandParts[0].toLowerCase();

        switch (taskType) {
        case "todo":
            task = parseTodoCommand(commandParts);
            break;
        case "deadline":
            task = parseDeadlineCommand(commandParts);
            break;
        case "event":
            task = parseEventCommand(commandParts);
            break;
        default:
            System.out.println("Sorry, I don't understand the command.");
            break;
        }
        return task;
    }

    private static Todo parseTodoCommand(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("What you want to do?");
            return null;
        }

        return new Todo(commandParts[1]);
    }

    private static Deadline parseDeadlineCommand(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("Please describe your deadline:)");
            return null;
        }
        String[] deadlineParts = commandParts[1].split(" /by ");
        if (deadlineParts.length < 2) {
            System.out.println("Tell me what and when your deadline is!");
            return null;
        }

        return new Deadline(deadlineParts[0], deadlineParts[1]);
    }

    private static Event parseEventCommand(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("Please describe your event.");
            return null;
        }
        String[] eventParts = commandParts[1].split(" /from | /to ");
        if (eventParts.length < 3) {
            System.out.println("Tell me what your event is and when it starts/ends!");
            return null;
        }

        return new Event(eventParts[0], eventParts[1], eventParts[2]);
    }
}
