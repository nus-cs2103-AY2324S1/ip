package main.java.juke;

import main.java.juke.commands.JukeExceptionCommand;
import main.java.juke.exceptions.JukeInitialisationException;
import main.java.juke.exceptions.JukeParseException;
import main.java.juke.exceptions.storage.JukeStorageException;
import main.java.juke.storage.Storage;
import main.java.juke.tasks.TaskList;
import main.java.juke.ui.Ui;

import java.util.Scanner;

/**
 * Juke Virtual Assistant
 */
public class Juke {
    /** UI for Juke. */
    private final Ui ui;

    /** Storage for Juke. */
    private final Storage storage;

    /** Task List for Juke. */
    private final TaskList taskList;

    /**
     * Constructor for Juke.
     */
    public Juke() throws JukeInitialisationException, JukeStorageException {
        this.storage = Storage.of();
        this.taskList = TaskList.of(this.storage);
        this.ui = new Ui(new Scanner(System.in), this.storage, this.taskList);
    }

    /**
     * Main function that is run when this Java file is compiled and executed.
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        try {
            Juke jukeAssistant = new Juke();
            jukeAssistant.start();
        } catch (JukeInitialisationException | JukeStorageException | JukeParseException ex) {
            // program should not continue if it cannot initialise properly
            // or if there are issues with retrieving data from the datafile
            new JukeExceptionCommand(ex).complete();
        }
    }

    /**
     * Starts the orchestrator for Juke.
     */
    public void start() {
        this.ui.start();
    }
}
