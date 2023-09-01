package parsers;

public class Parser {
    public String parseCommand(String userInput) {
        if (userInput.equals("bye")) {
            return "bye";
        } else if (userInput.equals("list")) {
            return "list";
        } else if (userInput.startsWith("mark")) {
            return "mark";
        } else if (userInput.startsWith("deadline")) {
            return "deadline";
        } else if (userInput.startsWith("event")) {
            return "event";
        } else if (userInput.startsWith("todo")) {
            return "todo";
        } else if (userInput.startsWith("delete")) {
            return "delete";
        } else {
            return "";
        }
    }
}
