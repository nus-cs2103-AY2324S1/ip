package duke.parser;
import duke.DukeException;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.ToDos;
import duke.task.Deadline;
import duke.task.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

//package duke.parser;
public class Parser {
	public static String monthValue(String month) throws IllegalArgumentException {
		switch (month.toLowerCase()) {
			case "january":
				return "01";
			case "february":
				return "02";
			case "march":
				return "03";
			case "april":
				return "04";
			case "may":
				return "05";
			case "june":
				return "06";
			case "july":
				return "07";
			case "august":
				return "08";
			case "september":
				return "09";
			case "october":
				return "10";
			case "november":
				return "11";
			case "december":
				return "12";
			default:
				throw new IllegalArgumentException("Invalid month string");
		}
	}

	public static String formatDate(String[] timeDate) {
		SimpleDateFormat inputTime = new SimpleDateFormat("ha");
		SimpleDateFormat outputTime = new SimpleDateFormat("HH:mm:ss");
		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		String dateFormat = "";
		try {
			Date time = inputTime.parse(timeDate[0]);
			String formatTime = outputTime.format(time);
			// return the formatTime and formatDate as the stored value
			// dateTtime
			String dayMonth = timeDate[1];
			String day;
			String month;
			try {
				// 630pm 29june
				// if can means is 10 june cannot means is 1 june
				Integer.parseInt(dayMonth.substring(1, 2));
				day = dayMonth.substring(0,2);
				month = dayMonth.substring(2);
			} catch (NumberFormatException e) {
				day = dayMonth.substring(0,1);
				month = dayMonth.substring(1);
			}
			StringBuilder br = new StringBuilder();
			br.append(year).append("-").append(monthValue(month)).append("-").append(day).append("T").append(formatTime);
			dateFormat = br.toString();
		}   catch (ParseException e) {
			System.out.println("format of time is not right, enter it as /by 630pm 18june");
		}
		return dateFormat;
	}

