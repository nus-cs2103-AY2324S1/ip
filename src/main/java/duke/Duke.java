package duke;

import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Duke is a simple task management chatbot that allows users to manager their tasks.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a Duke object.
     * @param filePath The path to the file where the tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        this.ui.printGreeting();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            Parser parser = new Parser(input);
            Command keyword = parser.getCommand();

            try {
                switch (keyword) {
                case BYE: {
                    this.ui.printFarewell();
                    return;
                }
                case LIST: {
                    this.ui.printTasks(this.tasks);
                    break;
                }
                case UNMARK: {
                    Task task = this.tasks.unmarkTask(parser.getTaskNumber());
                    this.ui.printBotMessage("OK, I've marked this task as not done yet:\n\t\t" + task);
                    break;
                }
                case MARK: {
                    Task task = this.tasks.markTask(parser.getTaskNumber());
                    this.ui.printBotMessage("Nice! I've marked this task as done:\n\t" + task);
                    break;
                }
                case TODO: {
                    Todo todo = this.tasks.addTodo(parser.getContentForTodo());
                    this.ui.printBotMessage("Got it. I've added this task:\n\t" + todo
                            + "\nNow you have " + this.tasks.getSize() + " tasks in the list.");
                    break;
                }
                case DEADLINE: {
                    String[] inputList = parser.getContentForDeadline();
                    Deadline deadline = this.tasks.addDeadline(inputList[0], parser.parseDateTime(inputList[1]));
                    this.ui.printBotMessage("Got it. I've added this task:\n\t" + deadline
                            + "\nNow you have " + this.tasks.getSize() + " tasks in the list.");
                    break;
                }
                case EVENT: {
                    String[] inputList = parser.getContentForEvent();
                    Event event = this.tasks.addEvent(inputList[0], parser.parseDateTime(inputList[1]),
                            parser.parseDateTime(inputList[2]));
                    this.ui.printBotMessage("Got it. I've added this task:\n\t" + event
                            + "\nNow you have " + this.tasks.getSize() + " tasks in the list.");
                    break;
                }
                case DELETE: {
                    Task task = this.tasks.deleteTask(parser.getTaskNumber());
                    this.ui.printBotMessage("Noted. I've removed this task:\n\t" + task
                            + "\nNow you have " + this.tasks.getSize() + " tasks in the list.");
                    break;
                }
                case FIND: {
                    TaskList filteredTasks = this.tasks.filterTasks(parser.getSearchTerm());
                    this.ui.printTasks(filteredTasks);
                    break;
                }
                default:
                    throw new DukeInvalidCommandException();
                }
                this.storage.saveTasks(this.tasks.getTasks());
            } catch (DukeException e) {
                this.ui.printBotMessage(e.toString());
            }
        }
    }

    /**
     * Runs the Duke chatbot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
