package rat.io;

import static rat.io.RatPrinter.printWithLines;

import java.util.Scanner;

import rat.command.AddCommand;
import rat.command.CommandType;
import rat.command.DeleteCommand;
import rat.command.ExitCommand;
import rat.command.FindCommand;
import rat.command.MarkCommand;
import rat.command.UnmarkCommand;
import rat.tasks.RatTaskManager;

/**
 * This class encapsulates the input handling of Rat.
 *
 * @author Keagan
 */
public class RatInput {

    /**
     * The Scanner object used to read user input.
     * This Scanner should be initialised in main.
     */
    private Scanner sc;

    /**
     * The RatTaskManager object used to store and process the user's tasks.
     * This RatTaskManager should be initialised in main.
     */
    private RatTaskManager ratTaskManager;

    /**
     * Constructor for RatInput.
     *
     * @param sc             The Scanner object used to read user input.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     */
    public RatInput(Scanner sc, RatTaskManager ratTaskManager) {
        this.sc = sc;
        this.ratTaskManager = ratTaskManager;
    }

    /**
     * Parses and processes the user input based on a set of commands.
     * Creates and executes the corresponding command object.
     */
    public void handleInput() {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String command = inputArr[0];
            switch (command) {
            case "bye":
                new ExitCommand(this.ratTaskManager).execute();
                break;
            case "list":
                this.ratTaskManager.listItems();
                break;
            case "mark":
                new MarkCommand(this.ratTaskManager, inputArr).execute();
                break;
            case "unmark":
                new UnmarkCommand(this.ratTaskManager, inputArr).execute();
                break;
            case "todo":
                new AddCommand(this.ratTaskManager, input, CommandType.TODO).execute();
                break;
            case "deadline":
                new AddCommand(this.ratTaskManager, input, CommandType.DEADLINE).execute();
                break;
            case "event":
                new AddCommand(this.ratTaskManager, input, CommandType.EVENT).execute();
                break;
            case "delete":
                new DeleteCommand(this.ratTaskManager, input).execute();
                break;
            case "find":
                new FindCommand(this.ratTaskManager, input).execute();
                break;
            case "help":
                this.showCommands();
                break;
            default:
                printWithLines("Sorry, I don't understand what you mean by " + input);
            }
        }
    }

    /**
     * Prints the list of commands that the user can use.
     */
    public void showCommands() {
        String output = "Hello! I'm Rat, your personal task manager.\n"
                + "Here are the commands you can use:\n"
                + "\nhelp: show this list of commands\n"
                + "list: list all tasks\n"
                + "mark <index>: mark task at <index> as done\n"
                + "unmark <index>: mark task at <index> as not done\n"
                + "todo <name>: add a todo task with <name>\n"
                + "deadline <name> /by <deadline>: add a deadline task with <name> and <deadline>\n"
                + "event <name> /from <start> /to <end>: add an event task with <name>, <start> and <end>\n"
                + "delete <index>: delete task at <index>\n"
                + "delete all: delete all tasks\n"
                + "find <keyword>: find all tasks with <keyword>\n"
                + "bye: exit the program\n"
                + "\nbuilt by @keaganpzh";
        printWithLines(output);
    }

}
