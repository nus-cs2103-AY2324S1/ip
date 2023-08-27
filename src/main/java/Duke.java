import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Duke class contains the code for interacting
 * with Jarvis, a task manager bot.
 *
 * @author: Shishir
 **/
public class Duke {
    /** Contains the user inputted message which is split on the basis of ' ' **/
    private String[] split;
    /** Contains the list of all tasks. **/
    private ArrayList<Task> tasks;

    /** Contains the stored data for Duke Class. **/
    private Storage storage;

    /** Constructor for Duke Class. **/
    public Duke() {
        this.storage = new Storage();
    }

    /** Sets up the file to store data in. **/
    public void setup() {
        this.tasks = this.storage.readData();
    }

    /** The command to greet the user. **/
    public void greet() {
        this.indent();
        System.out.println("Greetings, I am Jarvis. How may I assist you today?");
        this.indent();
    }

    /** The command to provide the lines. **/
    public void indent() {
        System.out.println("_______________________________________________________________");
    }

    /** The command to add tasks into the tasks list. **/
    public void addCommand() {
        this.indent();
        System.out.println("Added the following task to the list.");
        System.out.println(this.tasks.size() + ") " + this.tasks.get(this.tasks.size() - 1).toString());
        System.out.println("You currently have " + this.tasks.size() + " tasks in your list.");
        this.indent();
        this.storage.writeData(this.tasks);
    }

    /** The command to mark tasks in the tasks list.
     * @param index The index to mark
     * **/
    public void markCommand(int index) {
        this.indent();
        System.out.println("The following task is marked as complete:");
        System.out.println(index + ") " + this.tasks.get(index - 1).toString());
        System.out.println("Is there anything else I can assist you with?");
        this.indent();
        this.storage.writeData(this.tasks);
    }

    /** The command to unmark tasks in the tasks list.
     * @param index The index to unmark
     * **/
    public void unmarkCommand(int index) {
        this.indent();
        System.out.println("The following task has been unmarked:");
        System.out.println(index + ") " + this.tasks.get(index - 1).toString());
        System.out.println("Is there anything else I can assist you with?");
        this.indent();
        this.storage.writeData(this.tasks);
    }

    /** The command to delete tasks from the tasks list.
     * @param index The index to delete
     * **/
    public void deleteCommand(int index) {
        this.indent();
        System.out.println("The following task has been removed:");
        System.out.println(index + ") " + this.tasks.get(index - 1).toString());
        System.out.println("Is there anything else I can assist you with?");
        this.indent();
    }

