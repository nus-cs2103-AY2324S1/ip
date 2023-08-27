import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A command line app </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class Duke {
    private static final String BYE_FLAG = "bye";
    private static final String LIST_FLAG = "list";
    private static final String MARK_FLAG = "mark";
    private static final String UNMARK_FLAG = "unmark";
    private static final String TODO_FLAG = "todo";
    private static final String DEADLINE_FLAG = "deadline";
    private static final String EVENT_FLAG = "event";
    private static final String DELETE_FLAG = "delete";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello from\n" + logo;
        printOutput(welcomeMsg);

        // greet the users
        String greetings = "Hello! I'm Orion\n"
                + "What can I do for you?\n";
        printOutput(greetings);

        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = DiskManager.loadFromDisk();

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();

            // skip past empty lines
            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+", 2);
            String command = parts[0];
            String value = parts.length >= 2 ? parts[1].trim() : "";

            try {
                if (command.equals(BYE_FLAG)) {
                    if (!value.isEmpty()) {
                        throw new DukeException("Oops!!! The bye command should not be followed by any description");
                    }

                    printOutput("Bye. Hope to see you again soon!\n");
                    break;
                }

                if (command.equals(LIST_FLAG)) {
                    if (!value.isEmpty()) {
                        throw new DukeException("Oops!!! The list command should not be followed by any description");
                    }

                    String output = taskManager.listTasks();
                    printOutput(output);
                    continue;
                }

                if (command.equals(MARK_FLAG)) {
                    Scanner tempSc = new Scanner(value);

                    if (!tempSc.hasNextInt()) {
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of a mark command");
                    }

                    int index = tempSc.nextInt();
                    if (tempSc.hasNext()) {
                        // means invalid formatting for done command
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of a mark command");
                    }

                    String output = taskManager.markTask(index, true);
                    printOutput(output);
                    tempSc.close();
                    continue;
                }

                if (command.equals(UNMARK_FLAG)) {
                    Scanner tempSc = new Scanner(value);

                    if (!tempSc.hasNextInt()) {
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of an unmark command");
                    }

                    int index = tempSc.nextInt();
                    if (tempSc.hasNext()) {
                        // means invalid formatting for done command
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of an unmark command");
                    }

                    String output = taskManager.markTask(index, false);
                    printOutput(output);
                    tempSc.close();
                    continue;
                }

                if (command.equals(TODO_FLAG)) {
                    if (value.isEmpty()) {
                        throw new DukeException("Oops!!! The description of a todo task cannot be empty");
                    }

                    String output = taskManager.addTask(new Todo(value));
                    printOutput(output);
                    continue;
                }

                if (command.equals(DEADLINE_FLAG)) {

                    String[] tempParts = value.split("/by");

                    // did not provide the /by argument
                    if (tempParts.length < 2) {
                        throw new DukeException("Oops!!! You forgot to provide a deadline for the deadline task");
                    }

                    String taskName = tempParts[0].trim();
                    String deadline = tempParts[1].trim();
                    if (taskName.isEmpty()) {
                        throw new DukeException("Oops!!! The description of a deadline task cannot be empty");
                    }
                    if (deadline.isEmpty()) {
                        throw new DukeException("Oops!!! You forgot to provide a deadline for the deadline task");
                    }

                    LocalDate date;
                    try {
                        date = LocalDate.parse(deadline);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Oops!! the date format of deadline is incorrect, "
                                + "please use the format yyyy-mm-dd");
                    }
                    String output = taskManager.addTask(new Deadline(taskName, date));
                    printOutput(output);
                    continue;
                }

                if (command.equals(EVENT_FLAG)) {
                    String[] tempParts = value.split("/from|/to");

                    // did not provide the /from /to arguments
                    if (tempParts.length < 3) {
                        throw new DukeException("Oops!!! Please provide a proper period for the event task");
                    }

                    String taskName = tempParts[0].trim();
                    String start = tempParts[1].trim();
                    String end = tempParts[2].trim();
                    if (taskName.isEmpty()) {
                        throw new DukeException("Oops!!! The description of an event task cannot be empty");
                    }
                    if (start.isEmpty() || end.isEmpty()) {
                        throw new DukeException("Oops!!! Please provide a proper period for the event task");
                    }

                    LocalDate startDate;
                    LocalDate endDate;
                    try {
                        startDate = LocalDate.parse(start);
                        endDate = LocalDate.parse(end);
                        if (endDate.isBefore(startDate)) {
                            throw new DukeException("Oops!!! End date of an event should "
                                    + "not be earlier than the start date.");
                        }
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Oops!! the date format of event is incorrect, "
                                + "please use the format yyyy-mm-dd");
                    }

                    String output = taskManager.addTask(new Event(taskName, startDate, endDate));
                    printOutput(output);
                    continue;
                }

                if (command.equals(DELETE_FLAG)) {
                    Scanner tempSc = new Scanner(value);
                    if (!tempSc.hasNextInt()) {
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of a delete command");
                    }

                    int index = tempSc.nextInt();
                    if (tempSc.hasNext()) {
                        // means invalid formatting for done command
                        tempSc.close();
                        throw new DukeException("Oops!!! Invalid argument of a delete command");
                    }

                    String output = taskManager.deleteTask(index);
                    printOutput(output);
                    tempSc.close();
                    continue;

                }


                throw new DukeException("Oops!!! I'm sorry, but I don't know what that means :-(");

            } catch (DukeException exc) {
                printOutput(exc.getMessage() + "\n");
            }
        }
        sc.close();
    }

    private static void drawLine() {
        int lineLength = 60; // Adjust the length of the line as needed
        char horizontalLineChar = '\u2500'; // Unicode character for a horizontal line

        for (int i = 0; i < lineLength; i++) {
            System.out.print(horizontalLineChar);
        }
        System.out.println();
    }

    private static void printOutput(String output) {
        System.out.println(output);
        drawLine();
    }
}
