import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {

	private String name;

	Chatbot(String name) {
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
		while (newScan.hasNextLine()) {
			String action = newScan.nextLine();
			String[] words = action.split(" ");
			if (words.length > 1 && (words[0].equals("unmark") || words[0].equals("mark"))) {
				int idx = Integer.parseInt(words[1]) - 1;
				Task t = l1.get(idx);
				if (words[0].equals("mark")) {
					t.markAsDone();
					String echo = String.format("____________________________________________________________\n" +
							"Nice! I've marked this task as done:\n" +
							t.getDescription() + "\n" +
							"____________________________________________________________");
					System.out.println(echo);
				} else {
					l1.get(idx).unMark();
					String echo = String.format("____________________________________________________________\n" +
							"OK, I've marked this task as not done yet:\n" +
							t.getDescription() + "\n" +
							"____________________________________________________________");
					System.out.println(echo);
				}

			} else if (action.equals("bye")) {
				// exit
				String echo = "____________________________________________________________\n" +
						"Bye. Hope to see you again soon!\n" +
						"____________________________________________________________";
				System.out.println(echo);
				System.exit(0);
			} else if (!action.equals("list")) {
				l1.add(new Task(action));
				String echo = String.format("____________________________________________________________\n" +
						"added: %s\n" +
						"____________________________________________________________", action);
				System.out.println(echo);
			} else {
				String lst = "";
				for (int i = 0; i < l1.size(); i++) {
					int idx = i + 1;
					lst += idx + ". " + l1.get(i).getDescription() + "\n";
				}
				String echo = String.format("____________________________________________________________\n"
						+ "Here are the task in your list:\n"
						+ "%s"
						+ "____________________________________________________________", lst);
				System.out.println(echo);
			}
		}
	}

}
