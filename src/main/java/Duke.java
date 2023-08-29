import exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    private static Line line = new Line();
    private static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private Tasks tasks;
    private Ui ui;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        tasks = storage.load();
    }

    public void run() {
        ui.printGreetings();

        while (true) {
            try {
                String text = ui.readCommand();
                Command c = Parser.parse(text, false);

                // Empty or unknown command
                if (c == null) {
                    ui.showUnknownCommand();
                    continue;
                }

                // Execute command
                c.execute(this.tasks, this.ui, this.storage, false);

                // Check if is ExitCommand
                if (c.isExit()) {
                    break;
                }
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }

        }
    }
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }




}
