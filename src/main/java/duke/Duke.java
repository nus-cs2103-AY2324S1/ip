package duke;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;

import java.io.IOException;

/**
 * Represent the ChatBot.
 * Run the ChatBot to intereact with it.
 * Contains a list of tasks, TaskList, a user interface Ui, and storage, Storage.
 */
public class Duke {
    private static final String filePathMain = "./duke.txt";
    private static final String filePathArchive = "./archive.txt";

    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;



    /**
     * Constructs a new ChatBot to chat with.
     * Default name of the ChatBot is Duke
     */
    public Duke() {
        this.ui = new Ui("Duke");
        storage = new Storage(filePathMain, filePathArchive);
        try {
            // initialise the mainfile and archivefile
            tasks = new TaskList(storage.loadFiles());
        } catch (IOException e) {
            ui.showLoadingError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the ChatBot.
     */
    public void run() {
        ui.showWelcome();
        new ListCommand().execute(tasks, ui, storage);
        while (ui.hasNextLine()) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * @param args Main method to run ChatBot.
     */
    public static void main(String[] args) {
		// filepath was here
        new Duke().run();
    }

    /**
     * Get a response from interacting with GUI
     * @param input the input user gives
     * @return String representing response of ChatBot
     */

	public String getResponse(String input) {
        StringBuilder br = new StringBuilder();
        try {
                br.append(input);
                br.append("\n");
                br.append(ui.getLine());
                Command c = Parser.parse(input);
                br.append(c.execute(tasks, ui, storage));
                return br.toString();
        } catch (DukeException e) {
            return e.getMessage();
        } finally {
            br.append(ui.getLine());
        }
	}



}
