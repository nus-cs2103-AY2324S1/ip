package rat.io;

import java.util.Scanner;

import rat.tasks.*;

import static rat.io.RatPrinter.*;

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
     * Handles system exit.
     */
    public void handleInput() {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String command = inputArr[0];
            switch (command) {
            case "bye":
                this.ratTaskManager.save();
                printExit();
                System.exit(0);
            case "list":
                this.ratTaskManager.listItems();
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
                printWithLines("Sorry, I don't understand what you mean by " + input);
            }
        }
    }

    /**
     * Handles the action of marking a task as done.
     * Parses user input after the "mark" command.
     *
     * @param inputs Array of Strings resulting from splitting the remaining user input by spaces.
     */
    protected void handleMark(String[] inputs) {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratTaskManager.markItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            printWithLines(" \"mark\" command must be followed by a number");
        }
    }

    /**
     * Handles the action of marking a task as not done.
     * Parses user input after the "unmark" command.
     *
     * @param inputs Array of Strings resulting from splitting the remaining user input by spaces.
     */
    protected void handleUnmark(String[] inputs) {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratTaskManager.unmarkItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            printWithLines(" \"unmark\" command must be followed by a number");
        }
    }

    /**
     * Handles the action of creating a new ToDo task.
     * Parses user input after the "todo" command.
     *
     * @param params The remaining user input after the "todo" command.
     */
    protected void handleToDo(String params) {
        try {
            params = params.substring(5);
            this.ratTaskManager.addToDo(params);
        } catch (StringIndexOutOfBoundsException e) {
            printWithLines("To Do name cannot be empty");
        }
    }

    /**
     * Handles the action of creating a new Deadline task.
     * Parses user input after the "deadline" command.
     *
     * @param params The remaining user input after the "deadline" command.
     */
    protected void handleDeadline(String params) {
        try {
            params = params.substring(9);
            String[] paramsArr = params.split(" /by ");
            String name = paramsArr[0];
            String deadline = paramsArr[1];
            this.ratTaskManager.addDeadline(deadline, name);
        } catch (StringIndexOutOfBoundsException e) {
            printWithLines("Deadline name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithLines("Invalid deadline format. Please use \"deadline <name> /by <deadline>\"");
        }
    }

    /**
     * Handles the action of creating a new Event task.
     * Parses user input after the "event" command.
     *
     * @param params The remaining user input after the "event" command.
     */
    protected void handleEvent(String params) {
        try {
            params = params.substring(6);
            String eventName = params.split(" /from ")[0];
            if (eventName.isEmpty()) {
                printWithLines("Event name cannot be empty");
            }
            String[] time = params.split(" /from ")[1].split(" /to ");
            String startTime = time[0];
            String endTime = time[1];
            this.ratTaskManager.addEvent(startTime, endTime, eventName);
        } catch (StringIndexOutOfBoundsException e) {
            printWithLines("Event name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithLines("Invalid event format. Please use \"event <name> /from <start> /to <end>\"");
        }
    }

    /**
     * Handles the action of deleting task(s).
     * Parses user input after the "delete" command.
     *
     * @param params The remaining user input after the "delete" command.
     */
    protected void handleDelete(String params) {
        try {
            params = params.substring(7);
            if (params.equals("all")) {
                this.ratTaskManager.deleteAll();
            } else {
                int index = Integer.parseInt(params);
                this.ratTaskManager.deleteItem(index);
            }
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            printWithLines(" \"delete\" command must be followed by a number");
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
        printWithLines(output);
    }

}
