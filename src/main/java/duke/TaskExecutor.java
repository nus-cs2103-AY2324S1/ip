package duke;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

/**
 * This class handles any actions/commands that are to be done to the Task objects.
 */
public class TaskExecutor {

    /**
     * This method prints out the Tasks that are inside the ArrayList.
     * @param taskList
     */
    public String listTasks(ArrayList<Task> taskList) {
        String output = "";
        int count = 1;
        for (Task task : taskList) {
            if (task == null) {
                break;
            } else {
                output += count++ + ". " + task + "\n";
            }
        }
        return output;
    }

    /**
     * This method marks the Task at the specified index as done.
     * @param inputParts
     * @param taskList
     * @param markAsDone
     */
    public String markTask(String[] inputParts, ArrayList<Task> taskList, boolean markAsDone) {
        int index = Integer.parseInt(inputParts[1]) - 1;
        Task task = taskList.get(index);
        if (markAsDone) {
            task.markDone();
            return "Nice! I've marked this task as done:\n" + task;
        } else {
            task.markUndone();
            return "OK, I've marked this task as not done yet:\n" + task;
        }
    }

    /**
     * This method deletes the Task at the specified index.
     * @param inputParts
     * @param taskList
     */
    public String deleteTask(String[] inputParts, ArrayList<Task> taskList) {
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            if (index > taskList.size()) {
                throw new IrisException("☹ OOPS!!! Unable to delete non-existent task");
            }
            Task removedTask = taskList.remove(index);
            return "Noted. I've removed this task:\n" + removedTask +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (IrisException e) {
            return e.toString();
        }
    }

    /**
     * This method adds a Task to the given ArrayList.
     * @param inputParts
     * @param taskList
     */
    public String addTask(String[] inputParts, ArrayList<Task> taskList) {
        try {
            Task newTask;
            if (inputParts.length < 2) {
                throw new IrisException("☹ OOPS!!! The description of a command cannot be empty.");
            }

            String description = inputParts[1];
            if (inputParts[0].equals("todo")) {
                newTask = new Todo(description);
            } else if (inputParts[0].equals("deadline")) {
                String[] commandParts = description.split("/by ", 2);
                newTask = new Deadline(commandParts[0], commandParts[1]);
            } else if (inputParts[0].equals("event")) {
                String[] commandParts = description.split("/from ", 2);
                String[] eventParts = commandParts[1].split("/to ");
                newTask = new Event(commandParts[0], eventParts[0], eventParts[1]);
            } else {
                throw new IrisException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            taskList.add(newTask);
            return "Got it. I've added this task:\n" + newTask +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (IrisException e) {
            return e.getMessage();
        }
    }

    /**
     * This method lists out the tasks in the list of tasks that contain the target string.
     * @param inputParts
     * @param taskList
     */
    public String findTasks(String[] inputParts, ArrayList<Task> taskList) {
        int count = 1;
        String keyword = inputParts[1].toLowerCase();
        String output = "Here are the matching tasks in your list:\n";

        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                output += count++ + ". " + task + "\n";
            }
        }

        return output;
    }
}
