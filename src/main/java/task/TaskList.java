package task;

import exception.EpochMindException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("The Mind sees no task");
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
            System.out.println("The Mind sees no task");
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
    public void mark(int index) {
        if (index == -1) { // Parsing issue
            // Do nothing
        } else {
            if (tasks.size() < index) {
                EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                System.out.println(e);
            } else {
                tasks.get(index - 1).mark();
                System.out.println("The Mind sees that this task is completed \n" +
                        tasks.get(index - 1));
            }
        }
    }

    /**
     * Unmark a completed task
     *
     * @param index Index to mark as uncompleted
     */
    public void unmark(int index) {
        if (index == -1) { // Parsing issue
            // Do nothing
        } else {
            if (tasks.size() < index) {
                EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                System.out.println(e);
            } else {
                tasks.get(index - 1).unmark();
                System.out.println("The Mind sees that this task is not yet completed \n" +
                        tasks.get(index - 1));
            }
        }
    }

    /**
     * Delete a task from the list
     *
     * @param index Index to delete the task
     */
    public void delete(int index) {
        if (index == -1) { // Parsing issue
            // Do nothing
        } else {
            if (tasks.size() < index) {
                EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                System.out.println(e);
            } else {
                Task task = tasks.remove(index - 1);
                System.out.println("The Mind has eradicated the task \n" +
                        task + "\nThere are now " + tasks.size() + " tasks left to complete");
            }
        }
    }

    /**
     * Add a todo task
     *
     * @param task Parsed string containing the task description
     */
    public void todo(String task) {
        ToDo toDo = new ToDo(task);
        tasks.add(toDo);
        System.out.println("The Mind has added a new task \n" + toDo + "\nThere are now " + tasks.size() + " tasks left to complete");
    }

    /**
     * Add a deadline task
     *
     * @param task           Parsed string containing the task description
     * @param deadlineString Parsed string containing the deadline
     */
    public void deadline(String task, String deadlineString) {
        Deadline deadline = new Deadline(task, deadlineString);
        tasks.add(deadline);
        System.out.println("The Mind has added a new task \n" + deadline + "\nThere are now " + tasks.size() + " tasks left to complete");
    }


    /**
     * Add an event task
     *
     * @param description Parsed string containing the task description
     * @param start       Parsed string containing the start datetime
     * @param end         Parsed string containing the end datetime
     */
    public void event(String description, String start, String end) {
        Event event = new Event(description, start, end);
        tasks.add(event);
        System.out.println("The Mind has added a new task \n" + event + "\nThere are now " + tasks.size() + " tasks left to complete");
    }

    /**
     * Displays all tasks that are overdue
     */
    public String overdue() {
        List<Task> overdue = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDeadline()) {
                Deadline deadline = (Deadline) task;
                if (deadline.overdue()) {
                    overdue.add(deadline);
                }
            }
        }
        if (overdue.size() == 0) {
            return "The Mind sees no overdue deadlines";
        } else {
            return list(overdue);
        }
    }

    public String dueBy(String command) {
        String[] commandList = command.trim().toLowerCase().split(" ");
        if (commandList.length > 1) {
            String dateTimeString = commandList[1];
            LocalDateTime localDateTime = Deadline.convertDate(dateTimeString);
            if (localDateTime != null) {
                List<Task> dueBy = new ArrayList<>();
                for (Task task : tasks) {
                    if (task.isDeadline()) {
                        Deadline deadline = (Deadline) task;
                        if (deadline.dueBy(localDateTime)) {
                            dueBy.add(deadline);
                        }
                    }
                }
                if (dueBy.size() == 0) {
                    return "The Mind sees no deadlines due by that day";
                } else {
                    return list(dueBy);
                }
            } else {
                return "The Mind does not sense a correct DateTime";
            }
        } else {
            return "The Mind sees no deadline";
        }
    }
}
