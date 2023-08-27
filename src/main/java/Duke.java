import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Constructor for Duke
    public Duke() {
        this.list = new ArrayList<>();
    }

    // Constants
    private static final String name = "Beep Boop";
    private static final String line = "─".repeat(100);

    // Fields
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;

    public void printMessage(String message) {
        System.out.printf("\t%s\n", message);
        System.out.println(line);
    }

    public void greet() {
        String greeting = String.format("Hello! I'm %s\n\tWhat can I do for you?\n", name);
        System.out.println(line);
        printMessage(greeting);
    }

    public void exit() {
        String exiting = "Bye Bye. Hope to see you again soon! Beep Boop!\n";
        printMessage(exiting);
    }

    public void addToList(Task task) {
        list.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n", task.toString(), list.size());
        System.out.println(line);
    }

    private void deleteFromList(int index) {
        Task task = list.get(index);
        list.remove(index);
        System.out.println("\tNoted. I've removed this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n", task.toString(), list.size());
        System.out.println(line);
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i =0; i < list.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, list.get(i));
        }
        System.out.println(line);
    }

    private void validateToDo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private void validateDeadline(String[] deadlineString) throws DukeException {
        if (deadlineString.length != 2 || deadlineString[0].isBlank() || deadlineString[1].isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! Please make sure that the description and date of the deadline is not empty.");
        }
    }

    private void validateEvent(String[] eventString) throws DukeException {
        if (eventString.length != 3 || eventString[0].isBlank() || eventString[1].isBlank() || eventString[2].isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! Please make sure that the description and dates of the event is not empty.");
        }
    }

    private void validateMarkOrUnmarkorDelete(String number) throws DukeException {
        if (number.isBlank()) {
            throw new DukeException("Boop Beep OOPS!!! Please make sure that the index of the task is not empty.");
        } else {
            try {
                int numberInt = Integer.parseInt(number);
                if (numberInt <= 0 || numberInt > list.size()) {
                    throw new DukeException("Boop Beep OOPS!!! Please make sure that the index of the task is within range.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Boop Beep OOPS!!! Please make sure that the index of the task is an integer.");
            }
        }
    }
    public void runDuke() {
        greet();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    isDone = true;
                } else if (input.equals("list")) {
                    printList();
                } else if (input.startsWith("mark")) {
                    String number = input.replaceFirst("mark", "").trim();
                    validateMarkOrUnmarkorDelete(number);

                    int index = Integer.parseInt(number) - 1;
                    list.get(index).markDone();
                    String markDoneMessage = String.format("\t%s", list.get(index).toString());

                    System.out.println("\tBeep Boop Beep! I've marked this task as done:");
                    printMessage(markDoneMessage);
                } else if (input.startsWith("unmark")) {
                    String number = input.replaceFirst("unmark", "").trim();
                    validateMarkOrUnmarkorDelete(number);

                    int index = Integer.parseInt(number) - 1;
                    list.get(index).markNotDone();
                    String markNotDoneMessage = String.format("\t%s", list.get(index).toString());

                    System.out.println("\tBeep Boop Boop! I've marked this task as not done yet:");
                    printMessage(markNotDoneMessage);
                } else if (input.startsWith("todo")) {
                    String description = input.replaceFirst("todo", "").trim();
                    validateToDo(description);

                    ToDo todo = new ToDo(description);
                    addToList(todo);
                } else if (input.startsWith("deadline")) {
                    String[] deadlineString = input.replaceFirst("deadline", "").split("/", 2);
                    validateDeadline(deadlineString);

                    String description = deadlineString[0].trim();
                    String deadlineDate = deadlineString[1].replaceFirst("by", "").trim();

                    Deadline deadline = new Deadline(description, deadlineDate);
                    addToList(deadline);
                } else if (input.startsWith("event")) {
                    String[] eventString = input.replaceFirst("event", "").split("/", 3);
                    validateEvent(eventString);

                    String description = eventString[0].trim();
                    String start = eventString[1].replaceFirst("from", "").trim();
                    String end = eventString[2].replaceFirst("to", "").trim();

                    Event event = new Event(description, start, end);
                    addToList(event);
                } else if (input.startsWith("delete")) {
                    String number = input.replaceFirst("delete", "").trim();
                    validateMarkOrUnmarkorDelete(number);

                    int index = Integer.parseInt(number) - 1;
                    deleteFromList(index);
                } else {
                    throw new DukeException("Boop Beep OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }

        exit();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
