package Duke;

import java.util.Scanner;
import Duke.Tasks.TaskList;

/**
 * The Duke class is the main class that serves as the entry point to the Duke application.
 */
public class Duke {
    /**
     * The main method that starts the Duke application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        Ui iu = new Ui();
        Parser her = new Parser();
        TaskList tasks = new TaskList();
        boolean running = true;
        while (running) {
            String entry = sc.nextLine();
            running = her.inputs(entry, tasks, iu);
        }
    }
}
