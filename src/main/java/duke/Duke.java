package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.ui.VerboseUi;

/**
 * The main chatbot.
 */
public class Duke {

    private String name;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke.
     *
     * @param name Name of the bot.
     */
    public Duke(String name) {
        this.name = name;
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
        return String.format("Hello! I'm %s\nWhat can I do for you?", name);
    }

    /**
     * Generates response to command.
     *
     * @param input Command from user.
     * @return Response by chatbot.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            cmd.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.print(e.getMessage());
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
