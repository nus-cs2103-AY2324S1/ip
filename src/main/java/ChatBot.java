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


	public void run() {
		Scanner newScan = new Scanner(System.in);
		ArrayList<Task> l1 = new ArrayList<>(100);
		Records r = new Records("./duke.txt", l1);
		try {
			r.readFile();
		} catch (IOException e) {
			try {
				r.writeFile();
			} catch (IOException j) {
				System.out.println("sheesh");
			}
		}

		while (newScan.hasNextLine()) {
			// outer try loop to validate Scanner input
			try {
				String action = newScan.nextLine();
				String[] words = action.split(" ");
				String Event = words[0];
				if (words.length == 1 && !Event.equals("list") && !Event.equals("bye")) {
					throw new DukeException("Invalid input");
				}

				if (Event.equals("unmark") || Event.equals("mark")) {
					int idx = Integer.parseInt(words[1]) - 1;
					if (idx + 1 > l1.size()) {
						throw new DukeException("trying to mark or unmark something beyond the list");
					}
					Task t = l1.get(idx);
					if (Event.equals("mark")) {
						t.markAsDone();
						String echo = String.format("____________________________________________________________\n" +
								"Nice! I've marked this task as done:\n" +
								t.toString() + "\n" +
								"____________________________________________________________");
						System.out.println(echo);
					} else {
						l1.get(idx).unMark();
						String echo = String.format("____________________________________________________________\n" +
								"Nice! I've marked this task as not done yet:\n" +
								t.toString() + "\n" +
								"____________________________________________________________");
						System.out.println(echo);
					}
				} else if (action.equals("bye")) {
					// exit
					String echo = "____________________________________________________________\n" +
							"Bye. Hope to see you again soon!\n" +
							"____________________________________________________________";
					System.out.println(echo);
					// write to file before leaving the system
					try {
						r.writeFile();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					System.exit(0);
				} else if (Event.equals("delete")) {
					int len = l1.size();
					String[] delete = action.split(" ");
					if (delete.length != 2) {
						throw new DukeException("Invalid delete args");
					}
					// check for exceptions as well
					Task t = l1.get(Integer.parseInt(delete[1]));
					String remaining = Integer.toString(l1.size() - 1);
					l1.remove(Integer.parseInt(delete[1]));
					String echo = String.format("    ____________________________________________________________\n" +
							"Noted. I've removed this task:\n" +
							"%s\n" +
							"Now you have %s tasks in the list.\n" +
							"____________________________________________________________", t.toString(), remaining);
					System.out.println(echo);
				} else if (Event.equals("todo") || Event.equals("deadline") || Event.equals("event")) {
					// add to task to list then print list if event is list
					// String[] slice = Arrays.copyOfRange(items, 1, items.length - 1);
					String[] items = action.split("/");
					String[] first = items[0].split(" ");
					String[] describe = Arrays.copyOfRange(first, 1, first.length);
					String description = "";
					for (String d : describe) {
						description = description + d + " ";
					}

					if (Event.equals("todo")) {
						if (first.length == 1) {
							throw new DukeException("enter todo like this, todo description");
						}
						l1.add(new ToDos(description));
					} else if (Event.equals("deadline")) {
						String[] byCheck = items[1].split(" ");
						if (items.length != 2 || !byCheck[0].equals("by")) {
							throw new DukeException("enter deadline like this, deadline description /by:");
						}
						String[] start = items[1].split(" ");
						String newStart = "";
						for (String s : Arrays.copyOfRange(start, 1, start.length)) {
							newStart = newStart + s + " ";
						}
						newStart = newStart.trim();
						l1.add(new Deadline(description, newStart));
					} else {
						String[] startCheck = items[1].split(" ");
						String[] endCheck = items[2].split(" ");
						if (items.length != 3 || !endCheck[0].equals("to") || !startCheck[0].equals("from")) {
							throw new DukeException("enter event properly, event description /from /to");
						}
						String[] start = items[1].split(" ");
						String newStart = "";
						for (String s : Arrays.copyOfRange(start, 1, start.length)) {
							newStart = newStart + s + " ";
						}
						newStart = newStart.trim();
						String[] end = items[2].split(" ");
						String newEnd = "";
						for (String s : Arrays.copyOfRange(end, 1, end.length)) {
							newEnd = newEnd + s + " ";
						}
						newEnd = newEnd.trim();
						l1.add(new Event(Event, newStart, newEnd));
					}
					Task t = l1.get(l1.size() - 1);
					String echo = String.format("____________________________________________________________\n" +
							"Got it. I've added this task:\n" +
							"%s\n" +
							"Now you have %s tasks in the list\n" +
							"____________________________________________________________", t.toString(), l1.size());
					System.out.println(echo);
				} else if (Event.equals("list")) {
					String lst = "";
					for (int i = 0; i < l1.size(); i++) {
						int idx = i + 1;
						lst += idx + ". " + l1.get(i).toString() + "\n";
					}

					String echo = String.format("____________________________________________________________\n"
							+ "Here are the task in your list:\n"
							+ "%s"
							+ "____________________________________________________________", lst);
					System.out.println(echo);
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
			}
		}
	}


}
