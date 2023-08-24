import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

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
		ArrayList<String> l1 = new ArrayList<>();
		while (newScan.hasNextLine()) {
			String action = newScan.nextLine();
			if (action.equals("bye")) {
				// exit
				String echo = "____________________________________________________________\n" +
						"Bye. Hope to see you again soon!\n" +
						"____________________________________________________________";
				System.out.println(echo);
				System.exit(0);
			} else if (!action.equals("list")) {
				l1.add(action);
				String echo = String.format("____________________________________________________________\n" +
						"added: %s\n" +
						"____________________________________________________________", action);
				System.out.println(echo);
			} else {
				String lst = "";
				for (int i = 0; i < l1.size(); i++) {
					int idx = i + 1;
					lst += idx + ". " + l1.get(i) + "\n";
				}
				String echo = String.format("____________________________________________________________\n"
						+ "%s"
						+ "____________________________________________________________", lst);
				System.out.println(echo);
			}
		}
	}

}
