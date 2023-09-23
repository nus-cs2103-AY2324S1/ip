package dude;

import dude.command.Command;
import dude.exception.DudeException;
import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;
import dude.util.Parser;
import dude.util.Storage;

/**
 * Dude is a programme that allows users to manage their tasks.
 */

public class Dude {

    private static final String DEFAULT_FILEPATH = "./data/dude.txt";
    private TaskList taskList;
    private NoteList noteList;
    private Storage storage;
    private Ui ui;

    public Dude() {
        this.storage = new Storage(DEFAULT_FILEPATH);
    }

    /**
     * Constructor for Dude that takes in a file path to storage file.
     * @param filePath Path to storage file.
     */
    public Dude(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        taskList = storage.loadFromDisk();
        noteList = storage.loadNotesFromDisk();
    }

    /**
     * Generates a response from Dude to user input.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(taskList, noteList, ui, storage);
        } catch (DudeException e) {
            output = e.getMessage();
        }
        assert !output.trim().isEmpty() : "Dude output should not be empty";
        return output;
    }
}
