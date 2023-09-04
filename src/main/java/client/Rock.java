package client;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import commands.Commands;
import io.Invoker;
import io.Ui;
import storage.Storage;
import storage.StorageException;
import tasks.Task;
import tasks.TaskList;
/**
 * Rock is the name of and the main program used
 * to run the chatbot for ip.
 * @author Alvis Ng (supermii2)
 */
public class Rock {
    private static Path filePath = Paths.get("data", "tasks.ser");
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Invoker invoker;
    private boolean isTerminated = false;

    public Rock(Path path) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.invoker = new Invoker(new Commands(this));
        try {
            this.storage = new Storage(path.toAbsolutePath().toFile(), this);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        this.ui.startup();
    }
    public void run() {
        while (!isTerminated) {
            String userInput = this.ui.getInput();
            try {
                ui.respond(invoker.handle(userInput));
            } catch (RockException e) {
                this.ui.respond(e.getMessage());
            }
        }
    }
    public void addTask(Task task) {
        taskList.addTask(task);
    }
    public Task removeTask(int i) throws IndexOutOfBoundsException {
        return taskList.removeTask(i);
    }
    public void markTask(int index, boolean completed) {
        taskList.mark(index, completed);
    }
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
    public void resetTaskList() {
        taskList.reset();
    }
    public String taskListFilteredSearch(Predicate<Task> condiiton) {
        return taskList.filteredSearch(condiiton);
    }
    public String taskListToString() {
        return taskList.toString();
    }
    public void say(String msg) {
        ui.say(msg);
    }
    public void saveFile() {
        try {
            storage.saveSaveFile(taskList);
        } catch (StorageException e) {
            ui.say(e.getMessage());
        }
    }
    public void saveFile(TaskList tl) {
        try {
            storage.saveSaveFile(tl);
        } catch (StorageException e) {
            ui.say(e.getMessage());
        }
    }
    public void terminate() {
        // Sets necessary fields to closed.
        this.isTerminated = true;
        ui.close();
    }
    public static void main(String[] args) {
        new Rock(filePath).run();
    }
}
