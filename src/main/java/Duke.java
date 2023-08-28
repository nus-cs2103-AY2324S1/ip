import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printLine(String message) {
        System.out.println(" ----------------------------------------\n"
                + message
                + "\n ----------------------------------------");
    }

    public static void main(String[] args) {
        Storage storage = new Storage("./data/duke.txt");
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = storage.readFile();
        String welcome = "  Hello! I'm Handsome\n"
                + "  What can I do for you?";
        String goodbye = "  Bye. Hope to see you again soon!";
        printLine(welcome);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                int index = 1;
                System.out.println(" ----------------------------------------\n"
                        + "Here are the tasks in your list:");
                for (Task task : tasks) {
                    System.out.printf(" %d. %s%n", index, task.toString());
                    index++;
                }
                System.out.println(" ----------------------------------------");
            } else {
                try {
                    String[] substrings = input.split(" ", 2);
                    String command = substrings[0];
                    switch (command) {
                        case "mark":
                            // user input only has the command eg "mark"
                            if (substrings.length < 2 || substrings[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! " +
                                        "Please include the index of the task you wish to mark");
                            }
                            int markTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (markTaskId >= 0 && markTaskId < tasks.size()) {
                                tasks.get(markTaskId).markAsDone();
                                storage.writeToFile(tasks);
                            } else { // user input is an integer bigger than size of task list
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
                                throw new DukeException(message);
                            }
                            break;
                        case "unmark":
                            // user input only has the command eg "unmark"
                            if (substrings.length < 2 || substrings[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! " +
                                        "Please include the index of the task you wish to unmark");
                            }
                            int unmarkTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (unmarkTaskId >= 0 && unmarkTaskId < tasks.size()) {
                                tasks.get(unmarkTaskId).markAsUndone();
                                storage.writeToFile(tasks);
                            } else { // user input is an integer bigger than size of task list
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
                                throw new DukeException(message);
                            }
                            break;
                        case "delete":
                            // user input only has the command eg "delete"
                            if (substrings.length < 2 || substrings[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! " +
                                        "Please include the index of the task you wish to delete");
                            }
                            int deleteTaskId = Integer.parseInt(substrings[1]) - 1;
                            if (deleteTaskId >= 0 && deleteTaskId < tasks.size()) {
                                String details = tasks.get(deleteTaskId).toString();
                                tasks.remove(deleteTaskId);
                                storage.writeToFile(tasks);
                                String message = " Noted. I've removed this task:\n"
                                        + "     " + details
                                        + "\n Now you have " + tasks.size() + " tasks in the list.";
                                printLine(message);
                            } else { // user input is an integer bigger than size of task list
                                String message = tasks.size() > 0
                                        ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                        : "You have no tasks! Please add some tasks first";
                                throw new DukeException(message);
                            }
                            break;
                        case "todo":
                            // user input only has the command eg "todo"
                            if (substrings.length < 2 || substrings[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! Please include details of this task");
                            }
                            String todoDesc = substrings[1];
                            Todo todo = new Todo(todoDesc);
                            tasks.add(todo);
                            storage.writeToFile(tasks);
                            printLine(" Got it. I've added this task:\n"
                                    + "     " + todo
                                    + "\n Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "deadline":
                            // user input only has the command eg "deadline"
                            if (substrings.length < 2 || substrings[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! Please include details of this task");
                            }
                            String[] details = substrings[1].split("/by", 2);
                            if (details.length < 2 || details[1].trim().isEmpty()) { // user input does not have /by
                                throw new DukeException("Invalid command! Please include the deadline of this task");
                            }
                            if (details[0].split(" ", 2).length < 2) {
                                throw new DukeException("Invalid command! Please include details of this task");
                            }
                            LocalDateTime by = LocalDateTime.parse(details[1].trim(), inputFormatter);
                            Deadline deadline = new Deadline(details[0], by);
                            tasks.add(deadline);
                            storage.writeToFile(tasks);
                            printLine(" Got it. I've added this task:\n"
                                    + "     " + deadline
                                    + "\n Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "event":
                            // user input only has the command eg "event"
                            if (substrings.length < 2 || substrings[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! Please include details of this task");
                            }
                            String[] eventDetails = substrings[1].split("/from", 2);
                            // user input does not include /from
                            if (eventDetails.length < 2 || eventDetails[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! Please include when the event starts");
                            }
                            // user input does not include /from
                            if (eventDetails[0].split(" ", 2).length < 2) {
                                throw new DukeException("Invalid command! Please include details of this task");
                            }
                            String[] eventTimings = eventDetails[1].split("/to", 2);
                            // user input does not include /to
                            if (eventTimings.length < 2 || eventTimings[1].trim().isEmpty()) {
                                throw new DukeException("Invalid command! Please include when the event ends");
                            }
                            LocalDateTime from = LocalDateTime.parse(eventTimings[0].trim(), inputFormatter);
                            LocalDateTime to = LocalDateTime.parse(eventTimings[1].trim(), inputFormatter);
                            Event event = new Event(eventDetails[0], from, to);
                            tasks.add(event);
                            storage.writeToFile(tasks);
                            String message = " Got it. I've added this task:\n"
                                    + "     " + event
                                    + "\n Now you have " + tasks.size() + " tasks in the list.";
                            printLine(message);
                            break;
                        default:
                            throw new DukeException("Sorry! I do not recognise this command");
                    }
                } catch (NumberFormatException e) { // user inputs invalid argument for mark and unmark eg. "mark ab"
                    printLine("Invalid command! Please enter only one valid task ID");
                } catch (DateTimeParseException e) {
                    printLine("Invalid date and time format! Please use the format dd/mm/yyyy hhmm");
                } catch (DukeException e) {
                    printLine(e.getMessage());
                }
            }
        }
        printLine(goodbye);
    }
}

