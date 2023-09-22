package task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exception.EpochMindException;

/**
 * Class that contains list of tasks and operations on those tasks
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    /**
     * Method to return the List of tasks
     *
     * @return The list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * List out the task list
     */
    public String list() {
        if (tasks.size() == 0) {
            return "The Mind sees no task";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * List out the task list when given an ArrayList of tasks
     *
     * @param tasks ArrayList of task.Task
     */
    public String list(List<Task> tasks) {
        if (tasks.size() == 0) {
            return "The Mind sees no task";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Mark a task as completed
     *
     * @param index Index to mark as completed
     */
    public String mark(int index) {
        if (index == -1) { // Parsing issue
            return "Thou hast specified an invalid index";
        } else {
            if (tasks.size() < index) {
                EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                return e.toString();
            } else {
                tasks.get(index - 1).mark();
                return "The Mind sees that this task is completed \n" +
                        tasks.get(index - 1);
            }
        }
    }

    /**
     * Unmark a completed task
     *
     * @param index Index to mark as uncompleted
     */
    public String unmark(int index) {
        if (index == -1) { // Parsing issue
            return "Thou hast specified an invalid index";
        } else {
            if (tasks.size() < index) {
                EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                return e.toString();
            } else {
                tasks.get(index - 1).unmark();
                return "The Mind sees that this task is not yet completed \n" +
                        tasks.get(index - 1);
            }
        }
    }

    /**
     * Delete a task from the list
     *
     * @param index Index to delete the task
     */
    public String delete(int index) {
        if (index == -1) { // Parsing issue
            return "Thou hast specified an invalid index";
        } else {
            if (tasks.size() < index) {
                EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                return e.toString();
            } else {
                Task task = tasks.remove(index - 1);
                return "The Mind has eradicated the task \n" +
                        task + "\nThere are now " + tasks.size() + " tasks left to complete";
            }
        }
    }

    /**
     * Add a todo task
     *
     * @param task Parsed string containing the task description
     */
    public String todo(String task) {
        ToDo toDo = new ToDo(task);
        if (!hasDuplicate(toDo)) {
            tasks.add(toDo);
            return "The Mind has added a new task \n" + toDo + "\nThere are now " +
                    tasks.size() + " tasks left to complete";
        } else {
            return "The Mind sees a duplicate entry";
        }
    }

    /**
     * Add a deadline task
     *
     * @param task           Parsed string containing the task description
     * @param deadlineString Parsed string containing the deadline
     */
    public String deadline(String task, String deadlineString) {
        Deadline deadline = new Deadline(task, deadlineString);
        if (!hasDuplicate(deadline)) {
            tasks.add(deadline);
            return "The Mind has added a new task \n" + deadline +
                    "\nThere are now " + tasks.size() + " tasks left to complete";
        } else {
            return "The Mind sees a duplicate entry";
        }
    }


    /**
     * Add an event task
     *
     * @param description Parsed string containing the task description
     * @param start       Parsed string containing the start datetime
     * @param end         Parsed string containing the end datetime
     */
    public String event(String description, String start, String end) {
        Event event = new Event(description, start, end);
        if (!hasDuplicate(event)) {
            tasks.add(event);
            return "The Mind has added a new task \n" + event +
                    "\nThere are now " + tasks.size() + " tasks left to complete";
        } else {
            return "The Mind sees a duplicate entry";
        }
    }

    /**
     * Adds any task
     *
     * @param task The task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Size of task list
     *
     * @return Number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Displays all tasks that are overdue
     */
    public String overdue() {
        TaskList overdue = new TaskList();
        for (Task task : tasks) {
            if (task.isDeadline()) {
                Deadline deadline = (Deadline) task;
                if (deadline.isOverdue()) {
                    overdue.add(deadline);
                }
            }
        }
        if (overdue.size() == 0) {
            return "The Mind sees no overdue deadlines";
        } else {
            return overdue.list();
        }
    }

    /**
     * Returns a string representation of all the tasks that are due by the specified date
     *
     * @param command The input by user with the command of dueby and second argument of the date
     * @return String
     */
    public String dueBy(String command) {
        String[] commandList = command.trim().toLowerCase().split(" ");
        if (commandList.length <= 1) {
            return "The Mind sees no deadline";
        }
        String dateTimeString = commandList[1];
        LocalDateTime localDateTime = Deadline.convertDate(dateTimeString);
        if (localDateTime == null) {
            return "The Mind does not sense a correct DateTime";
        }
        TaskList dueBy = new TaskList();
        for (Task task : tasks) {
            if (task.isDeadline()) {
                Deadline deadline = (Deadline) task;
                if (deadline.isDueBy(localDateTime)) {
                    dueBy.add(deadline);
                }
            }
        }
        if (dueBy.size() == 0) {
            return "The Mind sees no deadlines due by that day";
        } else {
            return dueBy.list();
        }
    }

    /**
     * Find all tasks that contains the string searchBy
     *
     * @param searchBy The string to searchBy
     * @return String representation of all the tasks that contains the string
     */
    public String find(String searchBy) {
        TaskList contains = new TaskList();
        for (Task task : tasks) {
            if (task.contains(searchBy)) {
                contains.add(task);
            }
        }
        if (contains.size() == 0) {
            return "The Mind sees no such tasks";
        }
        return contains.list();
    }

    /**
     * Check for duplicate in tasklist and get confirmation to add
     *
     *
     */
    public boolean hasDuplicate(Task newTask) {
        for (Task task : tasks) {
            if (task.isDuplicate(newTask)) {
                return true;
            }
        }
        return false;
    };

}
