import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

  private static final String BOT_NAME = "GOAT";
  private static final String FILEPATH = "data/";
  private static final String SAVEFILE_NAME = "save.txt";

  private static final Scanner in = new Scanner(System.in);
  private static final TaskList taskList = new TaskList();
  private static final Printer out = new Printer();
	private static final FileIO savefile = new FileIO(FILEPATH, SAVEFILE_NAME, "savefile");

  public static void main(String[] args) {
    initialize();
    while (true) {
      String input = in.nextLine().trim();
      if (input.equals("bye")) {
				break;
			}
			CommandBuilder.parse(input).toCommand(out, taskList, savefile).execute();
    }
    sayBye();
  }

  static void initialize() {
    String logo =
        "        ______    ___        _    _________ \n"
            + "      .' ___  | .'   `.     / \\  |  _   _  |\n"
            + "     / .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
            + "     | |   ____| |   | |  / ___ \\    | |    \n"
            + "     \\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
            + "      `._____.' `.___.'|____| |____|_____|  \n";

    out.print(String.format("Hello from\n%s", logo), String.format("Hello! I'm %s", BOT_NAME));

		try {
			readFromSaveFile();
		} catch(DukeSideEffectException e) {
			out.print(e);
		}
		out.flush();
  }

	static void readFromSaveFile() {
		List<Task> tasks = new ArrayList<>();
		try {
			String input = savefile.read();
			if(input.equals("")) {
				out.print(String.format("Successfully read from savefile %s", savefile.getFilename()));
				return;
			}
			String[] lines = input.split("\n");
			Arrays.stream(lines).forEach((line) -> {
				CommandBuilder b = CommandBuilder.parse(line);
				tasks.add(Task.createTask(b.getCommand(), b.getName(), b.getArguments()));
			});
		} catch(FileNotFoundException e) {
			throw new DukeSideEffectException(String.format("Unable to open savefile %s", savefile.getFilename()));
		} catch(DukeException e) {
			throw new DukeSideEffectException(String.format("Savefile %s is not in the correct format", savefile.getFilename()));
		}
		
		out.print(String.format("Successfully read from savefile %s", savefile.getFilename()));
		taskList.populate(tasks);
	}

  static void sayBye() {
    out.print("Bye. Hope to see you again soon!");
		out.flush();
	}
}
