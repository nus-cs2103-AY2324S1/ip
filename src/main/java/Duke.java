import duke.*;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showIntro();

        while (true) {

            String command = scanner.nextLine();

            if (command.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The description cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            // exits program
            } else if (command.equalsIgnoreCase("bye")) {
                ui.showBye();
                break;
            } else {
                Parser.parse(command, tasks, storage, ui);
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
