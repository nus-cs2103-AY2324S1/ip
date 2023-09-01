package services.tasklist;

import command.CommandType;
import services.Ui;
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

public class TaskList implements ITaskList {
    protected List<Task> taskList;
    protected IStorage repo;
    protected int taskCount;
    protected Ui ui;

    public TaskList(IStorage repo, Ui ui) {
        this.ui = ui;
        this.repo = repo;
        try {
            taskList = repo.load();
            taskCount = taskList.size();
        } catch (JarvisException e) {
            ui.print(e.toString() + "\nA temporary session is opened for you.");
            taskList = new ArrayList<>();
            taskCount = 0;
        }
    }

    @Override
    public void add(String description, CommandType taskType, String... args) throws JarvisException {
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
        repo.save(taskList);
        ui.print("added: " + newTask + "\n" + taskCount + " more tasks to do, Sir.");
    }

    @Override
    public void delete(int taskNumber) throws JarvisException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task deletedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        taskCount--;
        repo.save(taskList);
        ui.print("removed: " + deletedTask + "\n" + taskCount + " tasks left, Sir.");
    }

    @Override
    public void markDone(int taskNumber) throws JarvisException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task task = taskList.get(taskNumber - 1);
        task.setDone();
        repo.save(taskList);
        ui.print("Check.\n\t" + task + "\n" + "Way to go, sir.");
    }

    @Override
    public void markUndone(int taskNumber) throws JarvisException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task task = taskList.get(taskNumber - 1);
        task.setUndone();
        repo.save(taskList);
        ui.print("As you wish, sir.\n\t" + task);
    }

    @Override
    public void show() {
        if (taskCount == 0) {
            ui.print("Sir, there are no tasks on your calendar.");
            return;
        }
        String result = "Sir, there are " + taskCount + " tasks on your calendar:\n";
        for (int i = 1; i < taskCount; i++) {
            result += i + ". " + taskList.get(i - 1) + "\n";
        }
        result += taskCount + ". " + taskList.get(taskCount - 1);
        ui.print(result);
    }

    public void find(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        int count = matchingTasks.size();
        if (count == 0) {
            ui.print("Sir, there are no matching tasks on your calendar.");
            return;
        }
        String result = "Sir, there are " + count + " matching tasks on your calendar:\n";
        for (int i = 1; i < count; i++) {
            result += i + ". " + matchingTasks.get(i - 1) + "\n";
        }
        result += count + ". " + matchingTasks.get(count - 1);
        ui.print(result);
    }
}