	// convert the string into a command
	public static Command parse(String fullCommand) throws DukeException {
		// !!! many lines now to edit!!!!
		String[] command = fullCommand.split(" ");
		String commandWord = fullCommand.split(" ")[0];
		// delimited by the / for time task like deadline and event
		String[] items = fullCommand.split("/");
		// contains description
		String[] first = items[0].split(" ");
		// save as I go so the bye is only bye not save
		// each add command I need to write to file instead of using arraylist add at the end!!!
		StringBuilder description = new StringBuilder();
		StringBuilder startTime = new StringBuilder();
		StringBuilder endTime = new StringBuilder();

		switch (commandWord) {
			case "bye":
				return new ByeCommand();
			case "list":
				return new ListCommand();
			case "mark":
				try {
					int pos = Integer.parseInt(command[1]);
					return new MarkCommand(true, pos);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Cannot mark given position\n" + e.getMessage());
				} catch (NumberFormatException n) {
					System.out.println("Not a valid position to mark\n" + n.getMessage());
				}
			case "unmark":
				try {
					int pos = Integer.parseInt(command[1]);
					return new MarkCommand(false, pos);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Cannot mark given position\n" + e.getMessage());
				} catch (NumberFormatException n) {
					System.out.println("Not a valid position to mark\n" + n.getMessage());
				}
			case "delete":
				try {
					if (command.length != 2) {
						throw new DukeException("Enter a valid position to delete");
					}
					int pos = Integer.parseInt(command[1]) - 1;
					return new DeleteCommand(pos);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Trying to delete from an invalid index\n" + e.getMessage());
				} catch (DukeException d) {
					System.out.println(d.getMessage());
				} catch (NumberFormatException n) {
					System.out.println("The delete argument is not recognise as an integer\n" + n.getMessage());
				}
			case "todo":
				try {
					if (command.length == 1) {
						throw new DukeException("Enter todo with a description!");
					}
					for (String s : Arrays.copyOfRange(command, 1, command.length)) {
						description.append(s);
						description.append(" ");
					}
					description.deleteCharAt(description.length() - 1);
					return new AddCommand(new ToDos(description.toString()));
				} catch (DukeException e) {
					System.out.println(e.getMessage());
				}
			case "deadline":
				try {
					String[] byCheck = items[1].split(" ");
					if (items.length != 2 || !byCheck[0].equals("by")) {
						throw new DukeException("enter deadline like this, deadline description /by:");
					}
					String[] start = items[0].split(" ");
					for (String s : Arrays.copyOfRange(start, 1, start.length)) {
						description.append(s).append(" ");
					}
					description.deleteCharAt(description.length() - 1);
					// /by 06:30:00 2015-06-29
					// /by 630am/pm 29 june
					if (items[1].contains("am") || items[1].contains("pm")) {
						// take away by and give timeDate 630am 29june
						String[] timeDate = Arrays.copyOfRange(items[1].split(" "), 1, items[1].split(" ").length);
						String dateFormat = formatDate(timeDate);
						LocalDateTime begin = LocalDateTime.parse(dateFormat);
//						taskList.add(new Deadline(description.toString(), begin));
						return new AddCommand(new Deadline(description.toString(), begin));
					} else {
						// /by 06:30:00 2015-06-29
						String[] time = Arrays.copyOfRange(items[1].split(" "), 1, 3);
						startTime.append(time[1]).append("T").append(time[0]);
						LocalDateTime begin = LocalDateTime.parse(startTime.toString());
						// store datetime object as a string
						// taskList.add(new Deadline(description.toString(), startTime.toString()));
//						taskList.add(new Deadline(description.toString(), begin));
						return new AddCommand(new Deadline(description.toString(), begin));
					}
				} catch (DukeException e) {
					System.out.println(e.getMessage());
				}
			case "event":
				try {
					String[] startCheck = items[1].split(" ");
					String[] endCheck = items[2].split(" ");
					if (items.length != 3 || !endCheck[0].equals("to") || !startCheck[0].equals("from")) {
						throw new DukeException("enter event properly, event description /from /to");
					}
					for (String s : Arrays.copyOfRange(first, 1, first.length)) {
						description.append(s).append(" ");
					}
					description.deleteCharAt(description.length() - 1);
					// HEADD
					// add from and to
					if (items[1].contains("am") || items[1].contains("pm")) {
						// take away by and give timeDate 630am 29june
						String[] timeDateStart = Arrays.copyOfRange(items[1].split(" "), 1, items[1].split(" ").length);
						String dateFormatStart = formatDate(timeDateStart);
						LocalDateTime begin = LocalDateTime.parse(dateFormatStart);
						String[] timeDateEnd = Arrays.copyOfRange(items[2].split(" "), 1, items[1].split(" ").length);
						String dateFormatEnd = formatDate(timeDateEnd);
						LocalDateTime end = LocalDateTime.parse(dateFormatEnd);
						if (begin.isAfter(end)) {
							throw new DukeException("Start is after end!");
						}
//						taskList.add(new Event(description.toString(), begin, end));
						return new AddCommand(new Event(description.toString(), begin, end));
					} else {
						// /by 06:30:00 2015-06-29
						String[] timeStart = Arrays.copyOfRange(items[1].split(" "), 1, 3);
						startTime.append(timeStart[1]).append("T").append(timeStart[0]);
						System.out.println(startTime.toString());
						LocalDateTime begin = LocalDateTime.parse(startTime.toString());

						String[] timeEnd = Arrays.copyOfRange(items[2].split(" "), 1, 3);
						endTime.append(timeEnd[1]).append("T").append(timeEnd[0]);
						LocalDateTime end = LocalDateTime.parse(endTime.toString());
						// store datetime object as a string
						// taskList.add(new Deadline(description.toString(), startTime.toString()));
						if (begin.isAfter(end)) {
							throw new DukeException("Start is after end!");
						}
//						taskList.add(new Event(description.toString(), begin, end));
						return new AddCommand(new Event(description.toString(), begin, end));
					}
				} catch (DukeException e) {
					System.out.println(e.getMessage());
				}
			default:
				throw new DukeException("Invalid command that do not match any known command");
		}
	}
}

