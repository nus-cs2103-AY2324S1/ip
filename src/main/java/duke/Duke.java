package duke;

import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.tasks.Task;

import java.util.Scanner;

/**
 * The chatbot Duke.
 * Contains a Ui, Storage, TaskList and Parser object.
 */
public class Duke {
    private Ui ui;
    private Storage storage;

    private TaskList tasks;
    private Parser parser;

    /**
     * Instantiates a new Duke object.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
        this.parser = new Parser(ui, storage, tasks);
    }

    /**
     * Runs the chatbot until "bye" is entered.
     */
    public void run() {
        ui.introMessage();
        Scanner scan = new Scanner(System.in);
        String userinput = scan.nextLine();

        while (!userinput.equalsIgnoreCase("bye")) {
            try {
                parser.parse(userinput);
            } catch (DukeException e) {
                System.out.println(e);
            }
            userinput = scan.nextLine();
        }

        ui.byeMessage();
    }

    /**
     * Checks if a user input can be split.
     *
     * @param input    the input
     * @param splitter the regex for splitting
     * @throws DukeException cannot split exception
     */
    public static void canSplit(String input, String splitter) throws DukeException {
        if (input.split(splitter).length == 1) {
            throw new DukeException("☹ OOPS!!!");
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
