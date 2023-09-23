package moss;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The TaskList class handles processing and executing user commands related to task management.
 */
public class TaskList {

    /**
     * Processes and executes user commands related to task management.
     *
     * @param message The user command to be processed.
     * @param things The list of tasks being managed.
     * @param storage The storage object for saving tasks.
     * @return A string containing the output or response to the user command.
     * @throws MossException If there's an issue with processing commands or managing tasks.
     */
    public static String command(String message, ArrayList<Task> things, Storage storage) throws MossException {
        UI ui = new UI();
        things = (ArrayList<Task>) storage.loadTasks();

        if (message.equals("bye")) {
            return ui.bye();
        }

        if (message.equals("list")) {
            return listTasks(things);
        }

        // Handle other cases
        if (message.startsWith("mark")) {
            return markTask(message, things, storage);
        } else if (message.startsWith("unmark")) {
            return unmarkTask(message, things, storage);
        } else if (message.startsWith("delete")) {
            return deleteTask(message, things, storage);
        } else if (message.startsWith("help")) {
            return displayHelp();
        } else if (message.startsWith("todo")) {
            return addTodoTask(message, things, storage);
        } else if (message.startsWith("deadline")) {
            return addDeadlineTask(message, things, storage);
        } else if (message.startsWith("event")) {
            return addEventTask(message, things, storage);
        } else if (message.startsWith("find")) {
            return findTasks(message, things);
        } else {
            return ui.showError(new MossException("OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Lists all tasks in the given list.
     *
     * @param things The list of tasks to be listed.
     * @return A formatted string containing the list of tasks.
     */
    private static String listTasks(ArrayList<Task> things) {
        String temp = "";
        String output = "";
        for (int i = 0; i < things.size(); i++) {
            temp += (i + 1) + (".") + (things.get(i).toString("x")) + "\n";
        }
        output += "___________________________________________________________\n"
                + "Here are the tasks in your list:\n"
                + temp + "\n"
                + "____________________________________________________________\n";
        return output;
    }

    /**
     * Marks a task as done.
     *
     * @param message The user command for marking a task.
     * @param things The list of tasks being managed.
     * @param storage The storage object for saving tasks.
     * @return A string indicating the result of marking the task as done.
     * @throws MossException If there's an issue with the command format or task management.
     */
    private static String markTask(String message, ArrayList<Task> things, Storage storage) throws MossException {
        String indexSubstring = message.substring(5);
        int index;
        String output = "";
        UI ui = new UI();
        try {
            index = Integer.parseInt(indexSubstring) - 1;
        } catch (NumberFormatException e) {
            return ui.showError(new MossException("Please follow the format: mark [task number]"));
        }
        output = things.get(index).markDone();
        storage.saveTasks(things);
        return output;
    }

    /**
     * Marks a task as undone.
     *
     * @param message The user command for marking a task as undone.
     * @param things The list of tasks being managed.
     * @param storage The storage object for saving tasks.
     * @return A string indicating the result of marking the task as undone.
     * @throws MossException If there's an issue with the command format or task management.
     */
    private static String unmarkTask(String message, ArrayList<Task> things, Storage storage) throws MossException {
        // Mark a task as undone
        String indexSubstring = message.substring(7);
        int index;
        String output = "";
        UI ui = new UI();
        try {
            index = Integer.parseInt(indexSubstring) - 1;
        } catch (NumberFormatException e) {
            return ui.showError(new MossException("Please follow the format: unmark [task number]"));
        }
        output += things.get(index).markUndone();
        storage.saveTasks(things);
        return output;
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param message The user command for deleting a task.
     * @param things The list of tasks being managed.
     * @param storage The storage object for saving tasks.
     * @return A string indicating the result of deleting the task.
     * @throws MossException If there's an issue with the command format or task management.
     */
    private static String deleteTask(String message, ArrayList<Task> things, Storage storage) throws MossException {
        // Delete a task
        String indexSubstring = message.substring(7);
        int index;
        String output = "";
        UI ui = new UI();
        try {
            index = Integer.parseInt(indexSubstring) - 1;
        } catch (NumberFormatException e) {
            return ui.showError(new MossException("Please follow the format: delete [task number]"));
        }
        Task temp = things.remove(index);
        output += "___________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + temp.toString() + "\n"
                + "Now you have " + things.size() + " tasks in the list.\n"
                + "________________________________________________________\n";
        storage.saveTasks(things);
        return output;
    }

    /**
     * Displays a help message listing all available commands and their usage.
     *
     * @return A string containing the help message.
     */
    private static String displayHelp() {
        String output = "";
        output += "________________________________________________________\n"
                + "Here are the available commands:\n"
                + "1. todo <description>: Adds a new todo task.\n"
                + "2. deadline <description> /by <date>: Adds a new deadline task.\n"
                + "3. event <description> /from <start date> /to <end date>: Adds a new event task.\n"
                + "4. list: Lists all tasks.\n"
                + "5. mark <task number>: Marks a task as done.\n"
                + "6. unmark <task number>: Marks a task as undone.\n"
                + "7. delete <task number>: Deletes a task.\n"
                + "8. find <keyword>: Finds tasks containing the specified keyword in their description.\n"
                + "9. bye: Exits the application.\n"
                + "Use the commands without '<>' and replace <description>, <date>, <start date>, <end date>, and <task number> accordingly.\n"
                + "Example usage: todo Buy groceries";
        return output;
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param message The user command for adding a todo task.
     * @param things The list of tasks being managed.
     * @param storage The storage object for saving tasks.
     * @return A string indicating the result of adding the todo task.
     * @throws MossException If there's an issue with the command format or task management.
     */
    private static String addTodoTask(String message, ArrayList<Task> things, Storage storage) throws MossException {
        // Add a todo task
        // check if the command is valid otherwise throw errors
        String output = "";
        UI ui = new UI();
        if (message.length() <= 5) {
            return ui.showError(new MossException("OOPS!!! The description of a todo cannot be empty."));
        }
        ToDo task = new ToDo(message.substring(5));
        things.add(task);
        storage.saveTasks(things);

        // Provide feedback about the added task
        output += "________________________________________________________\n"
                + "Got it. I've added this task: \n"
                + things.get(things.size() - 1).toString() + "\n"
                + "Now you have " + things.size() + " tasks in the list.\n"
                +"________________________________________________________\n";

        return output;
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param message The user command for adding a deadline task.
     * @param things The list of tasks being managed.
     * @param storage The storage object for saving tasks.
     * @return A string indicating the result of adding the deadline task.
     */
    private static String addDeadlineTask(String message, ArrayList<Task> things, Storage storage) {
        // Add a deadline task
        String output = "";
        UI ui = new UI();
        // Find the position of "/by" in the input
        if (message.length() <= 9) {
            return ui.showError(new MossException("OOPS!!! The description of a deadline cannot be empty."));
        }
        try {
            int byIndex = message.indexOf("/by");

            // Extract the substring after "/by"
            String deadlineInfo = message.substring(byIndex + 4).trim();

            // Split the deadlineInfo by space to get individual parts
            String[] parts = deadlineInfo.split(" ");

            // The day is the last part of the parts array
            String day = parts[parts.length - 1];
            LocalDate date = LocalDate.parse(day);
            // Extract the substring before "/by"
            String taskDescription = message.substring(9, byIndex).trim();

            Deadline task = new Deadline(taskDescription, date);
            things.add(task);
            storage.saveTasks(things);

            // Provide feedback about the added task
            output += "____________________________________________________________\n"
                    + "Got it. I've added this task: \n"
                    + things.get(things.size() - 1).toString() + "\n"
                    + "Now you have " + things.size() + " tasks in the list.\n"
                    + "____________________________________________________________\n";

            return output;
        } catch (Exception e) {
            return ui.showError(new MossException("Please follow the format: deadline [description] /by [YYYY-MM-DD]"));
        }
    }

    /**
     * Adds an event task to the list of tasks.
     *
     * @param message The user command for adding an event task.
     * @param things The list of tasks being managed.
     * @param storage The storage object for saving tasks.
     * @return A string indicating the result of adding the event task.
     */
    private static String addEventTask(String message, ArrayList<Task> things, Storage storage) {
        // Add an event task
        String output = "";
        UI ui = new UI();
        if (message.length() <= 6) {
            return ui.showError(new MossException("OOPS!!! The description of a event cannot be empty."));
        }
        try {
            int byIndex = message.indexOf("/from");

            int fromIndex = message.indexOf("/from");
            int toIndex = message.indexOf("/to");

            // Extract the substring between "/from" and "/to" and behind "to"
            String from = message.substring(fromIndex + 5, toIndex).trim();
            LocalDate fromDate = LocalDate.parse(from);

            String to = message.substring(toIndex + 3).trim();
            LocalDate toDate = LocalDate.parse(to);

            String taskDescription = message.substring(6, byIndex).trim();

            Event task = new Event(taskDescription, fromDate, toDate);
            things.add(task);
            storage.saveTasks(things);

            // Provide feedback about the added task
            output += "____________________________________________________________\n"
                    + "Got it. I've added this task: \n"
                    + things.get(things.size() - 1).toString() + "\n"
                    + "Now you have " + things.size() + " tasks in the list.\n"
                    + "____________________________________________________________\n";

            return output;
        } catch (Exception e) {
            return ui.showError(new MossException("Please follow the format: event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]"));
        }
    }

    /**
     * Finds tasks in the list that match a given keyword.
     *
     * @param message The user command for finding tasks.
     * @param things The list of tasks being managed.
     * @return A string containing the matching tasks based on the provided keyword.
     */
    private static String findTasks(String message, ArrayList<Task> things) {
        String taskDescription = message.substring(5);
        int count = 0;
        String output = "";
        output += "________________________________________________________\n"
                + "Here are the matching tasks in your list: \n";
        for (Task thing : things) {
            if (thing.getDescription().contains(taskDescription)) {
                count++;
                output += count + ". " + thing.toString("") + "\n";
            }
        }
        if (count == 0) {
            output += "There is no matching task.\n";
        }
        return output;
    }

}
