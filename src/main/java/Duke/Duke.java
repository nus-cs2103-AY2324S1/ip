package Duke;
import Ui.Ui;
import TaskList.TaskList;
import Storage.Storage;
import Parser.Parser;
import Command.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke() throws DukeException, FileNotFoundException {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("data", "tasks.txt", taskList);
        storage.loadTasks();
    }

    public void run() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        try {
            Duke bloopBot = new Duke();
            bloopBot.run();
        } catch (DukeException | FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}