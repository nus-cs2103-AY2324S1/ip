package duke.parser;
import duke.Duke;
import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidActionException;
import duke.exception.PositionException;
import duke.exception.TimeFormatException;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.ToDo;
import duke.time.TimeFormat;

import java.util.Arrays;
import java.time.LocalDateTime;

/**
 * Represents a formatting object that formats inputs in known formats.
 * Reformats these inputs into different forms depending on the Command.
 */
public class Parser {
	/**
	 * Group the single words commands together
	 *
	 * @param actionPhrase action phrase representing what is to be done
	 * @return Command to be executed
	 * @throws InvalidActionException if the action phrase is not recognised
	 */
	public static Command doActionCommand(String actionPhrase) throws InvalidActionException {
		assert actionPhrase.length() <= 11 : "Either bye or list only";
		switch (actionPhrase) {
			case "bye":
				return new ByeCommand();
			case "list":
				return new ListCommand();
			case "archivelist":
				return new ArchiveListCommand();
			case "help":
				return new HelpCommand();
			case "clear":
				return new ClearCommand();
			default:
				throw new InvalidActionException("That is not a valid action, either bye or list");
		}
	}

	/**
	 * Check that we are getting from a valid position
	 *
	 * @param accessKey represents position that we are getting from
	 * @return Position if it is valid
	 * @throws NumberFormatException accessKey not valid
	 */

	public static int getValidIndex(String accessKey) throws PositionException {
		int pos;

		try {
			pos = Integer.parseInt(accessKey);
		} catch (NumberFormatException e) {
			throw new PositionException("Not a valid position indicated, please use a number\n" + e.getMessage());
		}

		if (pos < 0) {
			throw new PositionException("Cannot access negative position");
		}
		return pos;
	}

	/**
	 * Groups the actions that references task number
	 *
	 * @param accessPhrase input phrase from user
	 * @return Command to be executed
	 * @throws PositionException if task number provided is not valid
	 * @throws DukeException for unknown AccessCommands
	 */
	public static Command doAccessCommand(String accessPhrase) throws PositionException, DukeException, NumberFormatException {
		String[] words = accessPhrase.split(" ");
		String commandWord = words[0];
		assert commandWord.length() <= 13 : "not valid command";

		String accessKey = words[1];
		boolean isFind = commandWord.equals("find");

		int pos = isFind ? 0 : Parser.getValidIndex(accessKey);

		boolean isMark = commandWord.equals("mark") || commandWord.equals("unmark");
		boolean isDelete = commandWord.equals("delete");
		boolean isArchive = commandWord.equals("archive");
		boolean isUnArchive = commandWord.equals("unarchive");
		boolean isArchiveDelete = commandWord.equals("archivedelete");

		if (isFind) {
			return new FindCommand(accessKey);
		} else if (isMark) {
			return commandWord.equals("mark") ? new MarkCommand(true, pos) : new MarkCommand(false, pos);
		} else if (isDelete) {
			return new DeleteCommand(pos);
		} else if (isArchive) {
			return new ArchiveCommand(true, pos);
		} else if (isUnArchive) {
			return  new ArchiveCommand(false, pos);
		} else if (isArchiveDelete) {
			return  new ArchiveDeleteCommand(pos);
		} else {
			throw new DukeException("unknown command");
		}
	}

	/**
	 * Parses a todo user input into a todo command
	 *
	 * @param fullCommand user input
	 * @return Command to be executed
	 */
	public static ToDo getToDo(String fullCommand) {
		StringBuilder description = new StringBuilder();
		String[] descriptionWords = fullCommand.split(" ");
		for (String s : Arrays.copyOfRange(descriptionWords, 1, descriptionWords.length)) {
			description.append(s).append(" ");
		}
		description.deleteCharAt(description.length() - 1);
//		return new AddCommand(new ToDo(description.toString()));
		return new ToDo(description.toString());
	}

	/**
	 * Groups task together
	 *
	 * @param taskPhrase the user input
	 * @return Command to be executed
	 * @throws DukeException if unknown task command
	 * @throws TimeFormatException if time format provided is not valid
	 */
	public static Command doTaskCommand(String taskPhrase) throws DukeException, TimeFormatException {
		String command = taskPhrase.split(" ")[0];
		assert(command.length() <= 8) : "not a valid task command";
		switch (command) {
			case "todo":
				return new AddCommand(getToDo(taskPhrase));
			case "deadline":
				return new AddCommand(getDeadLine(taskPhrase));
			case "event":
				return new AddCommand(getEvent(taskPhrase));
			default: throw new DukeException("Not sure what task you are assigning!");
		}
	}

