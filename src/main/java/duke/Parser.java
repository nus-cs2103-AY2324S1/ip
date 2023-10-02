package duke;

import duke.command.*;

import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Parser deals with taking in an input and returning a command object.
 */
public class Parser {

    /**
     * Takes in a string and then returns a command based on the input string.
     * @param text The input given by the user.
     * @param ui The user interface which is in charge of interactions with the user.
     * @param list The TaskList that holds all the tasks.
     * @param storage The storage that holds and notes down all past commands.
     * @return A command object corresponding to what was input.
     * @throws DukeException
     */
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
        } else if (text.startsWith("sort")) {
                return new SortCommand();
        } else {
            throw new DukeException("I apologise, sir. But I do not understand what you mean.");
        }
    }
}



