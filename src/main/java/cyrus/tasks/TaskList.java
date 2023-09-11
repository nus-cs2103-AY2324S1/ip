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
     * Create TaskList with given {@code Storage} mechanism.
     *
     * @param storage {@code IStorage} mechanism to store task list after every action.
     */
    public TaskList(IStorage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Adds task and saves to file.
     *
     * @param task {@code Task} to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.saveTasks();
    }

    /**
     * Removes task by index and saves tto file.
     *
     * @param index index position of task to remove
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
        this.saveTasks();
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
        return this.tasks
                .stream()
                .filter(task -> task.name.contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Get size of current {@code TaskList}.
     *
     * @return size of {@code TaskList}
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Get {@code Task} given {@code index}.
     *
     * @param index index position of task to update
     * @return {@code Task} corresponding to the {@code index}
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Generates the task distribution across the three types of tasks.
     *
     * @return {@code HashMap} of task name to count.
     */
    public HashMap<String, Long> getTaskDistribution() {
        HashMap<String, Long> distribution = new HashMap<>();
        long todoCount = tasks.stream().filter(task -> task instanceof ToDo).count();
        long deadlineCount = tasks.stream().filter(task -> task instanceof Deadline).count();
        long eventCount = tasks.stream().filter(task -> task instanceof Event).count();
        distribution.put("To-Do", todoCount);
        distribution.put("Deadline", deadlineCount);
        distribution.put("Event", eventCount);
        return distribution;
    }


    /**
     * Generates the latest week's task distribution.
     *
     * @return {@code HashMap} of date to count.
     */
    public HashMap<LocalDate, Long> getLatestWeekTaskDistribution() {
        HashMap<LocalDate, Long> distribution = new HashMap<>();
        LocalDate earliestDate = LocalDate.now().minusDays(7);
        List<Task> filteredTasks = tasks.stream()
                .filter(task ->
                        task.getCompletedDate() != null
                                && (task.getCompletedDate().isEqual(earliestDate)
                                || task.getCompletedDate().isAfter(earliestDate)))
                .collect(Collectors.toList());
        for (var task : filteredTasks) {
            long value = distribution.getOrDefault(task.getCompletedDate(), 0L);
            distribution.put(task.getCompletedDate(), value + 1);
        }
        return distribution;
    }

    @Override
    public String toString() {
        if (this.tasks.size() == 0) {
            return "You do not have any tasks, use todo, deadline, or event to add new ones!";
        }
        List<String> formattedTasks = IntStream
                .range(0, tasks.size())
                .mapToObj((j) -> String.format("%d. %s", j + 1, this.tasks.get(j)))
                .collect(Collectors.toList());
        formattedTasks.add(0, "Here are the tasks in your list:");
        return String.join("\n", formattedTasks);
    }

    /**
     * Helper function to save the current list of tasks to the storage.
     */
    private void saveTasks() {
        this.storage.save(this.tasks);
    }
}
