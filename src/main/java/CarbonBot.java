import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarbonBot {
    private static String DIVIDER = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.println(DIVIDER);
        System.out.println("Hello! I'm CarbonBot");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while(true) {
            String input = sc.nextLine();

            // Do not process if the input was empty
            if (input.isEmpty()) {
                continue;
            }

            String cmd = input.split(" ")[0];
            int taskIdx;
            String desc;

            System.out.println(DIVIDER);
            switch(cmd) {
                case "bye":
                    // Exit the program if the command is "bye"
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(DIVIDER);
                    sc.close();
                    return;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    printList(taskList);
                    break;
                case "todo":
                    // Get the description from all the characters after "todo "
                    desc = input.substring("todo ".length() - 1, input.length());

                    // Validates if the description is empty (or only whitespaces)
                    if (desc.isBlank()) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        addTask(taskList, new Todo(desc));
                    }
                    break;
                case "deadline":
                    int indexOfBy = input.indexOf("/by");
                    // Validates the existence of /by syntax
                    if (indexOfBy == -1) {
                        System.out.println("☹ OOPS!!! Please specify the deadline using /by.");
                        break;
                    }

                    desc = input.substring("deadline ".length(), indexOfBy).trim();
                    String by = input.substring(indexOfBy + "/by ".length(), input.length());
                    if (desc.isBlank()) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        break;
                    }
                    if (by.isBlank()) {
                        System.out.println("☹ OOPS!!! The 'by' of a deadline cannot be empty.");
                        break;
                    }

                    addTask(taskList, new Deadline(desc, by));
                    break;
                case "event":
                    int indexOfFrom = input.indexOf("/from");
                    int indexOfTo = input.indexOf("/to");
                    if (indexOfFrom == -1 || indexOfTo == -1) {
                        System.out.println("☹ OOPS!!! Please specify the start and end of the" +
                                " event using /from and /to.");
                        break;
                    }

                    desc = input.substring("event ".length(), indexOfFrom).trim();
                    String from = input.substring(indexOfFrom + "/from ".length(), indexOfTo);
                    String to = input.substring(indexOfTo + "/to ".length(), input.length());

                    if (desc.isBlank()) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        break;
                    }
                    if (from.isBlank()) {
                        System.out.println("☹ OOPS!!! The 'from' of an event cannot be empty.");
                        break;
                    }
                    if (to.isBlank()) {
                        System.out.println("☹ OOPS!!! The 'to' of an event cannot be empty.");
                        break;
                    }

                    addTask(taskList, new Event(desc, from, to));
                    break;
                case "mark":
                    updateTaskStatus(taskList, input, true);
                    break;
                case "unmark":
                    updateTaskStatus(taskList, input, false);
                    break;
                default:
                    // Unrecognised Command
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("My supported commands are: list, mark, " +
                            "unmark, todo, deadline, event, bye.");
            }
            System.out.println(DIVIDER);
        }
    }

    // Adds a todo task to the list
    private static void addTask(List<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(getListSize(tasks));
    }

    // Get number of tasks in the list
    private static String getListSize(List<Task> tasks) {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    // Lists all the commands saved in the arraylist
    private static void printList(List<Task> tasks) {
        int idx = 1;
        for(Task t : tasks) {
            System.out.println(String.format("%d.%s", idx, t));
            idx++;
        }
    }

    // Marks the Task as Done or Not Done
    private static void updateTaskStatus(List<Task> tasks, String input, boolean isDone) {
        // Check if the user has specified the index to be updated
        if (input.split(" ").length < 2) {
            System.out.println("Please provide the task index to be updated.");
            return;
        }

        try {
            int taskIdx = Integer.parseInt(input.split(" ")[1]);
            if (taskIdx > 0 && taskIdx <= tasks.size()) {
                // The -1 is because of the list 0-indexing
                Task t = tasks.get(taskIdx - 1);

                if (isDone) {
                    // Mark task as done
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    // Mark task as not done
                    t.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                System.out.println(t);
            } else {
                // Do not process the command if the index was out of bounds
                System.out.println("Index given was out-of-bounds");
            }
        } catch (NumberFormatException ex) {
            // Ensure the index is integer
            System.out.println("Please provide a valid integer index.");
        }

    }

}
