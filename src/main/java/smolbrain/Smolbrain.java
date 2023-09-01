package smolbrain;

import smolbrain.command.Command;
import smolbrain.exception.*;
import smolbrain.task.TaskList;

public class Smolbrain {

    public static boolean loading = true;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Smolbrain(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

    public void run() {

        ui.showWelcome();
        boolean isExit = false;

        while(!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidRangeException | MissingDescriptionException | MissingTimeException |
                     InvalidNumberException | InvalidDateTimeException | MissingKeywordException e) {
                ui.showError(e);
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Smolbrain("data.txt").run();
    }
}
