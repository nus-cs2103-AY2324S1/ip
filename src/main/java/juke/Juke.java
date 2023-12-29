package juke;

import javafx.application.Application;
import juke.commands.JukeExceptionCommand;
import juke.commons.exceptions.JukeInitialisationException;
import juke.commons.exceptions.parsers.JukeParseException;
import juke.commons.exceptions.storage.JukeStorageException;
import juke.responses.Response;
import juke.ui.Ui;

/**
 * Juke Virtual Assistant.
 */
public class Juke {
    /** Represents the max character width of any String printed to screen. */
    public static final int MAX_STRING_LENGTH = 40;

    /**
     * Runs the main event loop for Juke. This method invokes the inner {@code start()}
     * method to start the UI.
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
            new JukeExceptionCommand(ex).execute(Response.of());
        }
    }

    /**
     * Starts the UI for Juke.
     */
    private void start() {
        Application.launch(Ui.class);
    }
}
