package duke.commands;

import duke.utils.*;
import duke.tasks.*;
import java.io.IOException;
import java.util.ArrayList;

public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param storage
     * @param tasks
     */
    @Override
    public void execute(Storage storage, ArrayList<Task> tasks) {
        try {
            storage.writeToFile(tasks);
            System.out.println("Bye. Hope to see you again soon!");
            Ui.printLine();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
