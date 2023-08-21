package rat.inputs;

import java.util.Scanner;
import rat.storage.*;
import rat.print.RatPrinter;

/**
 * This class encapsulates the input handling of Rat.
 * @author Keagan
 */
public class RatInput {

    /**
     * The Scanner object used to read user input.
     * This Scanner should be initialised in main.
     */
    Scanner sc;

    /**
     * The RatStorage object used to store and process the user's tasks.
     * This RatStorage should be initialised in main.
     */
    RatStorage ratStorage;

    /**
     * Constructor for RatInput.
     * @param sc The Scanner object used to read user input.
     * @param ratStorage The RatStorage object used to store and process the user's tasks.
     */
    public RatInput(Scanner sc, RatStorage ratStorage) {
        this.sc = sc;
        this.ratStorage = ratStorage;
    }

    /**
     * Parses and processes the user input based on a set of commands.
     * Handles system exit.
     */
    public void handleInput() {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String command = inputArr[0];
            switch (command) {
                case "bye":
                    RatPrinter.printExit();
                    System.exit(0);
                case "list":
                    this.ratStorage.listItems();
                    break;
                case "mark":
                    this.handleMark(inputArr);
                    break;
                case "unmark":
                    this.handleUnmark(inputArr);
                    break;
                case "todo":
                    this.handleToDo(input);
                    break;
                case "deadline":
                    this.handleDeadline(input);
                    break;
                case "event":
                    this.handleEvent(input);
                    break;
                case "delete":
                    this.handleDelete(input);
                    break;
                case "help":
                    this.showCommands();
                    break;
                default:
                    RatPrinter.printWithLines("Sorry, I don't understand what you mean by " + input);
            }
        }
    }

    /**
     * Handles the action of marking a task as done.
     * Parses user input after the "mark" command.
     * @param inputs Array of Strings resulting from splitting the remaining user input by spaces.
     */
    protected void handleMark(String[] inputs) {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratStorage.markItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            RatPrinter.printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            RatPrinter.printWithLines(" \"mark\" command must be followed by a number");
        }
    }

    /**
     * Handles the action of marking a task as not done.
     * Parses user input after the "unmark" command.
     * @param inputs Array of Strings resulting from splitting the remaining user input by spaces.
     */
    protected void handleUnmark(String[] inputs) {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratStorage.unmarkItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            RatPrinter.printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            RatPrinter.printWithLines(" \"unmark\" command must be followed by a number");
        }
    }

    /**
     * Handles the action of creating a new ToDo task.
     * Parses user input after the "todo" command.
     * @param params The remaining user input after the "todo" command.
     */
    protected void handleToDo(String params) {
        try {
            params = params.substring(5);
            this.ratStorage.addToDo(params);
        } catch (StringIndexOutOfBoundsException e) {
            RatPrinter.printWithLines("To Do name cannot be empty");
        }
    }

    /**
     * Handles the action of creating a new Deadline task.
     * Parses user input after the "deadline" command.
     * @param params The remaining user input after the "deadline" command.
     */
    protected void handleDeadline(String params) {
        try {
            params = params.substring(9);
            String[] paramsArr = params.split(" /by ");
            String name = paramsArr[0];
            String deadline = paramsArr[1];
            this.ratStorage.addDeadline(deadline, name);
        } catch (StringIndexOutOfBoundsException e) {
            RatPrinter.printWithLines("Deadline name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            RatPrinter.printWithLines("Invalid deadline format. Please use \"deadline <name> /by <deadline>\"");
        }
    }

    /**
     * Handles the action of creating a new Event task.
     * Parses user input after the "event" command.
     * @param params The remaining user input after the "event" command.
     */
    protected void handleEvent(String params) {
        try {
            params = params.substring(6);
            String eventName = params.split(" /from ")[0];
            if (eventName.isEmpty()) {
                RatPrinter.printWithLines("Event name cannot be empty");
            }
            String[] time = params.split(" /from ")[1].split(" /to ");
            String startTime = time[0];
            String endTime = time[1];
            this.ratStorage.addEvent(startTime, endTime, eventName);
        } catch (StringIndexOutOfBoundsException e) {
            RatPrinter.printWithLines("Event name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            RatPrinter.printWithLines("Invalid event format. Please use \"event <name> /from <start> /to <end>\"");
        }
    }

    /**
     * Handles the action of deleting task(s).
     * Parses user input after the "delete" command.
     * @param params The remaining user input after the "delete" command.
     */
    protected void handleDelete(String params) {
        try {
            params = params.substring(7);
            if (params.equals("all")) {
                this.ratStorage.deleteAll();
            } else {
                int index = Integer.parseInt(params);
                this.ratStorage.deleteItem(index);
            }
        } catch (IndexOutOfBoundsException e) {
            RatPrinter.printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            RatPrinter.printWithLines(" \"delete\" command must be followed by a number");
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
                + "bye: exit the program\n"
                + "\nbuilt by @keaganpzh";
        RatPrinter.printWithLines(output);
    }

}
