package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String filePath;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        this.filePath = filePath;
        try {
            tasks = new TaskList(storage.load(storage.filePath));
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() throws DukeException, IOException {
        ui.textGenerator("Hello! I'm War Room.");
        ui.textGenerator("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String user_input = scanner.nextLine();
                Parser parser = new Parser(user_input);
                parser.parserChecker(tasks);
                storage.save(filePath, tasks.userData);
            }
        } catch (Exception e) {
            scanner.close();
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("src/main/data/duke.txt").run();
    }
}