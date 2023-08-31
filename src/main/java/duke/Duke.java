package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.data.task.Task;
import duke.data.TaskList;
import duke.data.exception.DukeException;
public class Duke {
    static final String FILE_NAME = "data/duke.txt";
    private final Storage storage;
    private TaskList tasklist;
    private final Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasklist = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasklist = new TaskList();
        }
    }

    public void run() {
        this.ui.greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String keyword = parser.getInstruction(input);
            try {
                switch (keyword) {
                case "bye": {
                    this.ui.exitBot();
                    return;
                }
                case "list": {
                    this.ui.printlist(tasklist.getTasklist());
                    break;
                }
                case "unmark": {
                    Task task = this.tasklist.unmarkTask(parser.getTaskNumber(input));
                    this.ui.unmark(task);
                    this.storage.rewriteFile(this.tasklist.getTasklist());
                    break;
                }
                case "mark": {
                    Task task = this.tasklist.markTask(parser.getTaskNumber(input));
                    this.ui.marked(task);
                    this.storage.rewriteFile(this.tasklist.getTasklist());
                    break;
                }
                case "delete": {
                    Task task = this.tasklist.deleteTask(parser.getTaskNumber(input));
                    this.ui.deleted(task, this.tasklist.getTasklist().size());
                    this.storage.rewriteFile(this.tasklist.getTasklist());
                    break;
                }
                case "todo": {
                    Task task = this.tasklist.addTodo(parser.getTodoDescription(input));
                    this.ui.addedToList(task, this.tasklist.getTasklist().size());
                    this.storage.appendToFile(task.saveString());
                    break;
                }
                case "deadline": {
                    Task task = this.tasklist.addDeadline(parser.getDeadlineDescription(input), parser.getBy(input));
                    this.ui.addedToList(task, this.tasklist.getTasklist().size());
                    this.storage.appendToFile(task.saveString());
                    break;
                }
                case "event": {
                    Task task = this.tasklist.addEvent(parser.getEventDescription(input), parser.getFrom(input),
                            parser.getTo(input));
                    this.ui.addedToList(task, this.tasklist.getTasklist().size());
                    this.storage.appendToFile(task.saveString());
                    break;
                }
                default: {
                    ui.botErrorMsg();
                }
                }

            } catch (DukeException e) {
                ui.printBotMessage(e.toString());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
