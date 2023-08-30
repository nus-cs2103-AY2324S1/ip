package grumpygordon.ui;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.commands.ListCommand;
import grumpygordon.commands.Command;
import grumpygordon.parser.Parser;

import java.util.Scanner;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {

    /**
     * Separator for responses.
     */
    public static final String SEPARATOR = "    ___________________________________________________________________________________\n";

    /**
     * Indentation for responses.
     */
    private static final String INDENTATION = "     ";

    /**
     * Ascii-art for Grumpy Gordon.
     * Credits: https://patorjk.com/software/taag/#p=display&f=Big&t=GrumpyGordon
     */
    private static final String ASCII_ART =
                    INDENTATION + "    _____                                   _____               _              \n" +
                    INDENTATION + "   / ____|                                 / ____|             | |             \n" +
                    INDENTATION + "  | |  __ _ __ _   _ _ __ ___  _ __  _   _| |  __  ___  _ __ __| | ___  _ __   \n" +
                    INDENTATION + "  | | |_ | '__| | | | '_ ` _ \\| '_ \\| | | | | |_ |/ _ \\| '__/ _` |/ _ \\| '_ \\  \n" +
                    INDENTATION + "  | |__| | |  | |_| | | | | | | |_) | |_| | |__| | (_) | | | (_| | (_) | | | | \n" +
                    INDENTATION + "   \\_____|_|   \\__,_|_| |_| |_| .__/ \\__, |\\_____|\\___/|_|  \\__,_|\\___/|_| |_| \n" +
                    INDENTATION + "                              | |     __/ |                                    \n" +
                    INDENTATION + "                              |_|    |___/                                     \n";

    private static final String INTRO = INDENTATION + "Oi! I'm Grumpy Gordon. Why are you bothering me?\n";
    private static final String OUTRO = INDENTATION + "Bye. Hope to never see you again.\n";
    private final Scanner scanner;
    private final TaskList tasks;
    private final Storage storage;

    public Ui(TaskList tasks, Storage storage) {
        this.scanner = new Scanner(System.in);
        this.tasks = tasks;
        this.storage = storage;
    }

    public void run() {
        Command command = new ListCommand();
        String userInput;
        this.showIntroMessage();

        while (!command.isExit()) {
            try {
                userInput = this.scanner.nextLine();
                command = Parser.parseCommand(userInput, this.tasks);
                command.execute(tasks, this, storage);
            } catch (GrumpyGordonException e) {
                this.showErrorMessage(e);
            }
        }
        scanner.close();
    }
    public void showIntroMessage() {
        System.out.println(SEPARATOR + ASCII_ART + "\n" + INTRO + SEPARATOR);
    }
    public void showOutroMessage() {
        System.out.println(SEPARATOR + OUTRO + SEPARATOR);
    }
    public void showErrorMessage(GrumpyGordonException e) {
        System.out.println(SEPARATOR + INDENTATION + e.getMessage() + SEPARATOR);
    }
    public void showCommandMessage(String message) {
        System.out.println(SEPARATOR + message + SEPARATOR);
    }
}
