package duke.parser;

import duke.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
	/**
	 * Parses the query and returns the corresponding command
	 *
	 * @param query the query
	 * @return the corresponding command
	 */
	public static Command parse(String query) {

		String[] split = query.split(" ", 2);
		Command command = null;

		String keyword = split[0];

		switch (keyword) {
			case "help":
				command = validateHelp(split);
				break;
			case "bye":
				command = validateExit(split);
				break;
			case "list":
				command = validateList(split);
				break;
			case "todo":
			case "deadline":
			case "event":
				command = validateTask(split);
				break;
			case "mark":
			case "unmark":
				command = validateMark(split);
				break;
			case "delete":
				command = validateDelete(split);
				break;
			default:
				command = new IncorrectCommand("☹ OOPS!!! Please try again!\n"
								+ "If you need help with commands, please type 'help'!");
		}
		return command;
	}

	/**
	 * Validates the mark command
	 *
	 * @param split the split query
	 * @return the corresponding command
	 */
	private static Command validateMark(String[] split) {
		if (split.length == 1) {
			return new IncorrectCommand("☹ OOPS!!! You are missing a number\n" +
							"Please enter a valid mark query - mark 1");
		}

		if (split.length > 2) {
			return new IncorrectCommand("☹ OOPS!!! You have entered too many numbers\n" +
							"Please enter a valid mark query - mark 1");
		}

		if (!isNumeric(split[1])) {
			return new IncorrectCommand("☹ OOPS!!! You have entered a non-numeric item!\n" +
							"Please enter a valid mark query - mark 1");
		}

		int index = Integer.parseInt(split[1]);
		// Check if index is greater than 0.
		if (index <= 0) {
			return new IncorrectCommand("☹ OOPS!!! You have entered a non-numeric item!\n" +
							"Please enter a number greater than 0 - mark 1");
		}
		return new MarkCommand(index, split[0]);
	}

	/**
	 * Validates the delete command
	 *
	 * @param split the split query
	 * @return the corresponding command
	 */
	public static Command validateDelete(String[] split) {

		if (split.length == 1) {
			return new IncorrectCommand("☹ OOPS!!! You are missing a number\n" +
							"Please enter a valid delete query - delete 1");
		}
		if (split.length > 2) {
			return new IncorrectCommand("☹ OOPS!!! You have too many numbers\n" +
							"Please enter a valid delete query - delete 1");
		}
		if (!isNumeric(split[1])) {
			return new IncorrectCommand("☹ OOPS!!! You entered a non-numeric item!\n" +
							"Please enter a valid delete query - delete 1");
		}

		int index = Integer.parseInt(split[1]);

		// Check if index is greater than 0.
		if (index <= 0) {
			return new IncorrectCommand("Please enter a number greater than 0!");
		}

		return new DeleteCommand(index);
	}

	/**
	 * Validates the task command
	 *
	 * @param split the split query
	 * @return the corresponding command
	 */
	public static Command validateTask(String[] split) {
		if (split.length == 1) {
			return new IncorrectCommand("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
		}

		if (split[0].equals("deadline")) {
			if (!split[1].contains("/by")) {
				return new IncorrectCommand("☹ OOPS!!! Please enter a valid deadline - deadline return book /by 2pm");
			}

			if (split[1].split("\\s+/by\\s+").length == 1) {
				return new IncorrectCommand("☹ OOPS!!! You added a /by but did not include a deadline!.\n" +
								"Please enter a valid deadline - deadline return book /by dd/MM/yy HHmm");
			}

			String[] parts = split[1].split("\\s*/by\\s+");
			String taskName = parts[0];
			if (taskName.isBlank()) {
				return new IncorrectCommand("☹ OOPS!!! The description of a deadline cannot be empty.");
			}
			String deadline = parts[1];
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
				return new AddCommand(taskName, LocalDateTime.parse(deadline, formatter));
			} catch (DateTimeParseException e) {
				return new IncorrectCommand("☹ OOPS!!! Please enter the date & time in a valid format! (DD/MM/YY HHMM)");
			}
		} else if (split[0].equals("event")) {
			if (!split[1].contains("/from") || !split[1].contains("/to")) {
				return new IncorrectCommand("☹ OOPS!!! Your query is missing the prefixes /from or /to\n" +
								"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
			}

			int fromIndex = split[1].indexOf("/from");
			int toIndex = split[1].indexOf("/to");

			if (fromIndex > toIndex) {
				return new IncorrectCommand("☹ OOPS!!! The /from prefix should come before the /to prefix.\n" +
								"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
			}
			if (split[1].split("\\s+/from\\s+").length == 1 || split[1].split("\\s+/to\\s+").length == 1) {
				return new IncorrectCommand("☹ OOPS!!! You added a /from or /to but did not include a time!.\n" +
								"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
			}
			String[] parts = split[1].split("\\s*/from\\s+|\\s*/to\\s+");
			String taskName = parts[0];
			if (taskName.isBlank()) {
				return new IncorrectCommand("☹ OOPS!!! The description of an event cannot be empty.");
			}
			String from = parts[1];
			if (from.isEmpty()) {
				return new IncorrectCommand("☹ OOPS!!! You added a /from but did not include a time!.\n" +
								"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
			}
			String to = parts[2];
			if (to.isEmpty()) {
				return new IncorrectCommand("☹ OOPS!!! You added a /to but did not include a time!.\n" +
								"Please enter a valid event - event read book /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
			}

			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
				LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
				LocalDateTime toDate = LocalDateTime.parse(to, formatter);
				if (fromDate.isAfter(toDate) || fromDate.isEqual(toDate)) {
					return new IncorrectCommand("☹ OOPS!!! The start date cannot be after the end date!");
				}
				return new AddCommand(taskName, fromDate, toDate);
			} catch (DateTimeParseException e) {
				return new IncorrectCommand("Please enter the date & time in a valid format! (DD/MM/YY HHMM)");
			}

		} else {
			return new AddCommand(split[1]);
		}
	}

	/**
	 * Validates the list command
	 *
	 * @param split the split query
	 * @return the corresponding command
	 */
	public static Command validateList(String[] split) {
		if (split.length != 1) {
			return new IncorrectCommand("☹ OOPS!!! Please enter a valid command - list");
		}
		return new ListCommand();
	}

	/**
	 * Validates the exit command
	 *
	 * @param split the split query
	 * @return the corresponding command
	 */
	public static Command validateExit(String[] split) {
		if (split.length != 1) {
			return new IncorrectCommand("☹ OOPS!!! Please enter a valid command - bye");
		}
		return new ExitCommand();
	}

	/**
	 * Validates the help command
	 *
	 * @param split the split query
	 * @return the corresponding command
	 */
	public static Command validateHelp(String[] split) {
		if (split.length != 1) {
			return new IncorrectCommand("☹ OOPS!!! Please enter a valid command - help");
		}
		return new HelpCommand();
	}

	/**
	 * Checks if the string is numeric
	 *
	 * @param str the string
	 * @return true if the string is numeric
	 */
	static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
