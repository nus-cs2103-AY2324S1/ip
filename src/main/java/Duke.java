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
                            if (substrings.length < 2) {
                                throw new InvalidTaskIndexException("Invalid command!"
                                        + "Please include the index of the task you wish to mark");
                            }
                            int markTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (markTaskId >= 0 && markTaskId < tasks.size()) {
                                tasks.get(markTaskId).markAsDone();
                            } else {
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
                                throw new InvalidTaskIndexException(message);
                            }
                            break;
                        case "unmark":
                            if (substrings.length < 2) {
                                throw new InvalidTaskIndexException("Invalid command!"
                                        + "Please include the index of the task you wish to unmark");
                            }
                            int unmarkTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (unmarkTaskId >= 0 && unmarkTaskId < tasks.size()) {
                                tasks.get(unmarkTaskId).markAsUndone();
                            } else {
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
                                throw new InvalidTaskIndexException(message);
                            }
                            break;
                        case "todo":
                            if (substrings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include details of this task");
                            }
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
                            if (substrings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include details of this task");
                            }
                            String[] details = substrings[1].split("/by", 2);
                            if (details.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include the deadline of this task");
                            }
                            Deadline deadline = new Deadline(details[0], details[1]);
                            tasks.add(deadline);
                            System.out.println(" ────────────────────────────────────────\n"
                                    + " Got it. I've added this task:\n"
                                    + "     " + deadline
                                    + "\n Now you have " + tasks.size() + " tasks in the list.\n"
                                    + " ────────────────────────────────────────");
                            break;
                        case "event":
                            if (substrings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include details of this task");
                            }
                            String[] eventDetails = substrings[1].split("/from", 2);
                            if (eventDetails.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include when the event starts");
                            }
                            String[] eventTimings = eventDetails[1].split("/to", 2);
                            if (eventTimings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include when the event ends");
                            }
                            Event event = new Event(eventDetails[0], eventTimings[0], eventTimings[1]);
                            tasks.add(event);
                            System.out.println(" ────────────────────────────────────────\n"
                                    + " Got it. I've added this task:\n"
                                    + "     " + event
                                    + "\n Now you have " + tasks.size() + " tasks in the list.\n"
                                    + " ────────────────────────────────────────");
                            break;
                        default:
                            throw new UnknownCommandException("Sorry! I do not recognise this command");
                    }
                } catch (NumberFormatException e) { // user inputs invalid argument for mark and unmark eg. "mark ab"
                    System.out.println(" ────────────────────────────────────────\n"
                            + "Invalid command! Please enter only one valid task ID\n"
                            + " ────────────────────────────────────────");
                } catch (InvalidTaskIndexException | MissingTaskDetailsException | UnknownCommandException e) {
                    System.out.println(" ────────────────────────────────────────\n"
                            + e.getMessage()
                            + "\n ────────────────────────────────────────");
                }
            }
        }
        System.out.println(goodbye);
    }
}
