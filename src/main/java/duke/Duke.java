package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    public Duke() {
        this("./data/duke.txt");
    }

    public void run(String input) {
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Parser executor = new Parser();
        executor.parse(this.tasks, input);

        try {
            new FileWriter("./data/duke.txt", false).close();
            this.storage.writeToFile(this.tasks);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        new Duke("./data/duke.txt").run();
//    }
}
