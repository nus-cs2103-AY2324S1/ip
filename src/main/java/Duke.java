import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
	static ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) {
		printLine();
		System.out.println("Hello I'm RyamBot");
		System.out.println("What can I do for you?");
		printLine();
		queryBot();
	}

	static void printLine() {
		System.out.println("____________________________________________________________");
	}

	static void addToList(String item) {
		printLine();
		list.add(item);
		System.out.println("added: " + item);
		printLine();
	}

	static void printList() {
		printLine();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + ". " + list.get(i));
		}
		printLine();
	}

	static void exit() {
		printLine();
		System.out.println("Bye. Hope to see you again soon!");
		printLine();
	}

	static void queryBot() {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		String query = myObj.nextLine();  // Read user input
		if (query.equals("bye")) {
			exit();
		} else if (query.equals("list")) {
			printList();
			queryBot();
		} else {
			addToList(query);
			queryBot();
		}
	}
}
