import java.util.Scanner;

public class Duke {
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

	static void queryBot() {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		String query = myObj.nextLine();  // Read user input
//		  case "list": {
//				printLine();
//				System.out.println("list");
//				printLine();
//				queryBot();
//				break;
//			}
//			case "blah": {
//				printLine();
//				System.out.println("blah");
//				printLine();
//				queryBot();
//				break;
//			}
		if (query.equals("bye")) {
			printLine();
			System.out.println("Bye. Hope to see you again soon!");
			printLine();
		} else {
			printLine();
			System.out.println(query);
			printLine();
			queryBot();
		}
	}
}
