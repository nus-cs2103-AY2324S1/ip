package duke;

import java.util.List;
import java.util.Scanner;

/**
 * User Interface for the Duke program.
 */
public class UserInterface {

    private static String TERMINATE_READER_STRING = "";

    /** Scanner to scan line input. */
    private Scanner sc = new Scanner(System.in);
    /** Storage to store the save file. */
    private Storage storage;
    /** List to keep track of tasks while program is running. */
    private StoreList list;

    /**
     * Creates a user interface from a Storage object and a StoreList object.
     *
     * @param storage The Storage object to be used by the user interface.
     * @param list The StoreList object to be used by the user interface.
     */
    public UserInterface(Storage storage, StoreList list) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Checks if there is another command to be read from the user, and returns true if there is and false otherwise.
     *
     * @return True if there is another command to be read from the user, and false otherwise.
     */
    public boolean hasNextCommand() {
        return sc.hasNext();
    }

    /**
     * Starts the user interface by reading the tasks from the storage file and adding them to the list of tasks.
     *
     * @throws DukeException If there is an error reading the tasks from the storage file.
     */
    public void start() throws DukeException {
        Task[] tasks = Parser.parseFile(this.storage);
        this.list.addTasks(List.of(tasks));
    }

    /**
     * Reads commands from the user and processes them until the user enters the "bye" command.
     */
    public void readCommandLine() {
        Reading:
        while (true) {
            String line = sc.nextLine();
            String response = parseLine(line);
            if (response.equals(TERMINATE_READER_STRING)) {
                display("Bye");
                break;
            }
            display(response);

        }
        sc.close();
    }

    /**
     * Saves the current state of the list of tasks to the storage file.
     */
    public void save() {
        String saveText = list.showSaveText();
        storage.write(saveText);
    }

    /**
     * Exits the user interface by saving the current state of the list of tasks to the storage file and closing any resources used by the user interface.
     */
    public void exit() {
        this.save();
        sc.close();
    }

    /**
     * Displays a string to a user in a formatted manner.
     *
     * @param s The string to be displayed to the user.
     */
    public static void display(String s) {
        System.out.println(wrapper(s));
    }

    private static String wrapper(String line) {
        String frame = "=====================";
        return String.format("%s\n%s", line, frame);
    }

    /**
     * Gives the response of parsing the line.
     *
     * @param line The line to be parsed
     * @return The response.
     */
    public String parseLine(String line) {
        if (line.length() == 0) {
            return "Err: No command input";
        }
        String[] instructions = line.split(" ", 2);
        Commands cmd = Commands.get(instructions[0]);
        String response;
        switch (cmd) {
        case bye:
            this.save();
            System.exit(0);
            return TERMINATE_READER_STRING;
        case list:
            response = list.toString();
            break;
        case deadline:
            // Fallthrough
        case todo:
            // Fallthrough
        case event:
            response = Parser.parseTaskAndAddToList(cmd, instructions, list);
            break;
        case mark:
            response = list.markDone(instructions[1]);
            break;
        case unmark:
            response = list.markUndone(instructions[1]);
            break;
        case delete:
            response = list.delete(instructions[1]);
            break;
        case find:
            response = Parser.findAll(instructions, list);
            break;
        case update:
            response = Parser.parseUpdate(instructions, list);
            break;
        default:
            response = "Err: Unknown command - " + instructions[0];
            break;
        }
        return response;
    }
}
