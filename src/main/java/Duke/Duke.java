package Duke;

import java.io.IOException;
import java.io.File;


public class Duke {
    private Ui ui = new Ui();
    private TaskList tasks;

    public Duke() {
        System.out.println("Hello! I'm Auntie Maggie " +
                "\nWhat can I do for you?");
        Storage storage = new Storage("./data/duke.txt");
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
        ui.getInput(tasks, storage);
    }
    public static void main(String[] args) {
        new Duke();
    }
}