//	public void createTask(ArrayList<Task> taskList, String action, String event) throws DukeException, DateTimeParseException {
//		String[] items = action.split("/");
//		String[] first = items[0].split(" ");
//		StringBuilder description = new StringBuilder();
//		StringBuilder startTime = new StringBuilder();
//		StringBuilder endTime = new StringBuilder();
//
//
//		if (event.equals("todo")) {
//			if (first.length == 1) {
//				throw new DukeException("enter todo like this, todo description");
//			} else {
//				for (String s : Arrays.copyOfRange(first, 1, first.length)) {
//					description.append(s);
//					description.append(" ");
//				}
//				description.deleteCharAt(description.length() - 1);
//				taskList.add(new ToDos(description.toString()));
//			}
//		} else if (event.equals("deadline")) {
//			String[] byCheck = items[1].split(" ");
//			if (items.length != 2 || !byCheck[0].equals("by")) {
//				throw new DukeException("enter deadline like this, deadline description /by:");
//			}
//			String[] start = items[0].split(" ");
//
//			for (String s : Arrays.copyOfRange(start, 1, start.length)) {
//				description.append(s).append(" ");
//			}
//			description.deleteCharAt(description.length() - 1);
//			// /by 06:30:00 2015-06-29
//			// /by 630am/pm 29 june
//			if (items[1].contains("am") || items[1].contains("pm")) {
//				// take away by and give timeDate 630am 29june
//				String[] timeDate = Arrays.copyOfRange(items[1].split(" "), 1, items[1].split(" ").length);
//				String dateFormat = formatDate(taskList, items, timeDate);
//				LocalDateTime begin = LocalDateTime.parse(dateFormat);
//				taskList.add(new Deadline(description.toString(), begin));
//			} else {
//				// /by 06:30:00 2015-06-29
//				String[] time = Arrays.copyOfRange(items[1].split(" "), 1, 3);
//				startTime.append(time[1]).append("T").append(time[0]);
//				LocalDateTime begin = LocalDateTime.parse(startTime.toString());
//				// store datetime object as a string
//				// taskList.add(new Deadline(description.toString(), startTime.toString()));
//				taskList.add(new Deadline(description.toString(), begin));
//			}
//		} else {
//			String[] startCheck = items[1].split(" ");
//			String[] endCheck = items[2].split(" ");
//			if (items.length != 3 || !endCheck[0].equals("to") || !startCheck[0].equals("from")) {
//				throw new DukeException("enter event properly, event description /from /to");
//			}
//			for (String s : Arrays.copyOfRange(first, 1, first.length)) {
//				description.append(s).append(" ");
//			}
//			description.deleteCharAt(description.length() - 1);
//			// HEADD
//			// add from and to
//			if (items[1].contains("am") || items[1].contains("pm")) {
//				// take away by and give timeDate 630am 29june
//				String[] timeDateStart = Arrays.copyOfRange(items[1].split(" "), 1, items[1].split(" ").length);
//				String dateFormatStart = formatDate(taskList, items, timeDateStart);
//				LocalDateTime begin = LocalDateTime.parse(dateFormatStart);
//				String[] timeDateEnd = Arrays.copyOfRange(items[2].split(" "), 1, items[1].split(" ").length);
//				String dateFormatEnd = formatDate(taskList, items, timeDateEnd);
//				LocalDateTime end = LocalDateTime.parse(dateFormatEnd);
//				if (begin.isAfter(end)) {
//					throw new DukeException("Start is after end!");
//				}
//				taskList.add(new Event(description.toString(), begin, end));
//			} else {
//				// /by 06:30:00 2015-06-29
//				String[] timeStart = Arrays.copyOfRange(items[1].split(" "), 1, 3);
//				startTime.append(timeStart[1]).append("T").append(timeStart[0]);
//				System.out.println(startTime.toString());
//				LocalDateTime begin = LocalDateTime.parse(startTime.toString());
//
//				String[] timeEnd = Arrays.copyOfRange(items[2].split(" "), 1, 3);
//				endTime.append(timeEnd[1]).append("T").append(timeEnd[0]);
//				LocalDateTime end = LocalDateTime.parse(endTime.toString());
//				// store datetime object as a string
//				// taskList.add(new Deadline(description.toString(), startTime.toString()));
//				if (begin.isAfter(end)) {
//					throw new DukeException("Start is after end!");
//				}
//				taskList.add(new Event(description.toString(), begin, end));
//			}
////			// ENDD
//		}
//	}
