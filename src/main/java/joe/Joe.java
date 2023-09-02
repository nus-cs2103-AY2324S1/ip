package joe;

import joe.commands.Command;
import joe.exceptions.JoeException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Joe {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private static final String FILE_NOT_FOUND_MESSAGE = "No saved task list was found.";

    public Joe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = storage.readTasks();
        } catch (FileNotFoundException e) {
            ui.print(FILE_NOT_FOUND_MESSAGE);
            tasks = new TaskList();
        } catch (IOException | JoeException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Joe("joe.txt").run();
    }

    public void run() {
        boolean isExit = false;

        ui.newLine();
        ui.greet();
        ui.newLine();

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JoeException e) {
                ui.print(e.getMessage());
            } finally {
                ui.newLine();
            }
        }
    }
}
