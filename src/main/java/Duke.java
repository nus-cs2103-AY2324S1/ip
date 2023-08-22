import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String welcome = " ────────────────────────────────────────\n"
                + "  Hello! I'm Handsome\n"
                + "  What can I do for you?\n"
                + " ────────────────────────────────────────";
        String goodbye = " ────────────────────────────────────────\n"
                + "  Bye. Hope to see you again soon!\n"
                + " ────────────────────────────────────────";
        System.out.println(welcome);
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                int index = 1;
                System.out.println(" ────────────────────────────────────────\n"
                        + "Here are the tasks in your list:");
                for (Task task : tasks) {
                    System.out.println(String.format(" %d. %s ", index, task.toString()));
                    index++;
                }
                System.out.println(" ────────────────────────────────────────");
            } else {
                try {
                String[] substrings = input.split(" ", 2);
                String command = substrings[0];
                    switch (command) {
                        case "mark":
                            int markTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (markTaskId >= 0 && markTaskId < tasks.size()) {
                                tasks.get(markTaskId).markAsDone();
                            } else {
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
                                System.out.println(" ────────────────────────────────────────\n"
                                        + message
                                        + "\n ────────────────────────────────────────");
                            }
                            break;
                        case "unmark":
                            int unmarkTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (unmarkTaskId >= 0 && unmarkTaskId < tasks.size()) {
                                tasks.get(unmarkTaskId).markAsUndone();
                            } else {
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
                                System.out.println(" ────────────────────────────────────────\n"
                                        + message
                                        + "\n ────────────────────────────────────────");
                            }
                            break;
                        case "todo":
                            String todoDesc = substrings[1];
                            Todo todo = new Todo(todoDesc);
                            tasks.add(todo);
                            System.out.println(" ────────────────────────────────────────\n"
                                    + " Got it. I've added this task:\n"
                                    + "     " + todo
                                    + "\n Now you have " + tasks.size() + " tasks in the list.\n"
                                    + " ────────────────────────────────────────");
                            break;
                        case "deadline":
                            String[] details = substrings[1].split("/by", 2);
                            Deadline deadline = new Deadline(details[0], details[1]);
                            tasks.add(deadline);
                            System.out.println(" ────────────────────────────────────────\n"
                                    + " Got it. I've added this task:\n"
                                    + "     " + deadline
                                    + "\n Now you have " + tasks.size() + " tasks in the list.\n"
                                    + " ────────────────────────────────────────");
                            break;
                        case "event":
                            String[] eventDetails = substrings[1].split("/from", 2);
                            String[] eventTimings = eventDetails[1].split("/to", 2);
                            Event event = new Event(eventDetails[0], eventTimings[0], eventTimings[1]);
                            tasks.add(event);
                            System.out.println(" ────────────────────────────────────────\n"
                                    + " Got it. I've added this task:\n"
                                    + "     " + event
                                    + "\n Now you have " + tasks.size() + " tasks in the list.\n"
                                    + " ────────────────────────────────────────");
                            break;
                    }
                } catch (NumberFormatException e) { // invalid argument is entered for mark and unmark eg. "mark a"
                    System.out.println(" ────────────────────────────────────────\n"
                            + "Invalid command! Please enter only one valid task ID\n"
                            + " ────────────────────────────────────────");
                } catch (IndexOutOfBoundsException e) { // when user only inputs the command eg "mark" or "deadline"
                    System.out.println(" ────────────────────────────────────────\n"
                            + "Invalid command! Please enter valid task details or a valid task ID\n"
                            + " ────────────────────────────────────────");
                }
            }
        }
        System.out.println(goodbye);
    }
}
