package penguin;

/**
 * Parser is responsible for parsing input commands from the user.
 */
public class Parser {
    /**
     * Parses the input command.
     *
     * @param command input command.
     * @return Parsed type of command.
     */
    public String parse(String command) {
        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")) {
            return "list";
        } else if (command.startsWith("mark")) {
            return "mark";
        } else if (command.startsWith("unmark")) {
            return "unmark";
        } else if (command.startsWith("todo")) {
            return "todo";
        } else if (command.startsWith("deadline")) {
            return "deadline";
        } else if (command.startsWith("event")) {
            return "event";
        } else if (command.startsWith("delete")) {
            return "delete";
        } else if (command.startsWith("find")) {
            return "find"; 
        } else if (command.startsWith("schedule")) {
            return "schedule";
        }else {
            return "unknown";
        }
    }
}
