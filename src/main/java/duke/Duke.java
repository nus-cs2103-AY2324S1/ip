package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.loadTasks(), storage, ui);
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
