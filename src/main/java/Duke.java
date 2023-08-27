import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Scanner sc;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage();
    }

    public void run() {
        this.ui.printHello();
        try {
            this.storage.initialize();
            this.tasks = this.storage.readFile();
        } catch (DukeException e) {
            this.ui.printErrorMessage(e);
        }
        while (this.sc.hasNextLine()) {
            try {
                String input = this.sc.nextLine();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                if (input.split(" ")[0].equals("bye")) {
                    sc.close();
                    break;
                }
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
