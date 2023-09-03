import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Ui ui = new Ui();
    
    public static void addToList(String input, Storage storage, TaskList tasks) {
        if (input.startsWith("todo")) {
            try {
                if (input.trim().length() <= 4) {
                    throw new DukeException("\t Sorry! The description of a todo cannot be empty :(");
                }
                Task task = new ToDo(input.substring(5));
                tasks.addTask(task);
                ui.printAddTaskToList(tasks, task);
                storage.writeTasksToFile(tasks);
            }
            catch(DukeException d) {
                d.printMessage();
            }
            catch(IOException i) {

            }
            }



         else if (input.startsWith("deadline")) {
        try {
            if (input.trim().length() <= 8) {
                throw new DukeException("\t Sorry! The description of a deadline cannot be empty :(");
            }
            if (!input.contains("/by")) {
                throw new DukeException("\t Hey bud! Please include when the deadline is! " +
                        "\n\t For example you can type: deadline read /by 2023-09-01 1700");
            }
            int index = input.lastIndexOf("/by");
            Task task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
            tasks.addTask(task);
            ui.printAddTaskToList(tasks, task);
            storage.writeTasksToFile(tasks);

        }
        catch (DukeException e) {
            e.printMessage();
        }
        catch(DateTimeException d) {
            System.out.println("Please put a valid deadline in YYYY-MM-DD HHMM form." +
                    "\n\tFor example, 2003-19-08 1855");
        }
        catch(IOException i) {

        }

            }
         else if (input.startsWith("event")) {
        try {
            if (input.trim().length() <= 5) {
                throw new DukeException("\t Sorry! The description of an event cannot be empty :(");
            }
            if (!input.contains("/from")) {
                throw new DukeException("\t Hey bud! Please include when the event is!" +
                        "\n\t For example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
            }
            if (!input.contains("/to")) {
                throw new DukeException("\t Hey bud! Please include when the end date of the event is!" +
                        "\n\t For example you can type: event hangout /from 2023-09-01 1700 /to 2023-09-01 2000");
            }
            int indexFrom = input.lastIndexOf("/from");
            int indexTo = input.lastIndexOf("/to");
            Task task = new Event(input.substring(6, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1), input.substring(indexTo + 4));
            tasks.addTask(task);
            ui.printAddTaskToList(tasks, task);
            storage.writeTasksToFile(tasks);

        }
        catch(IOException i) {

        }
        catch (DukeException e) {
            e.printMessage();
        }
        catch(DateTimeException d) {
            System.out.println("Please put valid start and end dates in YYYY-MM-DD HHMM form." +
                    "For example, 2003-19-08 1855");
        }
            }

        else if (input.startsWith("mark")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5));
                    tasks.markTaskAsDone(taskIndex - 1);
                    ui.printMarkTasksAsDone(taskIndex, tasks);
                    storage.writeTasksToFile(tasks);
                }
                catch (IOException i) {
                    System.out.println(i);
                }

            } else if (input.startsWith("unmark")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7));
                    tasks.markTaskAsNotDone(taskIndex - 1);
                    ui.printMarkTasksAsNotDone(taskIndex, tasks);
                    storage.writeTasksToFile(tasks);
                }
                catch (IOException i) {
                    System.out.println(i);
                }
        } else if (input.startsWith("delete")) {
                try {
                    int pos = Integer.parseInt(input.substring(7).trim());
                    if (pos > tasks.getSize() || pos == 0) {
                        throw new DukeException("\tThis number is out of bounds! ");
                    }
                    Task element = tasks.getTask(pos - 1);
                    tasks.deleteTask(pos - 1);
                    ui.printDeleteTasks(pos, tasks, element);
                    storage.writeTasksToFile(tasks);
                }
                catch (DukeException e) {
                    e.printMessage();
                }
                catch (IOException i) {
                    System.out.println(i);
                }
        } else {
                try {
                    ui.printListMessage(tasks);
                }
                catch (DukeException e) {
                    e.printMessage();
                }
        }
    }

}