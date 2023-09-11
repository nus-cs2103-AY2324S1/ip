package juke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The parser is in charge of understanding user input
 * and calling the appropriate Juke method to handle that
 * input.
 *
 * @author lshaoqin
 */
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
    String parse(String input) throws Exception {
        if (input.equalsIgnoreCase("bye")) {
            return juke.closeBot();
        }
        else if (input.equalsIgnoreCase("list")) {
            return juke.printList();
        }
        else if (input.contains("unmark ")) {
            int index = Integer.parseInt(input.substring(7));
            return juke.unmark(index);
        }
        else if (input.contains("mark ")) {
            int index = Integer.parseInt(input.substring(5));
            return juke.mark(index);
        }
        else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.substring(7));
            return juke.delete(index);
        }
        else if (input.startsWith("find ")) {
            if (input.length() < 6) {
                throw new JukeError("Please specify a search term.");
            }
            String searchTerm = input.substring(5);
            return juke.find(searchTerm);
        }
        else if (input.startsWith("todo")) {
            if (input.length() < 5 || input.substring(5).length() == 0) {
                throw new JukeError("The description of a todo cannot be empty.");
            }
            return juke.createTodo(input.substring(5));
        }
        else if (input.startsWith("deadline")) {
            // Match string with pattern deadline (taskname) /by (date)
            final Pattern deadlinePattern = Pattern.compile(
                    "^deadline\\s+(.*)\\s+/by\\s+(.*)$");
            Matcher matcher = deadlinePattern.matcher(input);
            if (matcher.matches()) {
                try {
                    return juke.createDeadline(matcher.group(1), LocalDate.parse(matcher.group(2)));
                } catch (DateTimeParseException e) {
                    throw new JukeError("Failed to parse date.");
                }
            }
        }
        else if (input.startsWith("event")) {
            // Match string with pattern event (taskName) /from (date) /to (date)
            final Pattern eventPattern = Pattern.compile(
                    "^event\\s+(.*)\\s+/from\\s+(.*)\\s+/to\\s+(.*)$");
            Matcher matcher = eventPattern.matcher(input);
            if (matcher.matches()) {
                try {
                    return juke.createEvent(matcher.group(1),
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
        return "Juke may have encountered a problem - please try that again!";
    }
}
