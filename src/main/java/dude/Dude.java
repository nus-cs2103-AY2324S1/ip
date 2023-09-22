package dude;

import java.io.FileNotFoundException;

import dude.command.Command;
import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;

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

        try {
            taskList = storage.loadFromDisk();
            noteList = storage.loadNotesFromDisk();
        } catch (FileNotFoundException e) { // DudeException
            ui.showLoadingError();
            taskList = new TaskList();
            noteList = new NoteList();
        }
    }

    /**
     * Method to generate a response to user input.
     * Replace with completed method.
     */
    public String getResponse(String input) {
        System.out.println(input);
        Command c = Parser.parse(input);
        String output = c.execute(taskList, noteList, ui, storage);
        assert !output.trim().isEmpty() : "Dude output should not be empty";
        return output;
    }
}
