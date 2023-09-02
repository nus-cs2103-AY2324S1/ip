import duke.command.ByeCommand;
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * A Constructor to create Duke Chat bot.
     */
    public Duke() {
        storage = new Storage();
        this.ui = new Ui();

        try {
            storage.checkFile();
            this.taskList = new TaskList(storage.readFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * method to run the code.
     */
    public void run() {
        ui.printGreet();

        while (true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();

            if (s == null) {
                s = in.nextLine();
            }

            try {
                Command c = Parser.parse(s);
                c.execute(taskList, ui, storage);
                if (c instanceof ByeCommand) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        storage.writeFile(taskList);
    }



    public static void main(String[] args) {
        new Duke().run();
    }
}
