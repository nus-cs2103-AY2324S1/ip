import java.util.Scanner;
import ui.Ui;
import storage.Storage;
import taskList.TaskList;
import parser.Parser;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        storage.load(this.tasks);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        while(!false) {
            String command = sc.nextLine();
            int i = parser.parseCommand(command, tasks);
            if (i == 1) { break; }
        }
    }


    public static void main(String[] args) {
        Duke bob = new Duke();
        bob.ui.printStart();
        bob.run();
        bob.ui.printEnd();
    }
}
