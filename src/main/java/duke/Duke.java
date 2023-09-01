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
    protected static final String horizontalLine = "    ____________________________________________________________";
    enum TASK { MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, BYE, LIST, INVALID}

    public static TASK commandCheck(String command) {
        switch(command) {
            case "bye":
                return TASK.BYE;
            case "unmark":
                return TASK.UNMARK;
            case "mark":
                return TASK.MARK;
            case "delete":
                return TASK.DELETE;
            case "list":
                return TASK.LIST;
            case "todo":
                return TASK.TODO;
            case "deadline":
                return TASK.DEADLINE;
            case "event":
                return TASK.EVENT;
            default:
                return TASK.INVALID;
        }
    }
    public static void printCommand(TASK command, String info) throws DukeException, IOException, DukeArgumentException {
        switch(command) {
            case BYE:
                ui.exit();
                break;
            case UNMARK:
                System.out.println(horizontalLine);
                tasks.unmark(info);
                System.out.println(horizontalLine);
                break;
            case MARK:
                System.out.println(horizontalLine);
                tasks.mark(info);
                System.out.println(horizontalLine);
                break;
            case DELETE:
                System.out.println(horizontalLine);
                tasks.deleteTask(info);
                System.out.println(horizontalLine);
                break;
            case LIST:
                System.out.println(horizontalLine);
                tasks.listTask(info);
                System.out.println(horizontalLine);
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
            default:
                throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    /**
     * Constructor method for Duke.
     * @param filePath Relative path of the save file.
     * @param directoryPath Directory to be created, where the save file resides.
     */
    public Duke(String directoryPath, String filePath) {
        ui = new Ui();
        parser = new Parser();
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }
    public void run() {
        ui.greet();
        Scanner input = new Scanner(System.in);
        storage = new Storage(directoryPath, filePath);
        tasks = new TaskList(storage.loadTask(), storage);
        while (input.hasNext()) {
            String command = input.nextLine();
            String[] findCommand = parser.commandSplit(command);
            TASK order = commandCheck(findCommand[0]);
            try {
                printCommand(order, command);
                storage.saveTask(tasks.getTaskArray());
            } catch (DukeException | DukeArgumentException message) {
                System.out.println(horizontalLine + "\n" + message.getMessage() + "\n" + horizontalLine);
            } catch (IOException e) {
                System.out.println("     Oh no, seems like something is not working.. We can't save your data.");
            }
        }
    }
    public static void main(String[] args) {
        new Duke("./data", "./data/duke.txt").run();
    }
}
