import java.util.ArrayList;

import exception.DukeException;
import exception.EmptyTaskException;
import exception.InvalidDateTimeException;
import exception.InvalidIndexException;

import tasks.TaskList;
import tasks.Task;

import util.Ui;
import util.Storage;
import util.Parser;

import command.Command;
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
