package main.java.juke.core;

import main.java.juke.actions.JukeExceptionAction;
import main.java.juke.exceptions.JukeInitialisationException;
import main.java.juke.exceptions.JukeParseException;
import main.java.juke.exceptions.storage.JukeStorageException;

import java.util.Scanner;

/**
 * Juke Virtual Assistant
 */
public class Juke {
    /** Orchestrator for Juke. */
    private final JukeOrchestrator jukeOrchestrator;

    /**
     * Constructor for Juke.
     */
    public Juke() throws JukeInitialisationException, JukeStorageException {
        this.jukeOrchestrator = JukeOrchestrator.of(new Scanner(System.in));
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
            new JukeExceptionAction(ex).complete();
        }
    }

    /**
     * Starts the orchestrator for Juke.
     */
    public void start() {
        this.jukeOrchestrator.start();
    }
}
