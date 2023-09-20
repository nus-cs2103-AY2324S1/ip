package duke;

import duke.alias.AliasMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a simple task management application that allows users to keep track
 * of tasks, including to-dos, deadlines, and events.
 * Duke uses a command-based approach, where users enter text commands to
 * perform various operations on the task list.
 */
public class Duke {

    private static final String DEFAULT_DATA_FOLDER_PATH = "src/data";

    private final Storage storage;
    private TaskList items;
    private AliasMap aliases;
    private final Ui ui;

    private final Parser parser;


    /**
     * Constructs a Duke instance.
     *
     * @param folderPath The file path where tasks are stored.
     */
    public Duke(String folderPath) {
        ui = new Ui();
        storage = new Storage(folderPath);
        try {
            items = new TaskList(storage.loadData());
        } catch (DukeException c) {
            ui.showLoadingError();
            items = new TaskList();
        }
        try {
            aliases = new AliasMap(storage.loadAlias());
        } catch (DukeException c) {
            ui.showLoadingError();
            aliases = new AliasMap();
        }
        parser = new Parser(aliases);
    }

    public Duke() {
        this(DEFAULT_DATA_FOLDER_PATH);
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(items, aliases, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getGreetString() {
        return ui.greet();
    }
}
