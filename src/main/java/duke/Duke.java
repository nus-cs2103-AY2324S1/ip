package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     */
    public void run() {

        Ui.greetUser();
        Scanner scanObj = new Scanner(System.in);
        while(true) {
            String nextLine = scanObj.nextLine();
            Parser.parseCommands(nextLine, this.tasks, this.storage);
        }
    }

    /**
     * Creates a new instance of CringeBot.
     *
     * @param args Command line args.
     */
    public static void main(String[] args) {
        new Duke("./data/data.ser").run();
    }
}
