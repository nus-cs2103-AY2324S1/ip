package duke;
import java.io.IOException;

import duke.command.Command;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.TaskList;

/**
 * MeowBot is the main class which runs the iP project in its main function
 * @since 2023-08-30
 */


public class MeowBot {
    private final String FILE_NAME;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * @param file indicates the location of the file where data is stored
     * @throws DukeException when generating the tasks back from the data file
     * @throws IOException when the data file cannot be found
     */

    public MeowBot(String file) throws DukeException, IOException {
        this.ui = new Ui();
        this.FILE_NAME = file;
        this.storage = new Storage(this.FILE_NAME);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("Meow???? I cant find your data");
            storage.createNewFile();
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            throw new DukeException("Meow???? I cannot write to your files :(");
        }

    }

    /**
     * Main method to run the MeowBot that will execute commands given
     */

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        ui.bye();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new MeowBot("src/main/data/meowbot.txt").run();
    }

}
