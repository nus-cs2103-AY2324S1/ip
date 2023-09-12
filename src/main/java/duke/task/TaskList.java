package duke.task;

import duke.DukeException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list. Throws DukeException if the description is invalid.
     * @param taskType taskType.
     * @param description description.
     * @return success message and the task added.
     * @throws DukeException if the description is invalid.
     */
    public String addTask(TaskType taskType, String description) throws DukeException {
        description = description.trim();
        switch (taskType) {
        case TODO:
            if (!description.isEmpty()) {
                this.tasks.add(new ToDo(description));
            } else {
                throw new DukeException("todo error");
            }
            break;
        case DEADLINE:
            Pattern p = Pattern.compile("(.+) /by (.+)");
            Matcher m = p.matcher(description);
            if (m.matches() && !m.group(1).isEmpty() && !m.group(2).isEmpty()) {
                try {
                    this.tasks.add(new Deadline(m.group(1), m.group(2)));
                } catch (DateTimeParseException e) {
                    throw new DukeException("deadline error");
                }
            } else {
                throw new DukeException("deadline error");
            }
            break;
        case EVENT:
            Pattern pattern = Pattern.compile("(.+) /from (.+) /to (.+)");
            Matcher matcher = pattern.matcher(description);
            if (matcher.matches() && !matcher.group(1).isEmpty() && !matcher.group(2).isEmpty()
                    && !matcher.group(3).isEmpty()) {
                this.tasks.add(new Event(matcher.group(1), matcher.group(2), matcher.group(3)));
            } else {
                throw new DukeException("event error");
            }
            break;
        }
        int size = this.tasks.size();
        String taskInTotal = size > 1 ? " tasks in total." : " task in total.";
        return "Task added:\n" + this.tasks.get(size - 1) + "\nNow you have "
                + size + taskInTotal + "\n\"Be here now.\"";
    }

    /**
     * Returns the string representation of tasks in a list.
     */
    public String getTasks() {
        String result = "Here are your tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + " " + tasks.get(i) + "\n";
        }
        return result + "\"One thing at a time.\"";
    }

    /**
     * Returns the tasks in txt format.
     */
    public String getTasksTxt() {
        String result = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            result += tasks.get(i).toTxt() + System.lineSeparator();
        }
        if (!tasks.isEmpty()) {
            result += tasks.get(tasks.size() - 1).toTxt();
        }
        return result;
    }

    /**
     * Marks the task as undone/done accordingly. Throws DukeException if the task is not found.
     * @param taskIndex taskIndex.
     * @param isDone task status.
     * @return success message and the task modified.
     * @throws DukeException if the task is not found.
     */
    public String markTask(int taskIndex, boolean isDone) throws DukeException {
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new DukeException("task not found");
        } else {
            tasks.get(taskIndex - 1).markAsDone(isDone);
            return "Here's your modified task:\n" + tasks.get(taskIndex - 1) + "\n\"Keep moving forward.\"";
        }
    }

    /**
     * Finds tasks that contain the keyword.
     * @param task keyword.
     * @return Strings of valid tasks.
     * @throws DukeException if no task is found.
     */
    public String findTask(String task) throws DukeException{
        ArrayList<Task> foundedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(task)) {
                foundedTasks.add(tasks.get(i));
            }
        }
        if (foundedTasks.isEmpty()) {
            throw new DukeException("task not found");
        }
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < foundedTasks.size(); i++) {
            result += (i + 1) + " " + foundedTasks.get(i) + "\n";
        }
        return result + "\"One thing at a time.\"";
    }

    /**
     * Deletes the task.
     * @param taskIndex taskIndex.
     * @return success message and the task deleted.
     * @throws DukeException if the task is not found.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        //TODO: double check if not completed
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new DukeException("task not found");
        } else {
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            int size = this.tasks.size();
            String taskInTotal = size > 1 ? " tasks in total." : " task in total.";
            return "I've successfully deleted this task:\n" + task + "\nNow you have " + size + taskInTotal
                    + "\n\"Ride the waves.\"";
        }
    }
}
