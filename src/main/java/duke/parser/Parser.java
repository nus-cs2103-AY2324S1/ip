package duke.parser;
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
	 * Check that we are getting from a valdi position
	 * @param accessKey represents position that we are getting from
	 * @return Position if it is valid
	 * @throws NumberFormatException accessKey not valid
	 */

	public static int getValidIndex(String accessKey) throws PositionException {
		try {
			int pos = Integer.parseInt(accessKey);
			if (pos < 0) {
				throw new PositionException("Cannot access negative position");
			}
			return pos;
		} catch (NumberFormatException e) {
			throw new PositionException("Not a valid position indicated, please use a number\n" + e.getMessage());
		}
	}

	/**
	 * groups the actions that references task number
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

		int pos = Parser.getValidIndex(accessKey);
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
	 * parses a todo command
	 * @param fullCommand user input
	 * @return Command to be executed
	 */
	public static Command getToDo(String fullCommand) {
		StringBuilder description = new StringBuilder();
		String[] descriptionWords = fullCommand.split(" ");
		for (String s : Arrays.copyOfRange(descriptionWords, 1, descriptionWords.length)) {
			description.append(s).append(" ");
		}
		description.deleteCharAt(description.length() - 1);
		return new AddCommand(new ToDo(description.toString()));
	}

	/**
	 * groups task together
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
				return getToDo(taskPhrase);
			case "deadline":
				return getDeadLine(taskPhrase);
			case "event":
				return getEvent(taskPhrase);
			default: throw new DukeException("Not sure what task you are assigning!");
		}
	}

	/**
	 * Gets description
	 * @param commandDescription the command word together with description, i.e. todo aaa
	 * @return Description only
	 */
	public static String getDescription(String commandDescription) {
		String[] descriptionWords = commandDescription.split(" ");
		StringBuilder description = new StringBuilder();
		for (String s : Arrays.copyOfRange(descriptionWords, 1, descriptionWords.length)) {
			description.append(s).append(" ");
		}
		description.deleteCharAt(description.length() - 1);
		return description.toString();
	}

	/**
	 * Parse DeadLine
	 * @param fullCommand the user input
	 * @return Command to be executed
	 * @throws DukeException if user input does not correctly follow specified order
	 * @throws TimeFormatException if time format provided is not correct
	 */

	public static Command getDeadLine(String fullCommand) throws DukeException, TimeFormatException {
		String[] items = fullCommand.split("/");
		String[] descriptionWords = items[0].split(" ");
		String description = Parser.getDescription(items[0]);
		String timePhrase = items[1];
		boolean isValidFormat = items.length == 2 && timePhrase.split(" ")[0].equals("by") && descriptionWords.length >= 1;

		if (!isValidFormat) {
			throw new DukeException("enter deadline like this, deadline description /by:");
		}
		boolean isAmPmFormat = timePhrase.contains("am") || timePhrase.contains("pm");
		if (isAmPmFormat) {
			String[] timeDate = timePhrase.split(" ");
			if (timeDate.length != 3) {
				throw new DukeException("Enter time and date properly");
			}
			LocalDateTime begin = TimeFormat.amPmFormat(timeDate);
			return new AddCommand(new DeadLine(description, begin));
		} else {
			LocalDateTime begin = TimeFormat.fullDayFormat(description, timePhrase);
			return new AddCommand(new DeadLine(description, begin));
		}
	}

	/**
	 * checck if event format is valid
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
	 * gets event from user input
	 * @param fullCommand represent user input
	 * @return Command to add new event
	 * @throws DukeException if start is before end
	 * @throws TimeFormatException if time format is invalid
	 */
	public static Command getEvent(String fullCommand) throws DukeException, TimeFormatException {
		String[] items = fullCommand.split("/");
//		String[] descriptionWords = items[0].split(" ");
		String description = Parser.getDescription(items[0]);

		String timePhraseStart = items[1];
		String[] fromTimeStart = items[1].split(" ");
		String[] byTimeEnd = items[2].split(" ");
		boolean isAmPmFormat = timePhraseStart.contains("am") || timePhraseStart.contains("pm");
		Parser.isValidEvent(items);
		if (isAmPmFormat) {
			LocalDateTime begin = TimeFormat.amPmFormat(fromTimeStart);
			LocalDateTime end = TimeFormat.amPmFormat(byTimeEnd);
			if (begin.isAfter(end)) {
				throw new DukeException("Start is after end!");
			}
			return new AddCommand(new Event(description, begin, end));
		} else {
			LocalDateTime begin = TimeFormat.fullDayFormat(description, items[1]);
			LocalDateTime end = TimeFormat.fullDayFormat(description, items[2]);
			if (begin.isAfter(end)) {
				throw new DukeException("Start is after end!");
			}
			return new AddCommand(new Event(description, begin, end));
		}
	}

	/**
	 * parse user input into a Command
	 * @param fullCommand user input
	 * @return Command to be executed
	 * @throws DukeException for unknown commands aggregating all exceptions thrown
	 */
	public static Command parse(String fullCommand) throws DukeException {
		String[] item = fullCommand.split(" ");
		String commandWord = item[0];
		assert commandWord.length() <= 11 : "unknown command";
		try {
			return item.length == 1 ? doActionCommand(fullCommand) : item.length == 2 && !commandWord.equals("todo") ? doAccessCommand(fullCommand) : doTaskCommand(fullCommand);
		} catch (DukeException | PositionException | InvalidActionException | TimeFormatException | NumberFormatException d) {
			throw new DukeException(d.getMessage());
		}
	}
}

