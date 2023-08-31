package duke.parser;

import duke.command.Command;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.WrongCommand;
import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
	public static Command parse(String input) throws DukeException {
		input = input.trim();
		String commandWord = input.split(" ", 2)[0];
		switch (commandWord) {
			case AddTodoCommand.COMMAND_WORD:
				String[] todoData = input.split(" ", 2);
				if (todoData.length < 2) {
					throw new DukeException("The description of a todo cannot be empty.");
				}
				return new AddTodoCommand(todoData[1]);
			case AddDeadlineCommand.COMMAND_WORD:
				String[] deadlineData = input.split(" /by ", 2);

				if (deadlineData.length < 2) {
					throw new DukeException("A deadline task requires a /by (timedate) descriptor");
				}
				if (deadlineData[0].split(" ", 2).length < 2) {
					throw new DukeException("The description of a deadline cannot be empty.");
				}
				try {
					String by = LocalDate.parse(deadlineData[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
					return new AddDeadlineCommand(deadlineData[0].split(" ", 2)[1], by);
				} catch (DateTimeException e) {
					throw new DukeException("Please represent time in a proper time format of yyyy-mm-dd");
				}
			case AddEventCommand.COMMAND_WORD:
				String[] eventData = input.split(" /from ", 2);
				if (eventData.length < 2) {
					throw new DukeException("An event requires a /from (timedate) descriptor");
				}
				String[] period = eventData[1].split( "/to ");
				if (period.length < 2) {
					throw new DukeException("An event requires a /to (timedate) descriptor");
				}
				if (eventData[0].split(" ", 2).length < 2) {
					throw new DukeException("The description of an event cannot be empty.");
				}
				try {
					String from = LocalDate.parse(period[0].trim())
							.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
					String to = LocalDate.parse(period[1].trim())
							.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

					return new AddEventCommand(eventData[0].split(" ", 2)[1], from, to);
				} catch (DateTimeException e) {
					throw new DukeException("Please represent time in a proper time format of yyyy-mm-dd");
				}
			case MarkCommand.COMMAND_WORD:
				String[] markData = input.split(" ", 2);
				if (markData.length < 2) {
					throw new DukeException("Please select a task using its index");
				}
				try {
					return new MarkCommand(Integer.valueOf(markData[1]));
				} catch (NumberFormatException e) {
					throw new DukeException("Please specify the index of the task (Numbers only)");
				}
			case UnmarkCommand.COMMAND_WORD:
				String[] unmarkData = input.split(" ", 2);
				if (unmarkData.length < 2) {
					throw new DukeException("Please select a task using its index");
				}
				try {
					return new UnmarkCommand(Integer.valueOf(unmarkData[1]));
				} catch (NumberFormatException e) {
					throw new DukeException("Please specify the index of the task (Numbers only)");
				}
			case DeleteCommand.COMMAND_WORD:
				String[] deleteData = input.split(" ", 2);
				if (deleteData.length < 2) {
					throw new DukeException("Please select a task using its index");
				}
				try {
					return new DeleteCommand(Integer.valueOf(deleteData[1]));
				} catch (NumberFormatException e) {
					throw new DukeException("Please specify the index of the task (Numbers only)");
				}
			case ListCommand.COMMAND_WORD:
				return new ListCommand();
			case ExitCommand.COMMAND_WORD:
				return new ExitCommand();
			default:
				return new WrongCommand();
		}
	}

}
