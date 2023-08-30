import java.time.LocalDateTime;

public class Parser {
    public Task getTask(String userInput) throws DukeException {
        Task add;

        if (userInput.startsWith("todo")) {
            add = parseTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            add = parseDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            add = parseEvent(userInput);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return add;
    }

    public Todo parseTodo(String userInput) {
        String description = userInput.substring(userInput.indexOf(' ') + 1);

        if (description.isEmpty() || description.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
        }

        return new Todo(description);
    }

    public Deadline parseDeadline(String userInput) {
        try {
            String description = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            return new Deadline(description, LocalDateTime.parse(by, Duke.TIME_FORMAT));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Missing parameters in deadline");
        }
    }

    public Event parseEvent(String userInput) {
        try {
            String description = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
            String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
            String to = userInput.substring(userInput.indexOf("/to") + 4);
            return new Event(description, LocalDateTime.parse(from, Duke.TIME_FORMAT),
                    LocalDateTime.parse(to, Duke.TIME_FORMAT));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Missing parameters in event");
        }
    }
}
