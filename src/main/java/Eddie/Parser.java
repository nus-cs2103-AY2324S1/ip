package Eddie;

import Eddie.Commands.*;
import Eddie.Exceptions.*;
import Eddie.GUI.Ui;
import Eddie.Tasks.Deadline;
import Eddie.Tasks.Event;
import Eddie.Tasks.Task;
import Eddie.Tasks.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents a parser which makes sense of the user input.
 */
public class Parser {
    /**
     * Main control flow for recognizing commands and what to do with commands.
     * @throws DukeException Thrown when commands are not inputted correctly.
     */
    private static String execute(String s) throws DukeException {
        Scanner sc = new Scanner(s);
        while (true) {
            String command = sc.next();


            switch (command) {
                case "bye":
                    return ExitCommand.execute();
                case "list":
                    return ListCommand.execute();
                case "mark":
                    if (!sc.hasNextInt()) {
                        throw new DukeException("Task to mark must be denoted by an integer. E.g. mark 1 ");
                    }
                    int taskNum = sc.nextInt();

                    if(taskNum < 1 || taskNum > TaskList.size()) {
                        throw new DukeException("Index must be within the range of the task list");
                    }

                    Task task = TaskList.get(taskNum - 1);

                    task.taskIsDone();
                    Storage.write();

                    return Ui.mark(taskNum);
                case "unmark":
                    if (!sc.hasNextInt()) {
                        throw new DukeException("Task to unmark must be denoted by an integer. E.g. unmark 1 ");
                    }
                    taskNum = sc.nextInt();

                    if(taskNum < 1 || taskNum > TaskList.size()) {
                        throw new DukeException("Index must be within the range of the task list");
                    }
                    task = TaskList.get(taskNum - 1);

                    task.taskNotDone();

                    Storage.write();
                    return Ui.unmark(taskNum);
                case "delete":
                    if (!sc.hasNextInt()) {
                        throw new DukeException("Task to delete must be denoted by an integer. E.g. delete 1 ");
                    }
                    int index = sc.nextInt();
                    if(index < 1 || index > TaskList.size()) {
                        throw new DukeException("Index must be within the range of the task list");
                    }

                    return DeleteCommand.execute(index - 1);
                case "todo":
                    if (!sc.hasNext()) {
                        throw new EmptyDescriptionException();
                    }
                    String restOfString = sc.nextLine();

                    String taskName = restOfString;
                    Task taskToAdd = new Todo(taskName);
                    return AddCommand.execute(taskToAdd);
                case "deadline":
                    if (!sc.hasNext()) {
                        throw new EmptyDescriptionException();
                    }
                    restOfString = sc.nextLine();

                    int slashIndex = restOfString.indexOf("/by");
                    if (slashIndex == -1) {
                        throw new MissingByDateException();
                    } else if (slashIndex == 1) {
                        throw new EmptyDescriptionException();
                    }

                    taskName = restOfString.substring(0, slashIndex - 1);

                    try {
                        String date = restOfString.substring(slashIndex + 4);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new MissingByDateException();
                    }

                    String date = restOfString.substring(slashIndex + 4);

                    LocalDate d = LocalDate.parse(date);
                    taskToAdd = new Deadline(taskName, d);
                    return AddCommand.execute(taskToAdd);
                case "event":
                    if (!sc.hasNext()) {
                        throw new EmptyDescriptionException();
                    }
                    restOfString = sc.nextLine();


                    int fromIndex = restOfString.indexOf("/from");
                    int toIndex = restOfString.indexOf("/to");

                    if (fromIndex == -1) {
                        throw new MissingFromDateException();
                    } else if (toIndex == -1) {
                        throw new MissingToDateException();
                    }

                    taskName = restOfString.substring(0, fromIndex - 1);
                    String fromDate = restOfString.substring(fromIndex + 6, toIndex - 1);
                    String toDate = restOfString.substring(toIndex + 4);

                    LocalDate from = LocalDate.parse(fromDate);
                    LocalDate to = LocalDate.parse(toDate);
                    taskToAdd = new Event(taskName, from, to);
                    return AddCommand.execute(taskToAdd);
                case "clear":
                    return ClearCommand.execute();
                case "find":
                    restOfString = sc.next();
                    return FindCommand.execute(restOfString);
                case "tag":
                    taskNum = sc.nextInt();
                    task = TaskList.get(taskNum - 1);
                    String tag = sc.next();

                    return TagCommand.execute(task, tag);
                default:
                    throw new NoSuchCommandException();
            }
        }
    }


    /**
     * Method to be used to start parsing of user input.
     */
    public static String parse(String s) {
        try {
            return Parser.execute(s);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeException e) {
            return e.getMessage();
        }
    }
}
