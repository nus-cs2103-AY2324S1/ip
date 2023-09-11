package duke;

import java.io.IOException;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
    private static boolean isFinished = false;
    private static Parser parser;
    private static Storage storage;
    private static TaskList listOfTasks;
    private static Ui ui;

    /**
     * Constructs a new instance of the chat bot.
     *
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        ui = new Ui();
        try {
            listOfTasks = new TaskList(storage);
            parser = new Parser(listOfTasks, ui);
            storage.loadTasks();
            //start();

        } catch (IOException e) {
            System.err.println(e);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public static String getResponse(String userInput)
            throws InvalidCommandException, EmptyDescriptionException, IOException {
        return parser.parseInput(userInput);
    }

    public static void setIsFinishedToTrue() {
        isFinished = true;
    }

    /**
     * Driver method for CLI version of Duke.
     *
     * @param args not used.
     */
    public static void main(String[] args) throws InvalidCommandException, EmptyDescriptionException, IOException {
        new Duke();
        ui.greet();
        String userInput = ui.getUserInput();
        while (!isFinished) {
            System.out.println(getResponse(userInput));
        }
    }
}
