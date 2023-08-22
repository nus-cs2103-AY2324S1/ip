import java.util.Scanner;

/**
 * The main class for Echo Chatbot
 *
 * @author Jason Ray
 */
public class Echo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] list = new String[100];
		int current = 0;

		String LINE_BREAK = "----------------------------------------------------------";
		System.out.println(LINE_BREAK);
		System.out.println("Welcome. My name is Echo");
		System.out.println("What can I do for you?");
		System.out.println(LINE_BREAK);
		System.out.println("\n");
		System.out.println("Type 'list' to see list content");
		System.out.println("Type 'bye' to exit\n");

		String input = sc.nextLine();
		while (!input.equals("bye")) {
			if (input.equals("list")) {
				System.out.println(LINE_BREAK);
				if (current == 0) {
					System.out.println("List is empty");
				} else {
					for (int i = 1; i < current + 1; i++) {
						System.out.println(i + ". " + list[i-1]);
					}
				}
				System.out.println(LINE_BREAK);
				System.out.println("\n");
			} else {
				System.out.println(LINE_BREAK);
				System.out.println("added: " + input);
				System.out.println(LINE_BREAK);
				System.out.println("\n");
				list[current] = input;
				current++;
			}
            input = sc.nextLine();
        }
		if (input.equals("bye")) {
			System.out.println(LINE_BREAK);
			System.out.println("I hope you enjoy my service. Thank you and goodbye");
			System.out.println(LINE_BREAK);
		}
	}
}
