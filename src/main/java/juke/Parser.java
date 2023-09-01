package juke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    protected Juke juke;
    public Parser(Juke juke) {
        this.juke = juke;
    }

    /**
     * Parses input and calls the appropriate Juke function.
     *
     * @param input Input from the user.
     * @throws JukeError If there was a problem parsing the input.
     */
    void parse(String input) throws JukeError {
        if (input.equalsIgnoreCase("bye")) {
            juke.closeBot();
        }
        else if (input.equalsIgnoreCase("list")) {
            juke.printList();
        }
        else if (input.contains("unmark ")) {
            int index = Integer.parseInt(input.substring(7));
            juke.unmark(index);
        }
        else if (input.contains("mark ")) {
            int index = Integer.parseInt(input.substring(5));
            juke.mark(index);
        }
        else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.substring(7));
            juke.delete(index);
        }
        else if (input.startsWith("find ")) {
            if (input.length() < 6) {
                throw new JukeError("Please specify a search term.");
            }
            String searchTerm = input.substring(5);
            juke.find(searchTerm);
        }
        else if (input.startsWith("todo")) {
            if (input.length() < 5 || input.substring(5).length() == 0) {
                throw new JukeError("The description of a todo cannot be empty.");
            }
            juke.createTodo(input.substring(5));
        }
        else if (input.startsWith("deadline")) {
            final Pattern deadlinePattern = Pattern.compile(
                    "^deadline\\s+(.*)\\s+/by\\s+(.*)$");
            Matcher matcher = deadlinePattern.matcher(input);
            if (matcher.matches()) {
                try {
                    juke.createDeadline(matcher.group(1), LocalDate.parse(matcher.group(2)));
                } catch (DateTimeParseException e) {
                    throw new JukeError("Failed to parse date.");
                }
            }
        }
        else if (input.startsWith("event")) {
            final Pattern eventPattern = Pattern.compile(
                    "^event\\s+(.*)\\s+/from\\s+(.*)\\s+/to\\s+(.*)$");
            Matcher matcher = eventPattern.matcher(input);
            if (matcher.matches()) {
                try {
                    juke.createEvent(matcher.group(1),
                            LocalDate.parse(matcher.group(2)),
                            LocalDate.parse(matcher.group(3)));
                } catch (DateTimeParseException e) {
                    throw new JukeError("Failed to parse date.");
                }
            }
        }
        else {
            throw new JukeError("I'm sorry, but I don't know what that means :-(");
        }
    }
}
