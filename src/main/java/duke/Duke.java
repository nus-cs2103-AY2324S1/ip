package duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();

    public Duke(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (DukeException var3) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

    public void run() {
        this.ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String command = scanner.nextLine();

            try {
                if (Parser.isBye(command)) {
                    this.ui.showGoodbyeMessage();
                    this.storage.saveTasksToFile(this.tasks.getAllTasks());
                    return;
                }

                if (Parser.isList(command)) {
                    this.ui.showTaskList(this.tasks.getAllTasks());
                } else {
                    int taskIndex;
                    if (Parser.isMarkDone(command)) {
                        taskIndex = Parser.extractTaskIndex(command);
                        this.tasks.markAsDone(taskIndex);
                        this.ui.showTaskMarkedAsDone(this.tasks.getTask(taskIndex));
                        this.storage.saveTasksToFile(this.tasks.getAllTasks());
                    } else if (Parser.isMarkNotDone(command)) {
                        taskIndex = Parser.extractTaskIndex(command);
                        this.tasks.markAsNotDone(taskIndex);
                        this.ui.showTaskMarkedAsNotDone(this.tasks.getTask(taskIndex));
                        this.storage.saveTasksToFile(this.tasks.getAllTasks());
                    } else if (Parser.isDelete(command)) {
                        taskIndex = Parser.extractTaskIndex(command);
                        Task deletedTask = this.tasks.deleteTask(taskIndex);
                        this.ui.showTaskDeleted(deletedTask, this.tasks.getTotalTasks());
                        this.storage.saveTasksToFile(this.tasks.getAllTasks());
                    } else {
                        Task newTask = Parser.parseTask(command);
                        this.tasks.addTask(newTask);
                        this.ui.showTaskAdded(newTask, this.tasks.getTotalTasks());
                        this.storage.saveTasksToFile(this.tasks.getAllTasks());
                    }
                }
            } catch (DukeException var5) {
                this.ui.showError(var5.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        (new Duke("./src/main/java/duke/duke.txt")).run();
    }
}