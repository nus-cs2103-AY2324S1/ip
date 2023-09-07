package duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } ca    tch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            try {
                if (Parser.isBye(command)) {
                    ui.showGoodbyeMessage();
                    storage.saveTasksToFile(tasks.getAllTasks());
                    break;
                } else if (Parser.isList(command)) {
                    ui.showTaskList(tasks.getAllTasks());
                } else if (Parser.isMarkDone(command)) {
                    int taskIndex = Parser.extractTaskIndex(command);
                    tasks.markAsDone(taskIndex);
                    ui.showTaskMarkedAsDone(tasks.getTask(taskIndex));
                    storage.saveTasksToFile(tasks.getAllTasks());
                } else if (Parser.isMarkNotDone(command)) {
                    int taskIndex = Parser.extractTaskIndex(command);
                    tasks.markAsNotDone(taskIndex);
                    ui.showTaskMarkedAsNotDone(tasks.getTask(taskIndex));
                    storage.saveTasksToFile(tasks.getAllTasks());
                } else if (Parser.isDelete(command)) {
                    int taskIndex = Parser.extractTaskIndex(command);
                    Task deletedTask = tasks.deleteTask(taskIndex);
                    ui.showTaskDeleted(deletedTask, tasks.getTotalTasks());
                    storage.saveTasksToFile(tasks.getAllTasks());
                } else {
                    Task newTask = Parser.parseTask(command);
                    tasks.addTask(newTask);
                    ui.showTaskAdded(newTask, tasks.getTotalTasks());
                    storage.saveTasksToFile(tasks.getAllTasks());
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/duke/duke.txt").run();
    }
}