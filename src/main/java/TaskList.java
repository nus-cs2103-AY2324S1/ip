import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    public static void command(String message, ArrayList<Task> things, Storage storage) throws MossException{
        if (message.equals("list")){
            // List all tasks
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < things.size(); i++) {
                System.out.println(i+1 + "." + things.get(i).toString("x"));
            }
            System.out.println("____________________________________________________________");
        }
        else if (message.startsWith("mark")) {
            // Mark a task as done
            String indexSubstring = message.substring(5);
            int index = Integer.parseInt(indexSubstring) - 1;
            things.get(index).markDone();
            storage.saveTasks(things);
        }
        else if (message.startsWith("unmark")) {
            // Mark a task as undone
            String indexSubstring = message.substring(7);
            int index = Integer.parseInt(indexSubstring) - 1;
            things.get(index).markUndone();
            storage.saveTasks(things);
        }
        else if (message.startsWith("delete")) {
            // Delete a task
            String indexSubstring = message.substring(7);
            int index = Integer.parseInt(indexSubstring) - 1;
            Task temp = things.remove(index);
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(temp.toString());
            System.out.println("Now you have " + things.size() + " tasks in the list.");
            storage.saveTasks(things);
        }
        else {
            // Process other commands using the command method
            if (message.startsWith("todo")) {
                // check if the command is valid otherwise throw errors
                if (message.length() <= 5) {
                    throw new MossException("OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo task = new ToDo(message.substring(5));
                things.add(task);
                storage.saveTasks(things);
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
            }
            // check if the command is valid otherwise throw errors
            else {
                // Invalid command
                throw new MossException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            // Provide feedback about the added task
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task: ");
            System.out.println(things.get(things.size() - 1).toString());
            System.out.println("Now you have " + things.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }
}