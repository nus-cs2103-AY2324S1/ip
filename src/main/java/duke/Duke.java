package duke;

import duke.task.TaskList;
import duke.DukeException;
import duke.command.Command;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the Duke application, a simple task management system.
 * Duke allows users to manage their tasks through a command-line interface.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke instance.
     *
     * @param filePath The file path to the data file used for storing tasks.
     * @throws DukeException If there is an issue initializing Duke or loading tasks from the data file.
     */
    public Duke(String filePath) throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.loadIntoList(new TaskList());
    }

    /**
     * Runs the Duke application.
     *
     * @throws DukeException If there is an issue during the execution of the Duke application.
     */
    public void run() throws DukeException {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }
        this.ui.sendOff();
    }

    /**
     * Starts the Duke application.
     *
     * @param args The command-line arguments (not used in this application).
     * @throws DukeException If there is an issue during the execution of the Duke application.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}