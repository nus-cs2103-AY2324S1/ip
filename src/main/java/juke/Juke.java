package juke;

import java.util.Scanner;

import juke.commands.JukeExceptionCommand;
import juke.exceptions.JukeInitialisationException;
import juke.exceptions.JukeParseException;
import juke.exceptions.storage.JukeStorageException;
import juke.storage.Storage;
import juke.tasks.TaskList;
import juke.ui.Ui;

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
        } catch (JukeInitialisationException | JukeStorageException
                 | JukeParseException ex) {
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
