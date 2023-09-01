package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.data.task.Task;
import duke.data.TaskList;
import duke.data.exception.DukeException;
public class Duke {

    private final Storage storage;
    private TaskList tasklist;
    private final Ui ui;
    private final Parser parser;

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
                    this.ui.printList(tasklist.getTaskList());
                    break;
                }
                case "unmark": {
                    Task task = this.tasklist.unmarkTask(parser.getTaskNumber(input));
                    this.ui.printUnmarkedMessage(task);
                    this.storage.rewriteFile(this.tasklist.getTaskList());
                    break;
                }
                case "mark": {
                    Task task = this.tasklist.markTask(parser.getTaskNumber(input));
                    this.ui.printMarkedMessage(task);
                    this.storage.rewriteFile(this.tasklist.getTaskList());
                    break;
                }
                case "delete": {
                    Task task = this.tasklist.deleteTask(parser.getTaskNumber(input));
                    this.ui.printDeletedMessage(task, this.tasklist.getTaskList().size());
                    this.storage.rewriteFile(this.tasklist.getTaskList());
                    break;
                }
                case "todo": {
                    Task task = this.tasklist.addToDo(parser.getTodoDescription(input));
                    this.ui.printAddedToListMessage(task, this.tasklist.getTaskList().size());
                    this.storage.appendToFile(task.saveString());
                    break;
                }
                case "deadline": {
                    Task task = this.tasklist.addDeadline(parser.getDeadlineDescription(input), parser.getBy(input));
                    this.ui.printAddedToListMessage(task, this.tasklist.getTaskList().size());
                    this.storage.appendToFile(task.saveString());
                    break;
                }
                case "event": {
                    Task task = this.tasklist.addEvent(parser.getEventDescription(input), parser.getFrom(input),
                            parser.getTo(input));
                    this.ui.printAddedToListMessage(task, this.tasklist.getTaskList().size());
                    this.storage.appendToFile(task.saveString());
                    break;
                }
                case "find": {
                    String description = parser.getDescription(input);
                    ArrayList<Task> res = tasklist.searchTasks(description);
                    ui.printSearchList(res);
                    break;
                }
                default: {
                    ui.printBotErrorMsg();
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
