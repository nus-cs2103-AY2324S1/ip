import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printLine(String message) {
        System.out.println(" ────────────────────────────────────────\n"
                + message
                + "\n ────────────────────────────────────────");
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String welcome = "  Hello! I'm Handsome\n"
                + "  What can I do for you?";
        String goodbye = "  Bye. Hope to see you again soon!";
        printLine(welcome);
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
<<<<<<< HEAD
<<<<<<< HEAD
                            if (substrings.length < 2) { // user input only has the command eg "mark"
                                throw new DukeException("Invalid command! " +
                                        "Please include the index of the task you wish to mark");
=======
                            if (substrings.length < 2) {
                                throw new InvalidTaskIndexException("Invalid command!"
                                        + "Please include the index of the task you wish to mark");
>>>>>>> parent of 70c3407 (reorganised code and and removed exceptions that could be handled with if else statements)
=======
                            if (substrings.length < 2) { // user input only has the command eg "mark"
                                printLine("Invalid command! Please include the index of the task you wish to mark");
                                break;
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                            }
                            int markTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (markTaskId >= 0 && markTaskId < tasks.size()) {
                                tasks.get(markTaskId).markAsDone();
                            } else { // user input is an integer bigger than size of task list
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
<<<<<<< HEAD
<<<<<<< HEAD
                                throw new DukeException(message);
                            }
                            break;
                        case "unmark":
                            if (substrings.length < 2) { // user input only has the command eg "unmark"
                                throw new DukeException("Invalid command! " +
                                        "Please include the index of the task you wish to unmark");
=======
                                throw new InvalidTaskIndexException(message);
                            }
                            break;
                        case "unmark":
                            if (substrings.length < 2) {
                                throw new InvalidTaskIndexException("Invalid command!"
                                        + "Please include the index of the task you wish to unmark");
>>>>>>> parent of 70c3407 (reorganised code and and removed exceptions that could be handled with if else statements)
=======
                                printLine(message);
                            }
                            break;
                        case "unmark":
                            if (substrings.length < 2) { // user input only has the command eg "unmark"
                                printLine("Invalid command! Please include the index of the task you wish to unmark");
                                break;
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                            }
                            int unmarkTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (unmarkTaskId >= 0 && unmarkTaskId < tasks.size()) {
                                tasks.get(unmarkTaskId).markAsUndone();
                            } else { // user input is an integer bigger than size of task list
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
<<<<<<< HEAD
<<<<<<< HEAD
                                throw new DukeException(message);
                            }
                            break;
                        case "delete":
                            if (substrings.length < 2) { // user input only has the command eg "delete"
                                throw new DukeException("Invalid command! " +
                                        "Please include the index of the task you wish to delete");
=======
                                throw new InvalidTaskIndexException(message);
                            }
                            break;
                        case "delete":
                            if (substrings.length < 2) {
                                throw new InvalidTaskIndexException("Invalid command!"
                                        + "Please include the index of the task you wish to delete");
>>>>>>> parent of 70c3407 (reorganised code and and removed exceptions that could be handled with if else statements)
=======
                                printLine(message);
                            }
                            break;
                        case "delete":
                            if (substrings.length < 2) { // user input only has the command eg "delete"
                                printLine("Invalid command! Please include the index of the task you wish to delete");
                                break;
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                            }
                            int deleteTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (deleteTaskId >= 0 && deleteTaskId < tasks.size()) {
                                String details = tasks.get(deleteTaskId).toString();
                                tasks.remove(deleteTaskId);
                                String message = " Noted. I've removed this task:\n"
                                        + "     " + details
                                        + "\n Now you have " + tasks.size() + " tasks in the list.";
                                printLine(message);
                            } else { // user input is an integer bigger than size of task list
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
<<<<<<< HEAD
<<<<<<< HEAD
                                throw new DukeException(message);
                            }
                            break;
                        case "todo":
                            if (substrings.length != 2) { // user input only has the command
                                throw new DukeException("Invalid command! Please include details of this task");
=======
                                throw new InvalidTaskIndexException(message);
                            }
                            break;
                        case "todo":
                            if (substrings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include details of this task");
>>>>>>> parent of 70c3407 (reorganised code and and removed exceptions that could be handled with if else statements)
=======
                                printLine(message);
                            }
                            break;
                        case "todo":
                            if (substrings.length != 2) { // user input only has the command eg "todo"
                                printLine("Invalid command! Please include details of this task");
                                break;
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                            }
                            String todoDesc = substrings[1];
                            Todo todo = new Todo(todoDesc);
                            tasks.add(todo);
                            printLine(" Got it. I've added this task:\n"
                                    + "     " + todo
                                    + "\n Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "deadline":
<<<<<<< HEAD
<<<<<<< HEAD
                            if (substrings.length != 2) { // user input only has the command eg "deadline"
                                throw new DukeException("Invalid command! Please include details of this task");
                            }
                            String[] details = substrings[1].split("/by", 2);
                            if (details.length != 2) { // user input does not have /by
                                throw new DukeException("Invalid command! Please include the deadline of this task");
=======
                            if (substrings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include details of this task");
                            }
                            String[] details = substrings[1].split("/by", 2);
                            if (details.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include the deadline of this task");
>>>>>>> parent of 70c3407 (reorganised code and and removed exceptions that could be handled with if else statements)
=======
                            if (substrings.length != 2) { // user input only has the command eg "deadline"
                                printLine("Invalid command! Please include details of this task");
                                break;
                            }
                            String[] details = substrings[1].split("/by", 2);
                            if (details.length != 2) { // user input does not have /by
                                printLine("Invalid command! Please include the deadline of this task");
                                break;
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                            }
                            Deadline deadline = new Deadline(details[0], details[1]);
                            tasks.add(deadline);
                            printLine(" Got it. I've added this task:\n"
                                    + "     " + deadline
                                    + "\n Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "event":
<<<<<<< HEAD
<<<<<<< HEAD
                            if (substrings.length != 2) { // user input only has the command eg "event"
                                throw new DukeException("Invalid command! Please include details of this task");
                            }
                            String[] eventDetails = substrings[1].split("/from", 2);
                            if (eventDetails.length != 2) { // user input does not include /from
                                throw new DukeException("Invalid command! Please include when the event starts");
                            }
                            String[] eventTimings = eventDetails[1].split("/to", 2);
                            if (eventTimings.length != 2) { // user input does not include /to
                                throw new DukeException("Invalid command! Please include when the event ends");
=======
                            if (substrings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include details of this task");
=======
                            if (substrings.length != 2) { // user input only has the command eg "event"
                                printLine("Invalid command! Please include details of this task");
                                break;
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                            }
                            String[] eventDetails = substrings[1].split("/from", 2);
                            if (eventDetails.length != 2) { // user input does not include /from
                                printLine("Invalid command! Please include when the event starts");
                                break;
                            }
                            String[] eventTimings = eventDetails[1].split("/to", 2);
<<<<<<< HEAD
                            if (eventTimings.length != 2) {
                                throw new MissingTaskDetailsException("Invalid command! "
                                        + "Please include when the event ends");
>>>>>>> parent of 70c3407 (reorganised code and and removed exceptions that could be handled with if else statements)
=======
                            if (eventTimings.length != 2) { // user input does not include /to
                                printLine("Invalid command! Please include when the event ends");
                                break;
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                            }
                            Event event = new Event(eventDetails[0], eventTimings[0], eventTimings[1]);
                            tasks.add(event);
                            String message = " Got it. I've added this task:\n"
                                    + "     " + event
                                    + "\n Now you have " + tasks.size() + " tasks in the list.";
                            printLine(message);
                            break;
                        default:
<<<<<<< HEAD
<<<<<<< HEAD
                            throw new DukeException("Sorry! I do not recognise this command");
                    }
                } catch (NumberFormatException e) { // user inputs invalid argument for mark and unmark eg. "mark ab"
                    printLine("Invalid command! Please enter only one valid task ID");
                } catch (DukeException e) {
                    printLine(e.getMessage());
=======
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
>>>>>>> parent of 70c3407 (reorganised code and and removed exceptions that could be handled with if else statements)
=======
                            printLine("Sorry! I do not recognise this command");
                    }
                } catch (NumberFormatException e) { // user inputs invalid argument for mark and unmark eg. "mark ab"
                    printLine("Invalid command! Please enter only one valid task ID");
>>>>>>> 70c3407b0d609ed2fb2759555ad3096d44b6b2e6
                }
            }
        }
        printLine(goodbye);
    }
}
