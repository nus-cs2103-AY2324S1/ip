package duke;

import java.io.IOException;

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
        this.storage = new Storage("./data");
        try {
            this.tasks = new TaskList(storage.loadData());
            this.notes = new NotesList(storage.loadNotes());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Saves tasks and notes into their respective data files.
     */
    public void saveToFile() {
        try {
            this.storage.writeTasksToFile(this.tasks.getTasks());
            this.storage.writeNotesToFile(this.notes.getNotes());
        } catch (IOException e) {
            throw new DukeException("Cannot write tasks into file!");
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
        Command command = Parser.parse(input);
        try {
            return command.execute(tasks, notes);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
