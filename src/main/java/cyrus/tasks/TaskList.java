package cyrus.tasks;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cyrus.storage.IStorage;

/**
 * Wrapper around the list of {@code Task}.
 */
public class TaskList {
    private final List<Task> tasks;
    private final IStorage storage;

    /**
     * Creates TaskList with given {@code Storage} mechanism.
     *
     * @param storage {@code IStorage} mechanism to store task list after every action.
     */
    public TaskList(IStorage storage) {
        this.storage = storage;
        tasks = storage.load();
    }

    /**
     * Adds task and saves to file.
     *
     * @param task {@code Task} to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Removes task by index and saves tto file.
     *
     * @param index index position of task to remove.
     */
    public void removeTask(int index) {
        tasks.remove(index);
        saveTasks();
    }

    /**
     * Marks given task at {@code index} as done, updating the completed date to be the latest time if not previously
     * already completed.
     *
     * @param index index of task to mark as completed.
     */
    public void markTask(int index) {
        var task = tasks.get(index);
        if (task.getIsDone()) {
            // If already done, do nothing
            return;
        }
        task.setDone(true);
        task.setCompletedDate(LocalDate.now());
        saveTasks();
    }

    /**
     * Unmarks given task at {@code index} as not done, updating the completed date to be null.
     *
     * @param index index of task to mark as incompleted.
     */
    public void unmarkTask(int index) {
        var task = tasks.get(index);
        task.setDone(false);
        task.setCompletedDate(null);
        saveTasks();
    }

    /**
     * Finds {@code Task} whose name matches the given {@code keyword}.
     *
     * @param keyword keyword to find task.
     * @return all tasks that match the {@code keyword}.
     */
    public List<Task> findTask(String keyword) {
        return tasks
                .stream()
                .filter(task -> task.name.contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns the size of current {@code TaskList}.
     *
     * @return size of {@code TaskList}.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns {@code Task} given {@code index}.
     *
     * @param index index position of task to update.
     * @return {@code Task} corresponding to the {@code index}.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Generates the task distribution across the three types of tasks.
     *
     * @return {@code HashMap} of task name to count.
     */
    public HashMap<String, Long> getTaskDistribution() {
        HashMap<String, Long> distribution = new HashMap<>();

        long todoCount = tasks.stream().filter(task -> task instanceof ToDo).count();
        distribution.put("To-Do", todoCount);

        long deadlineCount = tasks.stream().filter(task -> task instanceof Deadline).count();
        distribution.put("Deadline", deadlineCount);

        long eventCount = tasks.stream().filter(task -> task instanceof Event).count();
        distribution.put("Event", eventCount);

        return distribution;
    }


    /**
     * Generates the latest week's task distribution.
     *
     * @return {@code HashMap} of date to count.
     */
    public HashMap<LocalDate, Long> getWeeklyTaskCompletionRate() {
        HashMap<LocalDate, Long> distribution = new HashMap<>();

        // Find the start of the week window starting from today
        LocalDate earliestDate = LocalDate.now().minusDays(7);

        // Only retrieve completed tasks and check if completed date falls within week window
        List<Task> filteredTasks = tasks.stream()
                .filter(Task::getIsDone)
                .filter(task -> {
                    var completedDate = task.getCompletedDate();
                    boolean isExactlyEarliestDate = completedDate.isEqual(earliestDate);
                    boolean isAfterEarliestDate = completedDate.isAfter(earliestDate);
                    return isExactlyEarliestDate || isAfterEarliestDate;
                })
                .collect(Collectors.toList());

        for (var task : filteredTasks) {
            long value = distribution.getOrDefault(task.getCompletedDate(), 0L);
            distribution.put(task.getCompletedDate(), value + 1);
        }

        return distribution;
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "You do not have any tasks, use todo, deadline, or event to add new ones!";
        }
        List<String> formattedTasks = IntStream
                .range(0, tasks.size())
                .mapToObj((j) -> String.format("%d. %s", j + 1, tasks.get(j)))
                .collect(Collectors.toList());
        formattedTasks.add(0, "Here are the tasks in your list:");
        return String.join("\n", formattedTasks);
    }

    /**
     * Saves the current list of tasks to the storage.
     */
    private void saveTasks() {
        storage.save(tasks);
    }
}
