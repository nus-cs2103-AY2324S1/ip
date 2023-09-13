package chatterbot.ui;

import chatterbot.data.Deadline;
import chatterbot.data.Event;
import chatterbot.data.Task;
import chatterbot.data.Todo;

import java.util.ArrayList;

/**
 * Determines what will be returned to the user.
 */
public class Ui {

    public static ArrayList<Task> list;

    public Ui(ArrayList<Task> list) {
        this.list = list;
    }
    protected static String response;

    /**
     * Prints a welcome message for the user.
     */
    public static void showWelcomeMessage() {
        String logo = "ChatterBot";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");
    }

    /**
     * Prints a goodbye message for the user.
     */
    public static String showGoodbyeMessage() {
        response = "Bye. Hope to see you again soon!";
        return response;
    }

    /**
     * Prints the current task list for the user.
     */
    public static String showTaskList() {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        for (Task t : list) {
            response.append("\n").append((list.indexOf(t) + 1)).append(". ").append(t.toString());
        }
        return response.toString();
    }

    /**
     * Prints the tasks in list format that contain the entered keyword.
     * @param toFind This is the entered keyword.
     */
    public static String showFoundTasks(String toFind) {
        ArrayList<Task> foundTasksArray = new ArrayList<Task>();

        for (Task t : list) {
            if (t.description.contains(toFind)) {
                foundTasksArray.add(t);
            }
        }

        StringBuilder response = new StringBuilder();
        if (foundTasksArray.isEmpty()) {
            response.append("No matching tasks!");
        } else {
            response.append("Here are the matching tasks in your list:");
            for (Task t : foundTasksArray) {
                response.append("\n").append((foundTasksArray.indexOf(t) + 1)).append(". ").append(t.toString());
            }
        }
        return response.toString();
    }


    /**
     * Prints a success message to let the user know the task was marked successfully.
     * @param toMark This is the task that will be marked.
     */
    public static String showMarked(String toMark) {
        String markedTaskDescription = list.get(Integer.parseInt(toMark) - 1).description;
        return "Nice! I've marked this task as done:\n[X] " + markedTaskDescription;
    }

    /**
     * Prints a success message to let the user know the task was unmarked successfully.
     * @param toUnmark This is the task that will be unmarked.
     */
    public static String showUnmarked(String toUnmark) {
        String unmarkedTaskDescription = list.get(Integer.parseInt(toUnmark) - 1).description;
        return "OK, I've marked this task as not done yet:\n[ ] " + unmarkedTaskDescription;
    }

    /**
     * Prints a success message to let the user know the deadline task was added successfully,
     * and the number of tasks they currently have.
     * @param d This is the description and deadline of the added deadline task.
     */

    public static String showAddedDeadline(Deadline d) {
        String addedTaskMessage = "Got it. I've added this task:\n" + d.toString() + "\nNow you have " + list.size()
                + " tasks in the list.";
        return addedTaskMessage;
    }

    /**
     * Prints a success message to let the user know the todo task was added successfully,
     * and the number of tasks they currently have.
     * @param td This is the description of the added todo task.
     */
    public static String showAddedTodo(Todo td) {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n")
                .append(td.toString())
                .append("\nNow you have ")
                .append(list.size())
                .append(" tasks in the list.");
        return message.toString();
    }

    /**
     * Prints a success message to let the user know the event task was added successfully,
     * and the number of tasks they currently have.
     * @param e This is the description, start and end time of the added event task.
     */
    public static String showAddedEvent(Event e) {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n")
                .append(e.toString())
                .append("\nNow you have ")
                .append(list.size())
                .append(" tasks in the list.");
        return message.toString();
    }

    /**
     * Prints a success message to let the user know the task was deleted successfully.
     * @param userMessage This is the task in the user input that will be deleted.
     */
    public static String showDeleted(String userMessage) {
        int taskIndex = Integer.parseInt(userMessage.substring(7)) - 1;
        StringBuilder message = new StringBuilder();

        if (taskIndex >= 0 && taskIndex < list.size()) {
            message.append("Noted. I've removed this task:\n")
                    .append(list.get(taskIndex))
                    .append("\nNow you have ")
                    .append(list.size() - 1)
                    .append(" tasks in the list.");
        } else {
            message.append("Invalid task index. No task removed.");
        }
        return message.toString();
    }

    /**
     * Prints a message to tell the user the entered input is unknown by ChatterBot.
     * @param userMessage This is the user input that is unknown.
     */
    public static String showUnknownCommand(String userMessage) {
        return "Unknown command: " + userMessage;
    }
}