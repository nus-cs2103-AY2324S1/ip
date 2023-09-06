package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class represents a parser that takes inputs and produces an output.
 */
public class Parser {

    /**
     * Processes and responds to the inputs received.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static void parseInput(String input, TaskList tasks, Storage storage) {
        try {
            if (input.equals("list")) {
                ListCommand.execute(input, tasks, storage);
            } else if (input.startsWith("todo ") || (input.startsWith("todo") && input.length() == 4)) {
                ToDoCommand.execute(input, tasks, storage);
            } else if (input.startsWith("deadline ") || (input.startsWith("deadline") && input.length() == 8)) {
                DeadlineCommand.execute(input, tasks, storage);
            } else if (input.startsWith("event ") || (input.startsWith("event") && input.length() == 5)) {
                EventCommand.execute(input, tasks, storage);
            } else if (input.startsWith("mark ") && input.length() > 5 && input.substring(5).matches("\\d+")) {
                MarkCommand.execute(input, tasks, storage);
            } else if (input.startsWith("unmark ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                UnmarkCommand.execute(input, tasks, storage);
            } else if (input.startsWith("delete ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                DeleteCommand.execute(input, tasks, storage);
            } else if (input.startsWith("find ") || (input.startsWith("find") && input.length() == 4)) {
                FindCommand.execute(input, tasks, storage);
            } else {
                throw new InvalidTextException();
            }
        } catch (InvalidTextException e) {
            Ui.invalidText();
        }
    }
}
