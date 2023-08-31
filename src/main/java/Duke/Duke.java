package Duke;

import Duke.exceptions.DukeException;
import Duke.fileHandler.Storage;
import Duke.parsers.InputParser;
import Duke.tasks.Task;
import Duke.ui.Ui;

import java.util.ArrayList;
public class Duke {


    private static ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        ui.printGreeting();
        storage = new Storage(filePath);
        try {
            tasks = storage.readTasks();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new ArrayList<>();
        }
    }

    public void run() {

        InputParser.getUserInputs(tasks);
        ui.printEnding();

    }
    public static void main(String[] args) {

        new Duke("tasks.txt").run();

        }
    }

