package juke;

import javafx.application.Application;
import juke.commands.JukeExceptionCommand;
import juke.exceptions.JukeInitialisationException;
import juke.exceptions.parsers.JukeParseException;
import juke.exceptions.storage.JukeStorageException;
import juke.responses.Response;
import juke.ui.Ui;

/**
 * Juke Virtual Assistant
 */
public class Juke {
    /** Represents the longest string that can be printed. */
    public static final int MAX_STRING_LENGTH = 35;

    /**
     * Runs when this Java file is compiled and executed. This method is used to start {@code Juke}
     * and begin the request-response cycle.
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
    public void start() {
        Application.launch(Ui.class);
    }
}
