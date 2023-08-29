package duke;

import java.util.ArrayList;

/**
 * This class handles any actions/commands that are to be done to the Task objects.
 */
public class TaskList {

    /**
     * This method prints out the Tasks that are inside the ArrayList.
     * @param taskList
     */
    public static void listTasks(ArrayList<Task> taskList) {
        int count = 1;
        for (Task task : taskList) {
            if (task == null) {
                break;
            } else {
                System.out.println(count++ + ". " + task);
            }
        }
    }

    /**
     * This method marks the Task at the specified index as done.
     * @param inputParts
     * @param taskList
     * @param markAsDone
     */
    public static void markTask(String[] inputParts, ArrayList<Task> taskList, boolean markAsDone) {
        int index = Integer.parseInt(inputParts[1]) - 1;
        Task task = taskList.get(index);
        if (markAsDone) {
            task.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } else {
            task.markUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + task);
        }
    }

    /**
     * This method deletes the Task at the specified index.
     * @param inputParts
     * @param taskList
     */
    public static void deleteTask(String[] inputParts, ArrayList<Task> taskList) {
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            if (index > taskList.size()) {
                throw new DukeException("☹ OOPS!!! Unable to delete non-existent task");
            }
            Task removedTask = taskList.remove(index);
            System.out.println("Noted. I've removed this task:\n" + removedTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * This method adds a Task to the given ArrayList.
     * @param inputParts
     * @param taskList
     */
    public static void addTask(String[] inputParts, ArrayList<Task> taskList) {
        try {
            Task newTask;
            if (inputParts.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a command cannot be empty.");
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
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            taskList.add(newTask);
            System.out.println("Got it. I've added this task:\n" + newTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void findTasks(String[] inputParts, ArrayList<Task> taskList) {
        String keyword = inputParts[1].toLowerCase();
        int count = 1;
        System.out.println("Here are the matching tasks in your list:");

        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                System.out.println(count++ + ". " + task);
            }
        }
    }
}
