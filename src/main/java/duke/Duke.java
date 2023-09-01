package duke;

import duke.command.Command;
import duke.exception.InvalidIndexException;
import duke.exception.NoSuchCommandException;
import duke.task.TaskList;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.nio.file.Path;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    public Duke(Path filePath) {

        this.ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.processFile());
    }

    public void run() {

        ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(Ui.showLine()); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoSuchCommandException e) {
                System.out.println(e);
            } catch (InvalidIndexException e) {
                System.out.println(e);
            } catch (NumberFormatException | StringIndexOutOfBoundsException | DateTimeException e) {

                System.out.println(Ui.showLine());
                System.out.println("\tPlease enter a proper date.");
                System.out.println("\t" + e.getMessage());
                System.out.println();
                System.out.println(Ui.showLine());
            }
        }
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt")).run();
    }
}
