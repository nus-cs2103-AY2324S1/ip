package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.ui.VerboseUi;
import javafx.application.Platform;

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
     */
    public Duke(String name) {
        this.ui = new VerboseUi(name);
        this.storage = new Storage(this.ui);
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Returns default greeting message.
     *
     * @return Default greeting message.
     */
    public String greet() {
        ui.greet();
        return ui.flush();
    }

    /**
     * Generates response to command.
     *
     * @param input Command from user.
     * @return Response by chatbot.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command cmd = Parser.parse(input);
            cmd.execute(tasks, ui, storage);
            isExit = cmd.isExit();
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        if (isExit) {
            Platform.exit();
        }
        return ui.flush();
    }

    /**
     * Saves tasks to file.
     */
    public void save() {
        storage.save(tasks);
    }

}
