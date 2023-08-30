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
 * duke.Duke
 *
 * CS2103T AY23/24 Semester 1
 * iP - Individual Project
 * duke.Duke Project
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

    private static void start() throws IOException,
            InvalidCommandException, EmptyDescriptionException {
        ui.greet();
        while (!isFinished) {
            parser.parseInput(ui.getUserInput());
        }
    }

    /**
     * Main method of the programme.
     * @param args not used here.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt");
    }
}

/**
 * String logo = " ____        _        \n"
 *                 + "|  _ \\ _   _| | _____ \n"
 *                 + "| | | | | | | |/ / _ \\\n"
 *                 + "| |_| | |_| |   <  __/\n"
 *                 + "|____/ \\__,_|_|\\_\\___|\n";
 *         System.out.println("Hello from\n" + logo);
 */
