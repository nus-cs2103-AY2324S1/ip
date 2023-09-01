package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

/**
 * Represents the bot SeeWhyAre.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Duke {

    // HORIZONTAL_LINE
    public static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    private static Parser parser;
    private static Storage storage;
    private static TaskList listOfTasks;
    private static Ui ui;
    public static boolean isFinished = false;

    /**
     * Constructs a new instance of the chat bot.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            listOfTasks = new TaskList(storage);
            parser = new Parser(listOfTasks, ui);
            storage.loadTasks();
            start();

        } catch (IOException e) {
            System.err.println(e);
        } catch (InvalidDateException | InvalidCommandException | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the SeeWhyAre bot.
     *
     * @throws IOException if there are issues with file handling.
     * @throws InvalidCommandException if there are invalid commands entered by users.
     * @throws EmptyDescriptionException if users attempt to create tasks with empty descriptions.
     */
    private static void start() throws IOException,
            InvalidCommandException, EmptyDescriptionException {
        ui.greet();
        while (!isFinished) {
            parser.parseInput(ui.getUserInput());
        }
    }

    /**
     * Driver method for CLI version of Duke.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt");
    }
}

