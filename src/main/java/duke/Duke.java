package duke;

import java.util.Scanner;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.parser.Parser;

/**
 * A chatbot inspired by the Java Mascot: duke.Duke.
 */
public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for duke.Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        storage.load(this.tasks);
    }

    /**
     * This runs the duke programs main chat functionality,
     * the while loop continues until user types bye.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        while(true) {
            String command = sc.nextLine();
            boolean isBye = parser.parseCommand(command, tasks);
            if (isBye) { break; }
        }
    }


    public static void main(String[] args) {
        Duke bob = new Duke();
        bob.ui.printStart();
        bob.run();
        bob.ui.printEnd();
    }
}
