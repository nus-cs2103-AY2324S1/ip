package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidStartEndException;

//copy this into the actual duke.Duke file later!1!!!!
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.loadTasks(), ui);
    }

    public void run() {
        this.ui.greeting();
        Parser parser = new Parser(this.storage, this.tasks, this.ui);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (parser.isGoodbye(input)) {
                break;
            } else {
                parser.parseInput(input);
            }
        }
        this.ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
