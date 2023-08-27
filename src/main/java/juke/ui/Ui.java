package juke.ui;

import java.util.Scanner;

import juke.commands.JukeCommand;
import juke.commands.JukeExceptionCommand;
import juke.commands.JukeExitCommand;
import juke.core.JukeObject;
import juke.exceptions.JukeException;
import juke.tasks.TaskList;

/**
 * Orchestrates the operation of Juke by accepting commands and dispatching
 * them to the correct target methods.
 */
public class Ui extends JukeObject {
    /** Separator used by the virtual assistant to demarcate the start or end of a conversation */
    private static final String SEPARATOR = "\n-----------------------------------------------------------"
            + "-------------------------------\n";

    /**
     * Juke Logo made from ASCII Art. Credits go to:
     * <a href="https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20">...</a>
     */
    private static final String LOGO =
            "       __      __\n"
                    + "      / /_  __/ /_____\n"
                    + " __  / / / / / //_/ _ \\\n"
                    + "/ /_/ / /_/ / ,< /  __/\n"
                    + "\\____/\\__,_/_/|_|\\___/";

    /** Introductory statement used by the assistant when first starting the assistant. */
    private static final String INTRO = "What's up! My name is Juke (J|ava D|uke) and I will be your personal "
            + "assistant for today!\nHow may I assist you?";

    /** The Virtual Assistant's output CLI prompt. */
    private static final String JUKEOUTPUT = ">>> ";

    /** The user's input CLI prompt. */
    private static final String JUKEINPUT = "juke> ";

    /** Scanner instance used to capture user input. */
    private final Scanner jukeScanner;

    /** Instance of TaskList that handles all JukeTasks. */
    private final TaskList taskList;

    /**
     * Constructor for Ui.
     * @param jukeScanner {@code Scanner} object to read in user input
     * @param taskList {@code TaskList} object that handles all {@code JukeTasks}
     */
    public Ui(Scanner jukeScanner, TaskList taskList) {
        this.jukeScanner = jukeScanner;
        this.taskList = taskList;
    }

    /**
     * Method that begins the operation of the Assistant.
     */
    public void start() {
        this.printIntroduction();
        this.dispatch();
    }

    /**
     * Dispatches the command and acts on it.
     */
    private void dispatch() {
        JukeCommand action = null;

        do {
            try {
                // obtain user input
                System.out.print(Ui.JUKEINPUT);
                String inputCommand = this.jukeScanner.nextLine();

                // parse the action into a JukeAction object
                // storage object is not passed into actions as storage is under the control of
                // tasklist; external access to storage is not authorised
                action = JukeCommand.of(inputCommand, this.taskList);
                System.out.print(Ui.JUKEOUTPUT);

                // act on it, or any other future generated actions
                action.complete();

                System.out.print(Ui.SEPARATOR);
            } catch (JukeException ex) {
                // a bit of Pokémon exception handling over here, but it is necessary
                // to ensure that the UI obtains all possible exceptions to be thrown by the
                // program over the course of its runtime
                new JukeExceptionCommand(ex).complete();
                System.out.print(Ui.SEPARATOR);
            }
        } while (!(action instanceof JukeExitCommand));
    }

    /**
     * Prints out the Introduction statements.
     */
    private void printIntroduction() {
        String builder = Ui.LOGO
                + Ui.SEPARATOR
                + Ui.INTRO
                + Ui.SEPARATOR;

        System.out.print(builder);
    }
}
