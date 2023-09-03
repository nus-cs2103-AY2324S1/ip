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

    public void parseInputs() {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            handleInput(input);
        }
    }

    /**
     * Parses and processes the user input based on a set of commands.
     * Creates and executes the corresponding command object.
     */
    public String handleInput(String input) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        switch (command) {
        case "bye":
            return new ExitCommand(this.ratTaskManager).getResponse();
        case "list":
            return this.ratTaskManager.listItems();
        case "mark":
            return new MarkCommand(this.ratTaskManager, inputArr).getResponse();
        case "unmark":
            return new UnmarkCommand(this.ratTaskManager, inputArr).getResponse();
        case "todo":
            return new AddCommand(this.ratTaskManager, input, CommandType.TODO).getResponse();
        case "deadline":
            return new AddCommand(this.ratTaskManager, input, CommandType.DEADLINE).getResponse();
        case "event":
            return new AddCommand(this.ratTaskManager, input, CommandType.EVENT).getResponse();
        case "delete":
            return new DeleteCommand(this.ratTaskManager, input).getResponse();
        case "find":
            return new FindCommand(this.ratTaskManager, input).getResponse();
        case "help":
            return this.showCommands();
        default:
            return "Sorry, I don't understand what you mean by " + input;
        }
    }


    /**
     * Prints the list of commands that the user can use.
     */
    public String showCommands() {
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
        return output;
    }

}
