package duke;

import java.util.ArrayList;

/**
 * Deals with making sense of the user's command.
 * 
 * @author Qin Yan Er
 */
public class Parser {

    /**
     * Parses the given command and performs corresponding actions on the provided list, storage and ui.
     *
     * @param command The command to be parsed and executed.
     * @param list Contains the list of tasks.
     * @param storage Saves tasks into file and load tasks from the file.
     * @param ui Displays messages to interact with user.
     * @return A string containing the response to the command.
     * @throws DukeException If there is an error in parsing the command.
     */
    public static String parse(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        if (command.equalsIgnoreCase("bye")) {
            storage.save(list);
            return ui.showBye();

        } else if (command.toLowerCase().startsWith("delete")) {
            return parseDelete(command, list, storage, ui);

        } else if (command.equalsIgnoreCase("list")) {
            return ui.printList(list);

        } else if (command.toLowerCase().startsWith("find")) {
            return parseFind(command, list, storage, ui);

        } else if (command.toLowerCase().startsWith("mark")) {
            return parseMark(command, list, storage, ui);

        } else if (command.toLowerCase().startsWith("unmark")) {
            return parseUnmark(command, list, storage, ui);

        } else if (command.toLowerCase().startsWith("todo")) {
            return parseTodo(command, list, storage, ui);

        } else if (command.toLowerCase().startsWith("deadline")) {
            return parseDeadline(command, list, storage, ui);

        } else if (command.toLowerCase().startsWith("event")) {
            return parseEvent(command, list, storage, ui);

        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public static String parseDelete(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        String[] sub = command.split(" ");

        if (sub.length == 2) {
            int number = Integer.parseInt(sub[1]) - 1;

            if (number < list.getSize() && number >= 0) {
                Task removedTask = list.delete(number);
                storage.save(list);

                return ui.removeTask(list, removedTask);
            } else {
                throw new DukeException("OOPS!!! The task you entered is not in the list");
            }
        } else {
            throw new DukeException("OOPS!!! Please fill in the task I need to delete");
        }
    }

    public static String parseFind(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        String keyword = command.substring(5).trim();
        ArrayList<Task> matchingTasks = list.find(keyword);

        return ui.printMatchingTasks(matchingTasks);
    }

    public static String parseMark(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        String[] sub = command.split(" ");

        if (sub.length == 2) {
            int number = Integer.parseInt(sub[1]) - 1;

            if (number < list.getSize() && number >= 0) {
                list.getTask(number).markAsDone();
                storage.save(list);

                return ui.mark(list, number);
            } else {
                throw new DukeException("OOPS!!! The task you entered is not in the list");
            }
        } else {
            throw new DukeException("OOPS!!! Please fill in the task I need to mark");
        }
    }

    public static String parseUnmark(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        String[] sub = command.split(" ");

        if (sub.length == 2) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;

            if (number < list.getSize() && number >= 0) {
                list.getTask(number).markAsNotDone();
                storage.save(list);

                return ui.unMark(list, number);
            } else {
                throw new DukeException("OOPS!!! The task you entered is not in the list");
            }
        } else {
            throw new DukeException("OOPS!!! Please fill in the task I need to unmark");
        }
    }

    public static String parseTodo(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        String todo = command.substring(4).trim();
        if (todo.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            Todo newTodo = new Todo(todo);
            list.add(newTodo);
            storage.save(list);

            return ui.addTodo(list, newTodo);
        }
    }

    public static String parseDeadline(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        String deadline = command.substring(8).trim();

        if (deadline.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            // separate the task and its deadline
            String[] sub = deadline.split("/by");

            if (sub.length == 2) {

                String description = sub[0].trim();
                String by = sub[1].trim();

                Deadline newDeadline = new Deadline(description, by);
                list.add(newDeadline);
                storage.save(list);

                return ui.addDeadline(list, newDeadline);
            } else {
                throw new DukeException("OOPS!!! Please fill in the deadline");
            }
        }
    }

    public static String parseEvent(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
        String event = command.substring(5).trim();

        if (event.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else {
            // separate event and timing
            String[] sub = event.split("/from");

            if (sub.length == 2) {
                String description = sub[0].trim();
                String timing = sub[1].trim();

                // separate start time and end time
                String[] fromTo = timing.split("/to");

                if (fromTo.length == 2) {
                    String from = fromTo[0].trim();
                    String to = fromTo[1].trim();

                    Event newEvent = new Event(description, from, to);
                    list.add(newEvent);
                    storage.save(list);

                    return ui.addEvent(list, newEvent);
                } else {
                    throw new DukeException("OOPS!!! Please fill in the time the event ends");
                }
            } else {
                throw new DukeException("OOPS!!! Please fill in the timings");
            }
        }
    }
}
