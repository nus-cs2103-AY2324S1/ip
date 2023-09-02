package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//CHECKSTYLE.OFF: MissingJavadocMethodCheck

/**
 * Class that is responsible for handling the input of the user
 */
public class Parser {

    /**
     * Read the input from the user and carry out the required action for text-based UI
     *
     * @param input input from the user
     * @param list TaskList created
     * @param number number of task in the list
     * @param filePath path of the storage file
     * @param ui the UI class response for handling ui actions
     * @param storage storage class that handles the loading and saving of files
     * @throws DukeException if the input does not much the required format
     */
    public static void parseInput(String input, Launcher.TaskList list, int number,
                                  String filePath, Ui ui, Storage storage) throws DukeException {

        if (input.equalsIgnoreCase("bye")) {
            ui.showGoodbyeMessage();
            storage.saveTasksToFile(list.task());
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            ui.print(list.listTasks());
        } else if (input.startsWith("mark")) {
            ui.print(list.mark(input, number));
        } else if (input.startsWith("unmark")) {
            list.unmark(input, number);
        } else if (input.startsWith("delete")) {
            number = 1;
        } else if (input.equalsIgnoreCase("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.equalsIgnoreCase("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (input.equalsIgnoreCase("event")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (input.equalsIgnoreCase("find")) {
            throw new DukeException("☹ OOPS!!! The description of find cannot be empty.");
        } else if (input.startsWith("find")) {
            ui.print(list.find(input));
        } else if (input.startsWith("todo")) {
            String task = input.substring(5).trim();
            ToDo todo = new ToDo(task);
            list.add(todo);
            number++;
            ui.print("Got it. I've added this task:\n " + todo.toString());
            System.out.println("Now you have " + (number) + " tasks in the list.");

        } else if (input.startsWith("deadline")) {
            // Implement deadline logic here
            int byIndex = input.indexOf("/by");
            if (byIndex != -1) {
                LocalDate d1;
                LocalTime t1 = null;
                Deadline deadline;
                String task = input.substring(9, byIndex).trim(); // Task description
                String date = input.substring(byIndex + 3).trim(); // Deadline day
                if (date.contains(" ")) {
                    String[] parts = date.split(" ");
                    String dateString = parts[0];
                    String timeString = parts[1];
                    d1 = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    t1 = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
                    deadline = new Deadline(task, d1, t1);
                } else {
                    d1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    deadline = new Deadline(task, d1, t1);
                }
                list.add(deadline);
                number++;
                System.out.println("Got it. I've added this task:\n" + deadline.toString());
                System.out.println("Now you have " + (number) + " tasks in the list.");
            } else {
                throw new DukeException("Invalid input format.");
            }
        } else if (input.startsWith("event")) {
            // Implement event logic here
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex != -1 && toIndex != -1) {
                String task = input.substring(6, fromIndex).trim(); // Task description
                String startDate = input.substring(fromIndex + 6, toIndex).trim(); // Start date
                String endDate = input.substring(toIndex + 4).trim(); // End date
                LocalDate d1 = LocalDate.parse(startDate);
                LocalDate d2 = LocalDate.parse(endDate);

                Events event = new Events(task, d1, d2);
                list.add(event);
                number++;
                System.out.println("Got it. I've added this task:\n" + event.toString());
                System.out.println("Now you have " + (number) + " tasks in the list.");
            } else {
                throw new DukeException("Invalid input format.");
            }
        } else {
            ui.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }



    /**
     * Read the input from the user and carry out the required action for Gui
     *
     * @param input input from the user
     * @param list TaskList created
     * @param number number of task in the list
     * @param filePath path of the storage file
     * @param ui the UI class response for handling ui actions
     * @param storage storage class that handles the loading and saving of files
     * @throws DukeException if the input does not much the required format
     */
    public static void parseInput(String input, Launcher.TaskList list, int number,
                                  String filePath, Gui ui, Storage storage) throws DukeException {

        if (input.equalsIgnoreCase("bye")) {
            ui.showGoodbyeMessage();
            storage.saveTasksToFile(list.task());
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            ui.print(list.listTasks());
        } else if (input.startsWith("mark")) {
            ui.print(list.mark(input, number));
        } else if (input.startsWith("unmark")) {
            list.unmark(input, number);
        } else if (input.startsWith("delete")) {
            number = list.delete(input, number, ui);
        } else if (input.equalsIgnoreCase("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.equalsIgnoreCase("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (input.equalsIgnoreCase("event")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (input.equalsIgnoreCase("find")) {
            throw new DukeException("☹ OOPS!!! The description of find cannot be empty.");
        } else if (input.startsWith("find")) {
            ui.print(list.find(input));
        } else if (input.startsWith("todo")) {
            String task = input.substring(5).trim();
            ToDo todo = new ToDo(task);
            list.add(todo);
            number++;
            ui.print("Got it. I've added this task:\n" + todo.toString()
                    + " \n Now you have " + (number) + " tasks in the list.");

        } else if (input.startsWith("deadline")) {
            // Implement deadline logic here
            int byIndex = input.indexOf("/by");
            if (byIndex != -1) {
                LocalDate d1;
                LocalTime t1 = null;
                Deadline deadline;
                String task = input.substring(9, byIndex).trim(); // Task description
                String date = input.substring(byIndex + 3).trim(); // Deadline day
                if (date.contains(" ")) {
                    String[] parts = date.split(" ");
                    String dateString = parts[0];
                    String timeString = parts[1];
                    d1 = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    t1 = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
                    deadline = new Deadline(task, d1, t1);
                } else {
                    d1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    deadline = new Deadline(task, d1, t1);
                }
                list.add(deadline);
                number++;
                ui.print("Got it. I've added this task:\n" + deadline.toString()
                        + "\n Now you have " + (number) + " tasks in the list.");
            } else {
                throw new DukeException("Invalid input format.");
            }
        } else if (input.startsWith("event")) {
            // Implement event logic here
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex != -1 && toIndex != -1) {
                String task = input.substring(6, fromIndex).trim(); // Task description
                String startDate = input.substring(fromIndex + 6, toIndex).trim(); // Start date
                String endDate = input.substring(toIndex + 4).trim(); // End date
                LocalDate d1 = LocalDate.parse(startDate);
                LocalDate d2 = LocalDate.parse(endDate);

                Events event = new Events(task, d1, d2);
                list.add(event);
                number++;
                ui.print("Got it. I've added this task:\n" + event.toString()
                        + "\n Now you have " + (number) + " tasks in the list.");
            } else {
                throw new DukeException("Invalid input format.");
            }
        } else {
            ui.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

