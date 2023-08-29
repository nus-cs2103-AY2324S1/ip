package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.ui.VerboseUi;
import java.io.File;
import java.util.List;

/**
 * The main chatbot.
 */
public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     * 
     * @param name Name of the bot.
     * @param projectName Directory name of the project folder.
     * @param mainFile The file that runs the main function.
     */
    public Duke(String name, String projectName, File mainFile) {
        this.storage = new Storage(projectName, mainFile);
        this.tasks = this.storage.loadTasks();
        this.ui = new VerboseUi(name);
    }

    /**
     * Runs the chatbot, receiving commands and terminating only on bye.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command cmd = Parser.parse(ui.readCommand());
                cmd.execute(tasks, ui, storage);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

}
