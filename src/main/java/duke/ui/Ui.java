package duke.ui;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
	private String name;
	private final Scanner in;
	private PrintStream out;
	private static final String COMMENT_LINE_REGEX = "#.";

	private static final String LOGO = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	private static final String DIVIDER = "____________________________________________________________\n";

	// scannner takes in inputstream
	public Ui(InputStream in, PrintStream out) {
		this.in = new Scanner(in);
		this.out = out;
	}

	public Ui(String name) {
		this(System.in, System.out);
		this.name = name;
	}
	public boolean hasNextLine() {
		return this.in.hasNextLine();
	}

	// return the fullcommand? lines(--> commands) separated by \n
	//

	// our run will do ui.hasnextline()

	private boolean isCommentLine(String inputLine) {
		return inputLine.trim().matches(COMMENT_LINE_REGEX);
	}
	private boolean shouldIgnore(String inputLine) {
		return inputLine.trim().isEmpty() || isCommentLine(inputLine);
	}

	public String readCommand() {
		// has nextline then save it skip the comments
		out.print("Enter your Command:");
		String inputLine = in.nextLine();

		while (shouldIgnore(inputLine)) {
			inputLine = in.nextLine();
		}
		String message = String.format("Command entered: %s", inputLine);
		System.out.println(message);
		return inputLine;
	}
	public void showLoadingError(Exception e) {
		System.out.println("Unable to process stored file:");
		System.out.println(e.getMessage());
	}

	public void showWelcome() {
		String greeting = String.format("____________________________________________________________\n" +
				"Hello! I'm %s\n" +
				"What can I do for you?\n" +
				"____________________________________________________________", this.name);
		System.out.println(LOGO);
		System.out.println(greeting);
	}
	public void showGoodBye() {
		String bye = "____________________________________________________________\n" +
				"Bye. Hope to see you again soon!\n" +
				"____________________________________________________________";
		System.out.println(bye);
		// write to file before leaving the system
//		r.writeFile();
		System.exit(0);
	}


	public void showLine() {
		System.out.println(DIVIDER);
	}


}
