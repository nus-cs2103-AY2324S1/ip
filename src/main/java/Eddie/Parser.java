package Eddie;

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
            assert(sc.hasNext());

            if (command.equals("bye")) {
                return Duke.exit();
            } else if (command.equals("list")) {
                return Duke.list();
            } else if (command.equals("mark")) {
                int taskNum = sc.nextInt();
                Task task = TaskList.get(taskNum - 1);
                task.taskIsDone();

                assert (task.getStatus().equals("x"));

                Storage.write();
                return Ui.mark(taskNum);
            } else if (command.equals("unmark")) {
                int taskNum = sc.nextInt();
                Task task = TaskList.get(taskNum - 1);
                task.taskNotDone();

                Storage.write();
                return Ui.unmark(taskNum);
            } else if (command.equals("delete")) {
                int index = sc.nextInt();
                return Duke.delete(index - 1);
            } else if (command.equals("todo")) {

                String restOfString = sc.nextLine();
                if (restOfString.length() != 0) {
                    String taskName = restOfString;
                    Task taskToAdd = new Todo(taskName);
                    return Duke.add(taskToAdd);

                } else {
                    throw new EmptyDescriptionException();
                }

            } else if (command.equals("deadline")) {
                String restOfString = sc.nextLine();

                if (restOfString.length() != 0) {
                    int slashIndex = restOfString.indexOf("/by");
                    if (slashIndex == -1) {
                        throw new MissingByDateException();
                    }
                    String taskName = restOfString.substring(0, slashIndex - 1);
                    String date = restOfString.substring(slashIndex + 4);
                    LocalDate d = LocalDate.parse(date);
                    Task taskToAdd = new Deadline(taskName, d);
                    return Duke.add(taskToAdd);
                } else {
                    throw new EmptyDescriptionException();
                }

            } else if (command.equals("event")) {

                String restOfString = sc.nextLine();
                if (restOfString.length() != 0) {
                    int fromIndex = restOfString.indexOf("/from");
                    int toIndex = restOfString.indexOf("/to");

                    if (fromIndex == -1) {
                        throw new MissingFromDateException();
                    } else if (toIndex == -1) {
                        throw new MissingToDateException();
                    }

                    String taskName = restOfString.substring(0, fromIndex - 1);
                    String fromDate = restOfString.substring(fromIndex + 6, toIndex - 1);
                    String toDate = restOfString.substring(toIndex + 4);

                    LocalDate from = LocalDate.parse(fromDate);
                    LocalDate to = LocalDate.parse(toDate);
                    Task taskToAdd = new Event(taskName, from, to);
                    return Duke.add(taskToAdd);
                } else {
                    throw new EmptyDescriptionException();
                }


            } else if (command.equals("clear")){
                return Duke.clear();
            } else if (command.equals("find")) {
                String restOfString = sc.next();
                return Duke.find(restOfString);
            } else {
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
