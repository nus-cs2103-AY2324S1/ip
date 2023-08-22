import java.util.Scanner;

/**
 * The main class for Echo Chatbot
 *
 * @author Jason Ray
 */
public class Echo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Task[] list = new Task[100];
		int current = 0;

		String LINE_BREAK = "----------------------------------------------------------";
		System.out.println(LINE_BREAK);
		System.out.println("Welcome. My name is Echo");
		System.out.println("What will you do today?");
		System.out.println(LINE_BREAK);
		System.out.println("\n");
		System.out.println("Type 'mark' to mark task as done");
		System.out.println("Type 'unmark' to mark task as not done");
		System.out.println("Type 'list' to see list content");
		System.out.println("Type 'bye' to exit\n");

		while (true) {
            String input = sc.nextLine();
			if (input.toLowerCase().equals("list")) {
				System.out.println(LINE_BREAK);
				if (current == 0) {
					System.out.println("List is empty");
				} else {
					for (int i = 1; i < current + 1; i++) {
						System.out.println(i + ".[" + 
							(list[i-1].getDone() ? "X" : " ")
							+ "] " + list[i-1].getName());
					}
				}
				System.out.println(LINE_BREAK);
				System.out.println("\n");
			} else if (input.toLowerCase().equals("mark"))  {
				System.out.println("Which task do you want to mark as done?");
				int taskNum = sc.nextInt();
				if (taskNum > current || taskNum < 1) {
					System.out.println(LINE_BREAK);
					System.out.println("I'm sorry, you have typed in an invalid number, please try again");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
				} else {
					list[taskNum - 1].markAsDone();
					System.out.println(LINE_BREAK);
					System.out.println("You have marked this task as done");
					System.out.println("\t[X] " + list[taskNum - 1].getName());
					System.out.println(LINE_BREAK);
					System.out.println("\n");
				}
			} else if (input.toLowerCase().equals("unmark")) {
				System.out.println("Which task do you want to mark as not done?");
				int taskNum = sc.nextInt();
				if (taskNum > current || taskNum < 1) {
					System.out.println(LINE_BREAK);
					System.out.println("I'm sorry, you have typed in an invalid number, please try again");
					System.out.println(LINE_BREAK);
					System.out.println("\n");
				} else {
					list[taskNum - 1].markAsNotDone();
					System.out.println(LINE_BREAK);
					System.out.println("You have marked this task as not done");
					System.out.println("\t[ ] " + list[taskNum - 1].getName());
					System.out.println(LINE_BREAK);
					System.out.println("\n");
				}
			} else if (input.toLowerCase().equals("bye")) {
				System.out.println(LINE_BREAK);
				System.out.println("I hope you enjoy my service. Thank you and goodbye");
				System.out.println(LINE_BREAK);
				break;
			} else if (input.toLowerCase().equals("")) {
				continue;
			} else {
				System.out.println(LINE_BREAK);
				System.out.println("added: " + input);
				System.out.println(LINE_BREAK);
				System.out.println("\n");
				list[current] = new Task(input);
				current++;
			}
        }
	}
}
