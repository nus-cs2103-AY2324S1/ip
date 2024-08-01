package services.tasklist;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import command.CommandType;
import services.bizerrors.IndexOutOfRangeException;
import services.bizerrors.JarvisException;
import services.bizerrors.SaveToFileException;
import services.tasklist.tasks.Deadline;
import services.tasklist.tasks.Event;
import services.tasklist.tasks.Task;
import services.tasklist.tasks.Todo;

/** This class implements the {@link ITaskList} interface. */
public class TaskList implements ITaskList {
    /** The list of tasks. */
    protected List<Task> tasks;
    protected IStorage repo;
    /** The number of tasks in the list. */
    protected int taskCount;

    /**
     * Creates a new TaskList object with the given Storage object and Ui object.
     *
     * @param repo   the Storage object that stores the list of tasks in a data file.
     */
    public TaskList(IStorage repo) {
        this.repo = repo;
        try {
            tasks = repo.load();
            taskCount = tasks.size();
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String addTask(String description, CommandType taskType, String... args) throws JarvisException {
        assert !description.isEmpty() : "description should not be empty";
        Task newTask;
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
            throw new JarvisException("Unknown task type.");
        }
        tasks.add(newTask);
        taskCount++;
        repo.save(tasks);
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
    public String deleteTask(int taskNumber) throws SaveToFileException, IndexOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        taskCount--;
        repo.save(tasks);
        return "removed: " + deletedTask + "\n" + taskCount + " tasks left, Sir.";
    }

    @Override
    public String addTagsToTask(int taskNumber, String[] tagNames) throws SaveToFileException, IndexOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task taskToTag = tasks.get(taskNumber - 1);
        taskToTag.addTags(tagNames);
        repo.save(tasks);
        return "added tags " + taskToTag.showAllTags() + " to the task:\n\t" + taskToTag;
    }

    @Override
    public String deleteTagsFromTask(int taskNumber, String[] tagNames) throws SaveToFileException, IndexOutOfRangeException {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            throw new IndexOutOfRangeException(taskNumber, taskCount);
        }
        Task taskToTag = tasks.get(taskNumber - 1);
        taskToTag.deleteTags(tagNames);
        repo.save(tasks);
        return "deleted tags from the task:\n\t" + taskToTag;
    }

    @Override
    public String findTask(String keyword) {
        List<Task> matchingTasks = tasks.stream()
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
        Task task = tasks.get(taskNumber - 1);
        task.setDone();
        repo.save(tasks);
        return "Check.\n\t" + taskNumber + ". " + task + "\nWay to go, sir.";
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
        Task task = tasks.get(taskNumber - 1);
        task.setUndone();
        repo.save(tasks);
        return "As you wish, sir.\n\t" + taskNumber + ". " + task;
    }

    @Override
    public String showAllTasks() {
        if (taskCount == 0) {
            return "Sir, there are no tasks on your calendar.";
        }
        String result = "Sir, there are " + taskCount + " tasks on your calendar:\n";
        result += IntStream.range(1, taskCount + 1)
                .mapToObj(i -> i + ". " + tasks.get(i - 1))
                .collect(Collectors.joining("\n"));
        return result;
    }
}
