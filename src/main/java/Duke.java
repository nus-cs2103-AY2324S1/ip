public class Duke {

	static String botName = "GOAT";

	public static void main(String[] args) {
		String logo = "   ______    ___        _    _________ \n"
				+ " .' ___  | .'   `.     / \\  |  _   _  |\n"
				+ "/ .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
				+ "| |   ____| |   | |  / ___ \\    | |    \n"
				+ "\\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
				+ " `._____.' `.___.'|____| |____|_____|  \n";
		System.out.println("Hello from\n" + logo);

		printLine();
		greet();
		printLine();
		sayBye();
		printLine();
	}

	static void greet() {
		System.out.printf("Hello! I'm %s\n", botName);
		System.out.println("What can I do for you?");
	}

	static void sayBye() {
		System.out.println("Bye. Hope to see you again soon!");
	}

	static void printLine() {
		String line = "____________________________________________________________";
		System.out.println(line);
	}
}
