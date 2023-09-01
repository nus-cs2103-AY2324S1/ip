import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.text.SimpleDateFormat;
import java.text.ParseException;

//test
public class ChatBot {
	private final String name;
	ChatBot(String name) {
		this.name = name;
	}
	public void greet() {
		String greeting = String.format("____________________________________________________________\n" +
				"Hello! I'm %s\n" +
				"What can I do for you?\n" +
				"____________________________________________________________", this.name);
		System.out.println(greeting);
	}

	public void list(ArrayList<Task> taskList) {
		String lst = "";
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < taskList.size(); i++) {
			// int idx = i + 1;
			br.append(i + 1).append(". ");
			br.append((taskList.get(i)).toString()).append("\n");
		}
		String echo = String.format("____________________________________________________________\n"
				+ "Here are the task in your list:\n"
				+ "%s"
				+ "____________________________________________________________", br.toString());
		System.out.println(echo);
	}

	public void toMark(ArrayList<Task> taskList, String event, int idx) throws DukeException {
		if (idx + 1 > taskList.size()) {
			throw new DukeException("trying to mark or unmark something beyond the list");
		}
		Task t = taskList.get(idx);
		if (event.equals("mark")) {
			t.markAsDone();
			String echo = String.format("____________________________________________________________\n" +
					"Nice! I've marked this task as done:\n" +
					t.toString() + "\n" +
					"____________________________________________________________");
			System.out.println(echo);
		} else {
			taskList.get(idx).unMark();
			String echo = String.format("____________________________________________________________\n" +
					"Nice! I've marked this task as not done yet:\n" +
					t.toString() + "\n" +
					"____________________________________________________________");
			System.out.println(echo);
		}
	}

	// just take in a /by/from/to 29june and reformat properly
	public String formatDate(ArrayList<Task> taskList, String[] items, String[] timeDate) {
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

	public void createTask(ArrayList<Task> taskList, String action, String event) throws DukeException, DateTimeParseException {
		String[] items = action.split("/");
		String[] first = items[0].split(" ");
		StringBuilder description = new StringBuilder();
		StringBuilder startTime = new StringBuilder();
		StringBuilder endTime = new StringBuilder();


		if (event.equals("todo")) {
			if (first.length == 1) {
				throw new DukeException("enter todo like this, todo description");
			} else {
				for (String s : Arrays.copyOfRange(first, 1, first.length)) {
					description.append(s);
					description.append(" ");
				}
				description.deleteCharAt(description.length() - 1);
				taskList.add(new ToDos(description.toString()));
			}
		} else if (event.equals("deadline")) {
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
				String dateFormat = formatDate(taskList, items, timeDate);
				LocalDateTime begin = LocalDateTime.parse(dateFormat);
				taskList.add(new Deadline(description.toString(), begin));
			} else {
				// /by 06:30:00 2015-06-29
				String[] time = Arrays.copyOfRange(items[1].split(" "), 1, 3);
				startTime.append(time[1]).append("T").append(time[0]);
				LocalDateTime begin = LocalDateTime.parse(startTime.toString());
				// store datetime object as a string
				// taskList.add(new Deadline(description.toString(), startTime.toString()));
				taskList.add(new Deadline(description.toString(), begin));
			}
		} else {
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
				String dateFormatStart = formatDate(taskList, items, timeDateStart);
				LocalDateTime begin = LocalDateTime.parse(dateFormatStart);
				String[] timeDateEnd = Arrays.copyOfRange(items[2].split(" "), 1, items[1].split(" ").length);
				String dateFormatEnd = formatDate(taskList, items, timeDateEnd);
				LocalDateTime end = LocalDateTime.parse(dateFormatEnd);
				if (begin.isAfter(end)) {
					throw new DukeException("Start is after end!");
				}
				taskList.add(new Event(description.toString(), begin, end));
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
				taskList.add(new Event(description.toString(), begin, end));
			}
//			// ENDD
		}
		Task t = taskList.get(taskList.size() - 1);
		String echo = String.format("____________________________________________________________\n" +
				"Got it. I've added this task:\n" +
				"%s\n" +
				"Now you have %s tasks in the list\n" +
				"____________________________________________________________", t.toString(), taskList.size());
		System.out.println(echo);
	}

	public void delete(ArrayList<Task> taskList, String[] words) throws DukeException, NumberFormatException {
		if (words.length != 2) {
			throw new DukeException("Invalid delete args");
		}
		// check for exceptions as well
		int idx = Integer.parseInt(words[1]) - 1;
		Task t = taskList.get(idx);
		String remaining = Integer.toString(taskList.size() - 1);
		taskList.remove(idx);
		String echo = String.format("    ____________________________________________________________\n" +
				"Noted. I've removed this task:\n" +
				"%s\n" +
				"Now you have %s tasks in the list.\n" +
				"____________________________________________________________", t.toString(), remaining);
		System.out.println(echo);
	}

	public String monthValue(String month) throws IllegalArgumentException {
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

	public void bye(Records r) throws IOException {
		// exit
		String echo = "____________________________________________________________\n" +
				"Bye. Hope to see you again soon!\n" +
				"____________________________________________________________";
		System.out.println(echo);
		// write to file before leaving the system
		r.writeFile();
		System.exit(0);
	}
	public void run() {
		Scanner newScan = new Scanner(System.in);
		ArrayList<Task> taskList = new ArrayList<>(100);
		Records r = new Records("./duke.txt", taskList);
		try {
			r.readFile();
			list(taskList);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			try {
				r.writeFile();
			} catch (IOException j) {
				System.out.println(j.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		while (newScan.hasNextLine()) {
			// outer try loop to validate Scanner input
			try {
				String action = newScan.nextLine();
				String[] words = action.split(" ");
				String event = words[0];
				if (words.length == 1 && !event.equals("list") && !event.equals("bye")) {
					throw new DukeException("Invalid input");
				}

				if (event.equals("unmark") || event.equals("mark")) {
					int idx = Integer.parseInt(words[1]) - 1;
					toMark(taskList, action, idx);
				} else if (action.equals("bye")) {
					bye(r);
				} else if (event.equals("delete")) {
					int len = taskList.size();
					delete(taskList, words);
					// where to handle it?
				} else if (event.equals("todo") || event.equals("deadline") || event.equals("event")) {
					// add to task to list then print list if event is list
					// String[] slice = Arrays.copyOfRange(items, 1, items.length - 1);
					createTask(taskList, action, event);
				} else if (event.equals("list")) {
					list(taskList);
				} else {
					throw new DukeException("enter valid args");
				}
			} catch(DukeException e){
				System.out.println(e.getMessage());
				System.out.println("Enter valid string input:\n"
						+ "todo\n"
						+ "deadline description by:\n"
						+ "event /from /to \n"
						+ "list\n"
						+ "mark idx\n"
						+ "unmark idx\n"
						+ "bye");
			} catch (NumberFormatException n) {
				System.out.println("enter a valid idx");
			} catch (IOException i) {
				System.out.println(i.getMessage());
			}
		}
	}


}
