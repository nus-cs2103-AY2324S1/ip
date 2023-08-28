package carbonbot;

import carbonbot.task.Deadline;
import carbonbot.task.Event;
import carbonbot.task.Task;
import carbonbot.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> tasks) throws DukeException {
        this.tasks = new ArrayList<Task>();
        for (String str : tasks) {

            try {
                Task task;

                // Split the string by its delimiter
                String[] cols = str.split(" \\| ");

                String taskType = cols[0];
                switch (taskType) {
                case "T":
                    task = new Todo(cols[2]);
                    break;
                case "D":
                    try {
                        LocalDateTime byDt = parseDateTimeString(cols[3]);
                        task = new Deadline(cols[2], byDt);
                    } catch (DateTimeParseException ex) {
                        throw new DukeException("☹ OOPS!!! The 'by' datetime was not in a valid format.");
                    }
                    break;
                case "E":
                    try {
                        LocalDateTime fromDt = parseDateTimeString(cols[3]);
                        LocalDateTime toDt = parseDateTimeString(cols[4]);
                        task = new Event(cols[2], fromDt, toDt);
                    } catch (DateTimeParseException ex) {
                        throw new DukeException("☹ OOPS!!! The 'by' datetime was not in a valid format.");
                    }
                    break;
                default:
                    throw new DukeException("Invalid File Format");
                }
                if (cols[1].equals("1")) {
                    task.markAsDone();
                }
                this.tasks.add(task);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new DukeException("☹ OOPS!!! The save file wasn't in the correct format.");
            }
        }
    }

    private LocalDateTime parseDateTimeString(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the index.
     *
     * @param index 1-based index of the task
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index <= tasks.size() && index >= 1) {
            tasks.remove(index - 1);
        } else {
            throw new IndexOutOfBoundsException("The task list does not contain the given index.");
        }
    }

    /**
     * Fetches the task at the index.
     *
     * @param index 1-based index of the task
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        if (index <= tasks.size() && index >= 1) {
            return tasks.get(index - 1);
        } else {
            throw new IndexOutOfBoundsException("The task list does not contain the given index.");
        }
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.serialize() + "\n");
        }
        return sb.toString();
    }

    public void printList() {
        int idx = 1;
        for (Task t : tasks) {
            System.out.println(String.format("%d.%s", idx, t));
            idx++;
        }
    }

    public int size() {
        return this.tasks.size();
    }

}
