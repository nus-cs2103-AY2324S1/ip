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
     * @throws MossException If there's an issue with processing commands or managing tasks.
     */
    public static String command(String message, ArrayList<Task> things, Storage storage) throws MossException {
        String output = "";
        if (message.equals("bye")){
            UI ui = new UI();
            return ui.bye();
        }
        if (message.equals("list")) {
            // List all tasks
            String temp = "";
            for (int i = 0; i < things.size(); i++) {
                temp += (i + 1) + (".") + (things.get(i).toString("x"));
            }
            output += "___________________________________________________________\n"
                    + "Here are the tasks in your list:\n"
                    + temp + "\n"
                    + "____________________________________________________________\n";
            return output;
        } else if (message.startsWith("mark")) {
            // Mark a task as done
            String indexSubstring = message.substring(5);
            int index = Integer.parseInt(indexSubstring) - 1;
            output = things.get(index).markDone();
            storage.saveTasks(things);
            return output;
        } else if (message.startsWith("unmark")) {
            // Mark a task as undone
            String indexSubstring = message.substring(7);
            int index = Integer.parseInt(indexSubstring) - 1;
            output += things.get(index).markUndone();
            storage.saveTasks(things);
            return output;
        } else if (message.startsWith("delete")) {
            // Delete a task
            String indexSubstring = message.substring(7);
            int index = Integer.parseInt(indexSubstring) - 1;
            Task temp = things.remove(index);
            output += "___________________________________________________________\n"
                    + "Noted. I've removed this task:\n"
                    + temp.toString() + "\n"
                    + "Now you have " + things.size() + " tasks in the list.\n"
                    + "________________________________________________________\n";
            storage.saveTasks(things);
            return output;
        } else {
            // Process other commands using the command method
            if (message.startsWith("todo")) {
                // check if the command is valid otherwise throw errors
                if (message.length() <= 5) {
                    throw new MossException("OOPS!!! The description of a todo cannot be empty.");
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
            // Add a deadline task
            else if (message.startsWith("deadline")) {
                // Find the position of "/by" in the input
                if (message.length() <= 9) {
                    throw new MossException("OOPS!!! The description of a todo cannot be empty.");
                }
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
            }
            // Add an event task
            else if (message.startsWith("event")) {
                if (message.length() <= 6) {
                    throw new MossException("OOPS!!! The description of a todo cannot be empty.");
                }
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
            }
            else if (message.startsWith("find")) {
                String taskDescription = message.substring(5);
                int count = 0;
                output += "________________________________________________________\n"
                        + "Here are the matching tasks in your list: \n";
                for (int i = 0; i < things.size(); i++) {
                    if (things.get(i).getDescription().contains(taskDescription)) {
                        count++;
                        output += count + ". " + things.get(i).toString("") + "\n";
                    }
                }
                if (count == 0) {
                    output += "There is no matching task.\n";
                }
                return output;
            }
            // check if the command is valid otherwise throw errors
            else {
                // Invalid command
                throw new MossException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
