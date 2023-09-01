import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

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

	public void createTask(ArrayList<Task> taskList, String action, String event) throws DukeException{
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
			String[] start = items[1].split(" ");

			for (String s : Arrays.copyOfRange(start, 1, start.length)) {
				description.append(s).append(" ");
			}
			description.deleteCharAt(description.length() - 1);

			for (String s : Arrays.copyOfRange(items[2].split(" "), 1, start.length)) {
				startTime.append(s).append(" ");
			}
			startTime.deleteCharAt(startTime.length() - 1);
			taskList.add(new Deadline(description.toString(), startTime.toString()));
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
			for (String s : Arrays.copyOfRange(startCheck, 1, startCheck.length)) {
				startTime.append(s).append(" ");
			}
			startTime.deleteCharAt(startTime.length() - 1);
			for (String s : Arrays.copyOfRange(endCheck, 1, endCheck.length)) {
				endTime.append(s).append(" ");
			}
			endTime.deleteCharAt(endTime.length() - 1);
			taskList.add(new Event(description.toString(), startTime.toString(), endTime.toString()));
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
