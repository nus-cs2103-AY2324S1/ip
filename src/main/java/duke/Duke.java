package duke;

import duke.command.Command;
import duke.management.NotesList;
import duke.management.Parser;
import duke.management.Storage;
import duke.management.TaskList;

/**
 * Duke program.
 */
public class Duke {
    private TaskList tasks;
    private NotesList notes;
    private Storage storage;

    /**
     * Duke Constructor.
     */
    public Duke() {
        this.storage = new Storage("./data", "/tasks.txt", "/notes.txt");
        try {
            this.tasks = new TaskList(storage.loadTasks());
            this.notes = new NotesList(storage.loadNotes());
        } catch (IndexOutOfBoundsException e) {
            this.tasks = new TaskList();
            this.notes = new NotesList();
        }
    }

    /**
     * Gets the response of the bot.
     *
     * @param input User's input.
     * @return Returns the bot's response in String representation.
     */
    public String getResponse(String input) {
        assert !input.isEmpty() : "Input cannot be empty!";
        try {
            Command command = Parser.parse(input);
            command.validateCommand();
            return command.execute(storage, tasks, notes);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
