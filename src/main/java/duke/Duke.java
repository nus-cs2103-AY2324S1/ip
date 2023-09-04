package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A chatbot that helps user to maintain their tasks.
 *
 * @author Joseph Oliver Lim
 */
public class Duke {
    private TaskList tasks;
    private Scanner sc;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs an instance of Duke chatbot.
     */
    public Duke() {
        this.sc = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage();
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        this.ui.printHello();
        try {
            this.storage.initialize();
            this.tasks = this.storage.readFile();
        } catch (DukeException e) {
            this.ui.printErrorMessage(e);
        }
        while (this.sc.hasNextLine()) {
            try {
                String input = this.sc.nextLine();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                if (input.split(" ")[0].equals("bye")) {
                    this.sc.close();
                    break;
                }
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    /**
     * Starts the Duke chatbot.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
