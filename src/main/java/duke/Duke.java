package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidIndexException;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.Parser;
import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        ArrayList<Task> tasks = storage.load();
        this.tasks = new TaskList(tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.getUserInput();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | InvalidIndexException | EmptyTaskException | InvalidDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }


        }
    }

}
