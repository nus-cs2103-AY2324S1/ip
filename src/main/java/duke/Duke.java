package duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
/**
 * The Duke class represents a chatbot application.
 * It allows users to add, mark as done, mark as not done, delete, and list 3 different type of tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();

        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        int taskIndex;
        String command = input;
        StringBuilder response = new StringBuilder(); // Create a StringBuilder to accumulate responses

        try {
            if (Parser.isBye(command)) {
                response.append(ui.showGoodbyeMessage());
            } else if (Parser.isList(command)) {
                response.append(ui.showTaskList(this.tasks.getAllTasks()));
            } else if (Parser.isHi(command)) {
                response.append(ui.showHiMessage());
            } else if (Parser.isMarkDone(command)) {
                taskIndex = Parser.extractTaskIndex(command);
                Task task = this.tasks.getTask(taskIndex);

                if (task.isDone()) {
                    response.append(ui.showMarkMarkedTaskMessage());
                } else {
                    this.tasks.markAsDone(taskIndex);
                    response.append(ui.showTaskMarkedAsDone(task));
                }
            } else if (Parser.isMarkNotDone(command)) {
                taskIndex = Parser.extractTaskIndex(command);
                Task task = this.tasks.getTask(taskIndex);

                if (!task.isDone()) {
                    response.append(ui.showUnmarkUnmarkedTaskMessage());
                } else {
                    this.tasks.markAsNotDone(taskIndex);
                    response.append(ui.showTaskMarkedAsNotDone(task));
                }
            } else if (Parser.isDelete(command)) {
                taskIndex = Parser.extractTaskIndex(command);
                Task deletedTask = this.tasks.deleteTask(taskIndex);
                response.append(ui.showTaskDeleted(deletedTask, this.tasks.getTotalTasks()));
            } else if (Parser.isFind(command)) {
                String keyword = Parser.extractKeyword(command);
                ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
                response.append(ui.showMatchingTasks(matchingTasks));
            } else {
                Task newTask = Parser.parseTask(command);
                this.tasks.addTask(newTask);
                response.append(ui.showTaskAdded(newTask, this.tasks.getTotalTasks()));
            }
            this.storage.saveTasksToFile(this.tasks.getAllTasks());
        } catch (DukeException e) {
            response.append(ui.showError(e.getMessage()));
        }

        return response.toString();
    }
}
