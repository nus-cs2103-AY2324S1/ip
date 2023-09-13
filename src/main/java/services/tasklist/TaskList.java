package services.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import command.CommandType;
import services.bizerrors.EmptyArgumentException;
import services.bizerrors.IndexOutOfRangeException;
import services.bizerrors.JarvisException;
import services.bizerrors.SaveToFileException;
import services.tasklist.tasks.Deadline;
import services.tasklist.tasks.Event;
import services.tasklist.tasks.Task;
import services.tasklist.tasks.Todo;

public class TaskList implements ITaskList {
    /** The list of tasks. */
    protected List<Task> taskList;
    protected IStorage repo;
    /** The number of tasks in the list. */
    protected int taskCount;

    /**
     * Creates a new TaskList object with the given Storage object and Ui object.
     *
     * @param repo   the Storage object that stores the list of tasks in a data file.
     * @param textUi the Ui object that prints the formatted task list to the user.
     */
    public TaskList(IStorage repo) {
        this.repo = repo;
        try {
            taskList = repo.load();
            taskCount = taskList.size();
        } catch (JarvisException e) {
            // Fix the problem here in the future.
            // System.out.println(e.toString() + "\nA temporary session is opened for you.");
            taskList = new ArrayList<>();
            taskCount = 0;
        }
    }

    @Override
    public String add(String description, CommandType taskType, String... args) throws JarvisException {
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
        return "added: " + newTask + "\n" + taskCount + " more tasks to do, Sir.";
    }

    /**
     * {@inheritDoc}
     *
     * @param taskNumber {@inheritDoc}
     * @return {@inheritDoc}
     * @throws SaveToFileException      if the task deletion operation cannot be saved to the data file.
     * @throws IndexOutOfRangeException if the task number is out of range.
     */
    @Override
    public String delete(int taskNumber) throws SaveToFileException, IndexOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task deletedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        taskCount--;
        repo.save(taskList);
        return "removed: " + deletedTask + "\n" + taskCount + " tasks left, Sir.";
    }

    @Override
    public String find(String keyword) {
        List<Task> matchingTasks = taskList.stream()
                .filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toList());

        int count = matchingTasks.size();
        if (count == 0) {
            return "Sir, there are no matching tasks on your calendar.";
        }
        String result = "Sir, there are " + count + " matching tasks on your calendar:\n";
        result += IntStream.range(1, count + 1)
                .mapToObj(i -> i + ". " + matchingTasks.get(i - 1))
                .collect(Collectors.joining("\n"));
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param taskNumber {@inheritDoc}
     * @return {@inheritDoc}
     * @throws SaveToFileException      if the task marking operation cannot be saved to the data file.
     * @throws IndexOutOfRangeException if the task number is out of range.
     */
    @Override
    public String markDone(int taskNumber) throws SaveToFileException, IndexOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task task = taskList.get(taskNumber - 1);
        task.setDone();
        repo.save(taskList);
        return "Check.\n\t" + task + "\n" + "Way to go, sir.";
    }

    /**
     * {@inheritDoc}
     *
     * @param taskNumber {@inheritDoc}
     * @return {@inheritDoc}
     * @throws SaveToFileException      if the task unmarking operation cannot be saved to the data file.
     * @throws IndexOutOfRangeException if the task number is out of range.
     */
    @Override
    public String markUndone(int taskNumber) throws SaveToFileException, IndexOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task task = taskList.get(taskNumber - 1);
        task.setUndone();
        repo.save(taskList);
        return "As you wish, sir.\n\t" + task;
    }

    @Override
    public String show() {
        if (taskCount == 0) {
            return "Sir, there are no tasks on your calendar.";
        }
        String result = "Sir, there are " + taskCount + " tasks on your calendar:\n";
        result += IntStream.range(1, taskCount + 1)
                .mapToObj(i -> i + ". " + taskList.get(i - 1))
                .collect(Collectors.joining("\n"));
        return result;
    }
}
