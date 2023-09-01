package duke;

import duke.Exceptions.LemonException;
import duke.datafile.Storage;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (LemonException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
                Parser.parseTasks(input, tasks, storage, ui);
            } catch (LemonException e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}