package duke;

import duke.Exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.time.DateTimeException;

public class Parser {
    public static Ui ui = new Ui();

    public static void userCommand(String input, Storage storage, TaskList tasks) throws DukeException, NumberFormatException {
        try {
            if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5));
                if (tasks.getTask(taskIndex - 1).getStatusIcon() == "X") {
                    throw new DukeException("\tThis task has already been marked as done!");
                }
                tasks.markTaskAsDone(taskIndex - 1);
                storage.writeTasksToFile(tasks);
                ui.printMarkTasksAsDone(taskIndex, tasks);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7));
                if (tasks.getTask(taskIndex - 1).getStatusIcon() == " ") {
                    throw new DukeException("\tThis task has already been marked as not done!");
                }
                tasks.markTaskAsNotDone(taskIndex - 1);
                ui.printMarkTasksAsNotDone(taskIndex, tasks);
                storage.writeTasksToFile(tasks);

            } else if (input.startsWith("delete")) {
                int pos = Integer.parseInt(input.substring(7).trim());
                if (pos > tasks.getSize() || pos == 0) {
                    throw new DukeException("\tThis number is out of bounds! ");
                }
                Task element = tasks.getTask(pos - 1);
                tasks.deleteTask(pos - 1);
                ui.printDeleteTasks(pos, tasks, element);
                storage.writeTasksToFile(tasks);
            } else if (input.startsWith("list")) {
                ui.printListMessage(tasks);
                storage.writeTasksToFile(tasks);
            } else {
                throw new DukeException("\tHey bud! Sorry I don't quite know what you mean :-(");
            }
        }
        catch (DukeException | DateTimeException | IOException e) {
            handleException(e);
        }
    }

    public static void addToList(String input, Storage storage, TaskList tasks) {
        try {
            if (input.startsWith("todo")) {
                if (input.trim().length() <= 4) {
                    throw new DukeException("\t Sorry! The description of a todo cannot be empty :(");
                }
                Task task = new ToDo(input.substring(5));
                tasks.addTask(task);
                ui.printAddTaskToList(tasks, task);
                storage.writeTasksToFile(tasks);
            } else if (input.startsWith("deadline")) {
                int index = input.lastIndexOf("/by");
                if (input.trim().length() <= 8 || (input.substring(9, index).isEmpty())) {
                    throw new DukeException("\t Sorry! The description of a deadline cannot be empty :(");
                }

                if (!input.contains("/by"))  {
                    throw new DukeException("\t Hey bud! Please include when the deadline is! " +
                            "\n\t For example you can type: deadline read /by 2023-09-01 1700");
                }
                Task task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
                tasks.addTask(task);
                ui.printAddTaskToList(tasks, task);
                storage.writeTasksToFile(tasks);

            } else if (input.startsWith("event")) {
                int indexFrom = input.lastIndexOf("/from");
                int indexTo = input.lastIndexOf("/to");
                if ((input.trim().length() <= 5) || (input.substring(6, indexFrom).isEmpty())) {
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
                Task task = new Event(input.substring(6, indexFrom - 1),
                        input.substring(indexFrom + 6, indexTo - 1), input.substring(indexTo + 4));
                tasks.addTask(task);
                ui.printAddTaskToList(tasks, task);
                storage.writeTasksToFile(tasks);
            } else {
                userCommand(input, storage, tasks);
            }
        } catch (DukeException | DateTimeException | IOException | NumberFormatException e) {
            handleException(e);
        }
    }

    private static void handleException(Exception e) {
        if (e instanceof DukeException) {
            System.out.println(e.getMessage());
        } else if (e instanceof DateTimeException) {
            System.out.println("\tPlease put a valid date and time in the format YYYY-MM-DD HHMM." +
                    "\n\tFor example: 2023-08-08 1800");
        } else if (e instanceof IOException) {
            System.out.println("\tAn error occurred while performing a file operation: " + e.getMessage());
        } else if (e instanceof NumberFormatException ) {
            System.out.println("\tYou can only perform this action on an integer!");
        } else {
            System.out.println("\tAn unexpected error occurred: " + e.getMessage());
        }
    }


}