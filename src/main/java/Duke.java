import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }
    public void run() {
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Parser execute = new Parser();
        execute.parse(this.tasks);
        try {
            new FileWriter("./data/duke.txt", false).close();
            this.storage.writeToFile(this.tasks);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        Ui.line();
        Ui.sayBye();
        Ui.line();
    }
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