	/**
	 * Gets description
	 * @param commandDescription the command word together with description, i.e. todo aaa
	 * @return Description only
	 */
	public static String getDescription(String commandDescription) throws DukeException {
		String[] descriptionWords = commandDescription.split(" ");
		if (descriptionWords.length == 1) {
			throw new DukeException("Please enter a description");
		}
		StringBuilder description = new StringBuilder();
		for (String s : Arrays.copyOfRange(descriptionWords, 1, descriptionWords.length)) {
			description.append(s).append(" ");
		}
		description.deleteCharAt(description.length() - 1);
		return description.toString();
	}

	/**
	 * Parse DeadLine
	 *
	 * @param fullCommand the user input
	 * @return Command to be executed
	 * @throws DukeException if user input does not correctly follow specified order
	 * @throws TimeFormatException if time format provided is not correct
	 */

	public static DeadLine getDeadLine(String fullCommand) throws DukeException, TimeFormatException {
		String[] items = fullCommand.split("/");
		if (items.length == 1) {
			throw new DukeException("Remember to indicate time /by");
		}
		String[] descriptionWords = items[0].split(" ");
		String description = Parser.getDescription(items[0]);
		String timePhrase = items[1];
		String[] timeDate = timePhrase.split(" ");
		boolean isValidFormat = items.length == 2 && timePhrase.split(" ")[0].equals("by") && descriptionWords.length >= 1 && timeDate.length == 3;
		if (!isValidFormat) {
			throw new DukeException("enter deadline like this, deadline description /by:");
		}
		boolean isAmPmFormat = timePhrase.contains("am") || timePhrase.contains("pm");
		LocalDateTime begin;
		if (isAmPmFormat) {
			begin = TimeFormat.amPmFormat(timeDate);
		} else {
			begin = TimeFormat.fullDayFormat(description, timePhrase);
		}
		return new DeadLine(description, begin);
//		return new AddCommand(new DeadLine(description, begin));
	}

	/**
	 * Checks if event format is valid
	 *
	 * @param items represent user input
	 * @throws DukeException if invalid event
	 */

	public static void isValidEvent(String[] items) throws DukeException {
		String[] startCheck = items[1].split(" ");
		String[] endCheck = items[2].split(" ");
		String[] fromTimeStart = items[1].split(" ");
		String[] byTimeEnd = items[2].split(" ");
		if (!endCheck[0].equals("to") || !startCheck[0].equals("from")) {
			throw new DukeException("enter event properly, event description /from /to");
		}
		if (fromTimeStart.length != 3 || byTimeEnd.length != 3) {
			throw new DukeException("Enter Event properly");
		}
	}

	/**
	 * Gets event from user input
	 *
	 * @param fullCommand represent user input
	 * @return Command to add new event
	 * @throws DukeException if start is before end
	 * @throws TimeFormatException if time format is invalid
	 */
	public static Event getEvent(String fullCommand) throws DukeException, TimeFormatException {
		String[] items = fullCommand.split("/");
		String description = Parser.getDescription(items[0]);
		if (items.length != 3) {
			throw new DukeException("Unknown Event format");
		}

		String timePhraseStart = items[1];
		String[] fromTimeStart = items[1].split(" ");
		String[] byTimeEnd = items[2].split(" ");
		boolean isAmPmFormat = timePhraseStart.contains("am") || timePhraseStart.contains("pm");
		Parser.isValidEvent(items);

		LocalDateTime begin;
		LocalDateTime end;
		if (isAmPmFormat) {
			begin = TimeFormat.amPmFormat(fromTimeStart);
			end = TimeFormat.amPmFormat(byTimeEnd);
		} else {
			begin = TimeFormat.fullDayFormat(description, items[1]);
			end = TimeFormat.fullDayFormat(description, items[2]);
		}

		if (begin.isAfter(end)) {
			throw new DukeException("Start is after end!");
		}
//		return new AddCommand(new Event(description, begin, end));
		return new Event(description, begin, end);
	}

	/**
	 * Parse user input into a Command.
	 *
	 * @param fullCommand user input.
	 * @return Command to be executed.
	 * @throws DukeException for unknown commands aggregating all exceptions thrown.
	 */
	public static Command parse(String fullCommand) throws DukeException {
		String[] item = fullCommand.split(" ");
		if (item.length == 0) {
			throw new DukeException("Space is not a valid command");
		}
		String commandWord = item[0];
		assert commandWord.length() <= 11 : "unknown command";
		try {
			return item.length == 1 ? doActionCommand(fullCommand) : item.length == 2 && !commandWord.equals("todo") ? doAccessCommand(fullCommand) : doTaskCommand(fullCommand);
		} catch (DukeException | PositionException | InvalidActionException | TimeFormatException | NumberFormatException d) {
			throw new DukeException(d.getMessage());
		}
	}
}

