package deterministicparrot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks that can be managed and manipulated.
 */
class TaskList {
    private List<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Formats the tasks in the TaskList as a string.
     *
     * @return A formatted string representing the tasks in the TaskList.
     */
    public String formatAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            sb.append("     " + (i + 1) + ". " + this.list.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Serializes the TaskList to a string format.
     *
     * @return A serialized string representation of the TaskList.
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.list) {
            if (task instanceof ToDo) {
                sb.append("T | ").append(task.getIsDone() ? "1" : "0").append(" | ").append(task.getName()).append("\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                sb.append("D | ").append(task.getIsDone() ? "1" : "0").append(" | ").append(task.getName()).append(" | ").append(DPUtils.saveFormatDateTime(deadline.by)).append("\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                sb.append("E | ").append(task.getIsDone() ? "1" : "0").append(" | ").append(task.getName()).append(" | ").append(DPUtils.saveFormatDateTime(event.timeStart)).append(" ").append(DPUtils.saveFormatDateTime(event.timeEnd)).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Deserializes a string representation to create a TaskList.
     *
     * @param rawData The serialized string representing the TaskList.
     * @return A deserialized TaskList object.
     * @throws Exception If there's an error during deserialization.
     */
    public static TaskList deserialize(String rawData) throws Exception {
        List<Task> tasks = new ArrayList<>();
        String[] lines = rawData.split("\n");

        for (String line : lines) {
            String[] data = line.split(" \\| ");
            switch (data[0]) {
                case "T":
                    ToDo todo = new ToDo(data[2]);
                    if (data[1].equals("1")) todo.markAsDone();
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(data[2], data[3]);
                    if (data[1].equals("1")) deadline.markAsDone();
                    tasks.add(deadline);
                    break;
                case "E":
                    String[] time = data[3].split(" ");
                    Event event = new Event(data[2], time[0], time[1]);
                    if (data[1].equals("1")) event.markAsDone();
                    tasks.add(event);
                    break;
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Checks if the provided index is valid and throws an exception if not.
     *
     * @param idx The index to check.
     * @throws DeterministicParrotException If the index is invalid.
     */
    private void checkIfValidIdx(int idx) throws DeterministicParrotException {
        if (idx <= 0 || idx > list.size()) {
            throw new DeterministicParrotException("Invalid task number.");
        }
    }

    /**
     * Gets a task at the specified index.
     *
     * @param idx The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws DeterministicParrotException If the index is invalid.
     */
    public Task getTask(int idx) throws DeterministicParrotException {
        checkIfValidIdx(idx);
        return this.list.get(idx - 1);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param idx The index of the task to delete.
     * @return The deleted task.
     * @throws DeterministicParrotException If the index is invalid.
     */
    public Task deleteTask(int idx) throws DeterministicParrotException {
        checkIfValidIdx(idx);
        Task t = this.list.remove(idx - 1);
        return t;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t The task to add.
     * @return The new size of the TaskList.
     */
    public int addTask(Task t) {
        this.list.add(t);
        return getSize();
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param idx The index of the task to mark as done.
     * @return The task that was marked as done.
     * @throws DeterministicParrotException If the index is invalid.
     */
    public Task markAsDone(int idx) throws DeterministicParrotException {
        Task t = getTask(idx);
        t.markAsDone();
        return t;
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param idx The index of the task to mark as undone.
     * @return The task that was marked as undone.
     * @throws DeterministicParrotException If the index is invalid.
     */
    public Task markAsUndone(int idx) throws DeterministicParrotException {
        Task t = getTask(idx);
        t.markAsUndone();
        return t;
    }
}
