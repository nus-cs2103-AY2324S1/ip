package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

public class Parser {
    public static Command parse(String userInput) throws DukeException {

        String command = userInput.split(" ", 2)[0].toUpperCase();
        switch (command) {
            case "BYE":
                return new ExitCommand();
            case "LIST":
                return new ListCommand();
            case "MARK":
                return new MarkCommand(getTaskNumber(userInput));
            case "UNMARK":
                return new UnmarkCommand(getTaskNumber(userInput));
            case "TODO":
                return new AddCommand(parseTodo(userInput));
            case "DEADLINE":
                return new AddCommand(parseDeadline(userInput));
            case "EVENT":
                return new AddCommand(parseEvent(userInput));
            case "DELETE":
                return new DeleteCommand(getTaskNumber(userInput));
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static ToDoTask parseTodo(String userInput) throws DukeException {
        String description = userInput.split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDoTask(description);
    }

    public static DeadlineTask parseDeadline(String userInput) throws DukeException {
        if (userInput.split("/by").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of deadline cannot be empty.");
        }
        String by = userInput.split("/by")[1].trim();

        String description = userInput.split("/by")[0].trim().split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        return new DeadlineTask(description, by);
    }

    public static EventTask parseEvent(String userInput) throws DukeException {
        if (userInput.split("/from").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of event cannot be empty.");
        }

        String date = userInput.split("/from")[1];

        String from = date.split("/to")[0].trim();
        if (from.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The date of 'from' cannot be empty.");
        }

        if (userInput.split("/to").length < 2) {
            throw new DukeException("☹ OOPS!!! The date of 'to' cannot be empty.");
        }
        String to = date.split("/to")[1].trim();

        String description = userInput.split("/from")[0].trim().split(" ", 2)[1];
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }

        return new EventTask(description, from, to);
    }

    private static int getTaskNumber(String userInput) throws DukeException {
        if (userInput.split(" ").length < 2) {
            throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
        }
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }
}
