package dukepackage;

import toolpackage.Storage;
import toolpackage.TaskList;
import toolpackage.Ui;
import toolpackage.Parser;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);

            boolean isCreated = this.storage.createStorage();
            if (!isCreated) {
                tasks = new TaskList(storage.load());
            } else {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    private void run() {
        this.ui.showWelcome();
        Scanner inputs = new Scanner(System.in);

        boolean shouldContinue = true;
        while (shouldContinue) {
            String command = this.ui.readCommands(inputs);
            try {
                shouldContinue = Parser.parse(command, this.tasks, this.ui);
                this.storage.saveStorage(this.tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        
        inputs.close();
        this.ui.showBye();
    }
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
