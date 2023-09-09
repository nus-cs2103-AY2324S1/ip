package duke;

public class Parser {
    /**
     * Parses a user command and creates a task object.
     *
     * @param userCommand  The user command.
     * @return  A Task object.
     */
    public static Task parse(String userCommand) {
        String[] commandParts = userCommand.split(" ", 2);
        String taskType = commandParts[0].toLowerCase();

        switch (taskType) {
            case "todo":
                if (commandParts.length == 2) {
                    return new Todo(commandParts[1]);
                }
                break;
            case "deadline":
                if (commandParts.length == 2) {
                    String[] deadlineParts = commandParts[1].split(" /by ");
                    if (deadlineParts.length == 2) {
                        return new Deadline(deadlineParts[0], deadlineParts[1]);
                    } else {
                        System.out.println("Please describe your deadline:)");
                        return null;
                    }
                }
                break;
            case "event":
                if (commandParts.length == 2) {
                    String[] eventParts = commandParts[1].split(" /from | /to ");
                    if (eventParts.length == 3) {
                        return new Event(eventParts[0], eventParts[1], eventParts[2]);
                    } else {
                        System.out.println("Please describe your event.");
                        return null;
                    }
                }
                break;
            default:
                System.out.println("Sorry, I don't understand the command.");
                break;
        }
        return null;
    }
}
