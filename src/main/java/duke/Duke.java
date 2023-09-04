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

    public Duke(String filePath) throws IOException, InvalidStartEndException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), ui);
    }

    public void run() {
        this.ui.greeting();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (parser.isGoodbye(input)) {
                    break;
                } else {
                    parser.parseInput(input, storage, tasks, ui);
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        this.ui.bye();
    }

    public static void main(String[] args) throws IOException, InvalidStartEndException {
        new Duke("./data/duke.txt").run();
    }
}
