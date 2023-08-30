import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {

    private static TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String message = ui.readCommand(sc);
                ui.showLine();
                Command c = Parser.parse(message);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("data", "duke.txt");

        new Duke(path).run();
    }
}
