import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * A chatbot that helps the user take note of tasks.
 *
 * @author Armando Jovan Kusuma
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner scanner;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage();
    }
    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        this.ui.greet();
        try {
            this.storage.init();
            this.tasks = this.storage.readFromFile();
        } catch (DukeException e) {
            this.ui.errorPrint(e);
        }
        while (true) {
            try {
                String input = this.scanner.nextLine();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                if (input.split(" ")[0].equals("bye")) {
                    this.scanner.close();
                    break;
                }
            } catch (DukeException e) {
                this.ui.errorPrint(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}