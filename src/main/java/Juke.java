package main.java;

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
    public Juke() {
        this.jukeOrchestrator = JukeOrchestrator.of(new Scanner(System.in));
    }

    /**
     * Main function that is run when this Java file is compiled and executed.
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        Juke jukeAssistant = new Juke();
        jukeAssistant.start();
    }

    /**
     * Starts the orchestrator for Juke.
     */
    public void start() {
        this.jukeOrchestrator.start();
    }
}
