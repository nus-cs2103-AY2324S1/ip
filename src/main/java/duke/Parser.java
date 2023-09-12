package duke;

import duke.command.*;

import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    public static Command parse(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        ui.clearStringBuilder();

        if (text.startsWith("list")) {
            return new ListCommand();

        } else if (text.startsWith("unmark")) {
            return new UnmarkCommand();

        } else if (text.startsWith("mark")) {
            return new MarkCommand();

        } else if (text.equals("bye")) {
            return new ByeCommand();

        } else if (text.startsWith("todo")) {
            return new TodoCommand();

        } else if (text.startsWith("deadline")) {
            return new DeadlineCommand();

        } else if (text.startsWith("event")) {
            return new EventCommand();

        } else if (text.startsWith("delete")) {
            return new DeleteCommand();

        } else if (text.startsWith("find")) {
            return new FindCommand();

        } else {
            throw new DukeException("I apologise, sir. But I do not understand what you mean.");
        }
    }
}



