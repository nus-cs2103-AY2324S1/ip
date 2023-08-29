package services.tasklist;

import command.CommandType;
import services.Format;
import services.bizerrors.EmptyArgumentException;
import services.bizerrors.IndexOutOfRangeException;
import services.bizerrors.JarvisException;
import services.tasklist.tasks.Deadline;
import services.tasklist.tasks.Event;
import services.tasklist.tasks.Task;
import services.tasklist.tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListManager {
    private static List<Task> taskList = new ArrayList<>();
    private static Storage repo;
    private static int taskCount = 0;

    public static void initialize() throws JarvisException {
        try {
            repo = new Storage();
            taskList = repo.load();
            taskCount = taskList.size();
        } catch (IOException e) {
            throw new JarvisException("Error initializing repository." + e);
        }
    }

    public static void add(String description, CommandType taskType, String... args) throws JarvisException {
        Task newTask;
        // this if block is unnecessary currently (is never reached), but it may be useful in the future.
        if (description.isEmpty()) {
            throw new EmptyArgumentException(taskType.toString().toLowerCase());
        }
        switch (taskType) {
            case TODO:
                newTask = new Todo(description);
                break;
            case DEADLINE:
                newTask = new Deadline(description, args[0]);
                break;
            case EVENT:
                newTask = new Event(description, args[0], args[1]);
                break;
            default:
                // the program should never reach this point.
                throw new JarvisException("Default case reached.");
        }
        taskList.add(newTask);
        taskCount++;
        try {
            repo.save(taskList);
        } catch (IOException e) {
            throw new JarvisException("Error adding task to repository." + e);
        }
        Format.print("added: " + newTask + "\n" + taskCount + " more tasks to do, Sir.");
    }

    public static void delete(int taskNumber) throws JarvisException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task deletedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        taskCount--;
        try {
            repo.save(taskList);
        } catch (IOException e) {
            throw new JarvisException("Error deleting task from repository." + e);
        }
        Format.print("removed: " + deletedTask + "\n" + taskCount + " tasks left, Sir.");
    }

    public static void markDone(int taskNumber) throws JarvisException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task task = taskList.get(taskNumber - 1);
        task.setDone();
        try {
            repo.save(taskList);
        } catch (IOException e) {
            throw new JarvisException("Error marking task as done in repository." + e);
        }
        Format.print("Check.\n\t" + task + "\n" + "Way to go, sir.");
    }

    public static void markUndone(int taskNumber) throws JarvisException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task task = taskList.get(taskNumber - 1);
        task.setUndone();
        try {
            repo.save(taskList);
        } catch (IOException e) {
            throw new JarvisException("Error marking task as undone in repository." + e);
        }
        Format.print("As you wish, sir.\n\t" + task);
    }

    public static void show() {
        if (taskCount == 0) {
            Format.print("Sir, there are no tasks on your calendar.");
            return;
        }
        String result = "Sir, there are " + taskCount + " tasks on your calendar:\n";
        for (int i = 1; i < taskCount; i++) {
            result += i + ". " + taskList.get(i - 1) + "\n";
        }
        result += taskCount + ". " + taskList.get(taskCount - 1);
        Format.print(result);
    }
}
