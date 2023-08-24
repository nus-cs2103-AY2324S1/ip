package penguin;

public class Parser {

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
        } else {
            return "unknown";
        }
    }
}
