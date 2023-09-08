package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A chatbot that helps the user take note of tasks.
 *
 * @author Armando Jovan Kusuma
 */
public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Scanner scanner;

    /**
     * Constructs an instance of the chatbot.
     */
    public Duke() {
        this.scanner = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.storage.init();
            this.tasks = this.storage.readFromFile();
        } catch (DukeException e) {
            this.ui.errorPrint(e);
        }
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

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
