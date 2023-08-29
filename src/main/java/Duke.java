
import seedu.*;

import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(null);
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean exit = false;
        while(!exit) {
            try {
                String input = this.ui.getUserCommand();

                Command c = Parser.parse(input, this.ui, this.storage, this.tasks);
                if(c.execute()) {
                    exit = true;
                    break;
                }
            } catch (Exception e) {
                this.ui.showError("Something wrong!" + e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        String filename = "src/save.txt";
        new Duke(filename).run();
    }
}
