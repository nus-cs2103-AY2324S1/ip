import java.util.Scanner;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class of the program.
 */
public class Duke {

    private static final String BOT_NAME = "Snake CYQJ";
    private final TaskList taskList = new TaskList("duke.txt");
    private Ui ui;
    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui(BOT_NAME);
        this.ui.showHelloMessage();
    }

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


}