    /** The command to list all tasks from the tasks list. **/
    public void list() {
        this.indent();
        if (this.tasks.size() == 0) {
            System.out.println("Your task list is empty! Add a task to view it here.");
        } else {
            System.out.println("Tasks displayed. Your guidance is requested.");
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ") " + this.tasks.get(i).toString());
        }
        this.indent();
    }

    /** The function to mark/unmark tasks
     * @param flag If flag is true, it is marked, otherwise unmarked.
     * @throws DukeException Throws error on incorrect input.
     *  **/
    public void mark(boolean flag) throws DukeException {
        // Check if mark is receiving any input or receiving extra input
        if (this.split.length != 2) {
            throw new DukeException("Please enter a valid mark command!");
        }

        // Check if mark is not receiving a number.
        if (this.split[1].isBlank() || !Character.isDigit(this.split[1].charAt(0))) {
            throw new DukeException("I cannot mark a character! Please enter a number.");
        }

        int index = Character.getNumericValue(this.split[1].charAt(0));

        // Check if index is invalid or the task is already marked
        if (index <= 0 || this.tasks.size() <= index || this.tasks.get(index - 1).isCompleted() == flag) {
            throw new DukeException("The task you are trying to mark either doesnt exist, or is already marked");
        }

        if (flag) {
            this.tasks.get(index - 1).completeTask();
            markCommand(index);
        } else {
            this.tasks.get(index - 1).revertTask();
            unmarkCommand(index);
        }
    }

    /** The function to delete tasks.
     * @throws DukeException Throws error on incorrect input.
     *  **/
    public void delete() throws DukeException {
        // Check if mark is receiving any input or receiving extra input
        if (this.split.length != 2) {
            throw new DukeException("Please enter a valid delete command!");
        }

        // Check if mark is not receiving a number.
        if (this.split[1].isBlank() || !Character.isDigit(this.split[1].charAt(0))) {
            throw new DukeException("I cannot delete a character index! Please enter a number.");
        }

        int index = Character.getNumericValue(this.split[1].charAt(0));

        // Check if index is invalid or the task exists.
        if (index <= 0 || this.tasks.size() <= index) {
            throw new DukeException("The task you are trying to delete either doesnt exist, or is already marked");
        }

        deleteCommand(index);
        this.tasks.remove(index - 1);
        this.storage.writeData(this.tasks);
    }

    /** The function to add todo tasks.
     * @throws DukeException Throws error on incorrect input.
     *  **/
    public void todo() throws DukeException {

        // Check if task is blank.
        if (this.split.length <= 1 || this.split[1].isBlank()) {
            throw new DukeException("Please enter a valid task.");
        }

        this.tasks.add(new Todo(this.split[1]));
        this.addCommand();
    }

    /** The function to add deadline tasks.
     * @throws DukeException Throws error on incorrect input.
     *  **/
    public void deadline() throws DukeException {

        if (this.split.length <= 1 || !this.split[1].contains(" /by ")) {
            throw new DukeException("Please enter a valid task and deadline");
        }

        String[] task = this.split[1].split(" /by ", 2);

        if (task.length <= 1 || task[1].isBlank()) {
            throw new DukeException("Please enter a valid task and deadline.");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            this.tasks.add(new Deadline(task[0], LocalDateTime.parse(task[1], formatter)));
            this.addCommand();
        } catch (DateTimeParseException exp) {
            this.indent();
            System.out.println("Please enter the date & time in a valid format! (DD/MM/YY HHMM)");
            this.indent();
        }
    }

    /** The function to add event tasks.
     * @throws DukeException Throws error on incorrect input.
     *  **/
    public void event() throws DukeException {

        // Check if /from is present
        if (this.split.length <= 1 || !this.split[1].contains(" /from ")) {
            throw new DukeException("There is no task and/or from command present. Please try again.");
        }

        String[] task = this.split[1].split(" /from ", 2);

        // Check if task entered is empty
        if (task.length <= 1 || task[1].isBlank()) {
            throw new DukeException("Please enter a valid task.");
        }

        // Check if /to is present
        if (!task[1].contains(" /to ")) {
            throw new DukeException("There is no /to command present. Please try again.");
        }

        String[] to = task[1].split(" /to ", 2);

        if (to.length <= 1 || to[1].isBlank() || to[0].isBlank()) {
            throw new DukeException("There enter valid to & from dates");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            this.tasks.add(new Event(task[0], LocalDateTime.parse(to[0], formatter), LocalDateTime.parse(to[1], formatter)));
            this.addCommand();
        } catch (DateTimeParseException exc) {
            System.out.println("Enter the date & time in a valid format! (DD/MM/YY HHMM)");
        }

    }

    /** The exit command when user types "bye" **/
    public void exit() {
        this.indent();
        System.out.println("I shall now take my leave. Farewell!");
        this.indent();
    }

    /** The function where user interacts with Jarvis using Scanner. **/
    public void interact() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                this.split = input.nextLine().split(" ", 2);
                switch(this.split[0]) {
                    case "bye":
                        break;
                    case "list":
                        this.list();
                        break;
                    case "mark":
                        this.mark(true);
                        break;
                    case "unmark":
                        this.mark(false);
                        break;
                    case "todo":
                        this.todo();
                        break;
                    case "deadline":
                        this.deadline();
                        break;
                    case "event":
                        this.event();
                        break;
                    case "delete":
                        this.delete();
                        break;
                    default:
                        throw new DukeException("I'm sorry, I couldn't understand that. Please try again!");
                }
                if (this.split[0].equals("bye")) {
                    break;
                }
            } catch (DukeException exc) {
                this.indent();
                System.out.println(exc.toString());
                this.indent();
            }
        }
    }

    /** The main function where Jarvis is initialised.
     * @param args Input args.
     **/
    public static void main(String[] args) {
        // Create a scanner object to read input
        Duke bot = new Duke();
        bot.setup();
        bot.greet();
        bot.interact();
        bot.exit();
    }
}
