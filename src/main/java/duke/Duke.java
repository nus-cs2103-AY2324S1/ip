package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        Ui.start();
        Scanner scan = new Scanner(System.in);
        String input = Ui.getInput(scan);
        while (!input.equals("bye")) {
            Parser.parseInput(input, this.tasks, this.storage);
            input = Ui.getInput(scan);
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}