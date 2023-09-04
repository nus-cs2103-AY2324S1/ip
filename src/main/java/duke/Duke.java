package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    final static String LINE = "────────────────────────────────────────────────────";

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Contains the logic flow of the Duke program.
     */
    public void run() {
        try {
            Parser parser = new Parser();

            Ui.greet();
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            parser.parse(input, tasks);

            while (!input.equals("bye")) {
                input = userInput.nextLine();
                parser.parse(input, tasks);
            }

            try{
                storage.writeTasksToFile(tasks.getTasks());
            } catch (IOException e) {
                throw new DukeException("Cannot write tasks into file!");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n" + LINE);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }
}