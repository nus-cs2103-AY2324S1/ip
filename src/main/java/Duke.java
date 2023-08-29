import duke.parser.Parser;
import duke.ui.Ui;
import duke.task.TaskList;
import java.util.Scanner;

/**
 * The main class of the program.
 */
public class Duke {

    /**
     * Name of the bot.
     */
    private final String NAME = "Snake CYQJ";
    /**
     * Dynamic array of tasks.
     */
    private final TaskList taskList = new TaskList("duke.txt");
    private Ui ui;

    /**
     * Entry-point for the program. Displays a greeting, echos the user's input, and exits.
     * @param args CLI arguments passed into the program.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            Parser.parseCommand(input, duke.taskList);
            input = scanner.nextLine();
        }
        Ui.showGoodbyeMessage();
    }

    public Duke () {
        this.ui = new Ui(this.NAME);
        this.ui.showHelloMessage();
    }
}
