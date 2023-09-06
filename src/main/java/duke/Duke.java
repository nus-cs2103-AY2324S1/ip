package duke;

import Exceptions.DukeArgumentException;
import Exceptions.DukeException;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the main program.
 */
public class Duke {
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static TaskList tasks;
    private static String directoryPath;
    private static String filePath;
    protected static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    enum Command { MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, BYE, LIST, INVALID, FIND }

    public static Command commandCheck(String command) {
        switch(command) {
        case "bye":
            return Command.BYE;
        case "unmark":
            return Command.UNMARK;
        case "mark":
            return Command.MARK;
        case "delete":
            return Command.DELETE;
        case "list":
            return Command.LIST;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "find":
            return Command.FIND;
        default:
            return Command.INVALID;
        }
    }
    public static void printCommand(Command command, String info)
            throws DukeException, IOException, DukeArgumentException {
        switch(command) {
        case BYE:
            ui.exit();
            break;
        case UNMARK:
            System.out.println(HORIZONTAL_LINE);
            tasks.unmark(info);
            System.out.println(HORIZONTAL_LINE);
            break;
        case MARK:
            System.out.println(HORIZONTAL_LINE);
            tasks.mark(info);
            System.out.println(HORIZONTAL_LINE);
            break;
        case DELETE:
            System.out.println(HORIZONTAL_LINE);
            tasks.deleteTask(info);
            System.out.println(HORIZONTAL_LINE);
            break;
        case LIST:
            System.out.println(HORIZONTAL_LINE);
            tasks.listTask();
            System.out.println(HORIZONTAL_LINE);
            break;
        case TODO:
            tasks.todoTask(info);
            break;
        case EVENT:
            tasks.eventTask(info);
            break;
        case DEADLINE:
            tasks.deadlineTask(info);
            break;
        case FIND:
            System.out.println(HORIZONTAL_LINE);
            tasks.findTask(info);
            System.out.println(HORIZONTAL_LINE);
            break;
        default:
            throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    /**
     * Constructor method for Duke.
     * @param directoryPath Directory to be created, where the save file resides.
     * @param filePath Relative path of the save file.
     */
    public Duke(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
        ui = new Ui();
        parser = new Parser();
    }
    public void run() {
        ui.greet();
        Scanner input = new Scanner(System.in);
        storage = new Storage(directoryPath, filePath);
        tasks = new TaskList(storage.loadTask(), storage);
        while (input.hasNext()) {
            String command = input.nextLine();
            String[] findCommand = parser.commandSplit(command);
            Command order = commandCheck(findCommand[0]);
            try {
                printCommand(order, command);
                storage.saveTask(tasks.getTaskArray());
            } catch (DukeException | DukeArgumentException message) {
                System.out.println(HORIZONTAL_LINE + "\n" + message.getMessage() + "\n" + HORIZONTAL_LINE);
            } catch (IOException e) {
                System.out.println("     Oh no, seems like something is not working.. We can't save your data.");
            }
        }
    }
    public static void main(String[] args) {
        new Duke("./data", "./data/duke.txt").run();
    }
}
