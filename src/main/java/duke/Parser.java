package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;

/**
 * The Parser class handles user input parsing and the execution of corresponding commands.
 * It interprets user commands and interacts with the TaskList to perform task-related operations.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param tasks The TaskList instance to perform operations on.
     * @param echo  The user's input command.
     * @return A response message based on the executed command.
     */
    public String parse(TaskList tasks, String echo) {
        if (echo.equals("bye")) {
            assert Ui.sayBye() != null;
            return Ui.sayBye();
        }
        if (echo.equals("list")) {
            assert tasks.printList() != null;
            return "H...here are the tasks in your list:\n" + tasks.printList();
        }
        if (echo.equals("help")) {
            return printHelp();
        }

        try {
            String[] parts = echo.split(" ");
            if (parts[0].equals("mark")) {
                return markCase(tasks, parts);
            } else if (parts[0].equals("unmark")) {
                return unmarkCase(tasks, parts);
            } else if (parts[0].equals("deadline")) {
                return deadline(tasks, echo);
            } else if (parts[0].equals("event")) {
                return event(tasks, echo);
            } else if (parts[0].equals("todo")) {
                return todo(tasks, echo);
            } else if (parts[0].equals("delete")) {
                return delete(tasks, parts);
            } else if (parts[0].equals("find")) {
                return find(tasks, parts);
            } else {
                throw new DukeException("AAA...AGHHH!!! Go...Gomenasaiii!!! I don't understand!!!°(°ˊДˋ°) °");
            }
        } catch (DukeException e) {
            assert e.getMessage() != null;
            return e.getMessage() + "\n";
        }
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param tasks The TaskList instance to mark the task in.
     * @param parts An array of command parts, including the task number to be marked.
     * @return A response message indicating the task has been marked as done.
     * @throws DukeException If there's an issue with the command or task number.
     */
    public String markCase(TaskList tasks, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("AAA...AGHHH!!! The number to be marked done... " +
                    "c...cannot be empty!!!°(°ˊДˋ°) °");
        }
        int taskNum = Integer.parseInt(parts[1]);
        if (taskNum > tasks.totalTaskNum() ||  taskNum <= 0) {
            throw new DukeException("AAA...AGHHH!!! You...you don't have sooo many tasks\n" +
                    "y...yyet!!!°(°ˊДˋ°) °");
        }
        tasks.markDone(taskNum - 1);
        return "O...Omedeto! I have... have marked this as done!!ヾ(*´▽‘*)ﾉ\n"
                + tasks.printTask(taskNum - 1) + "\n";
    }

    /**
     * Handles the "unmark" command to unmark a task as done.
     *
     * @param tasks The TaskList instance to unmark the task in.
     * @param parts An array of command parts, including the task number to be unmarked.
     * @return A response message indicating the task has been marked as undone.
     * @throws DukeException If there's an issue with the command or task number.
     */
    public String unmarkCase(TaskList tasks, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("AAA...AGHHH!!! The number to be unmarked done... " +
                    "c...cannot be empty!!!°(°ˊДˋ°) °");
        }
        int taskNum = Integer.parseInt(parts[1]);
        if (taskNum > tasks.totalTaskNum() || taskNum <= 0) {
            throw new DukeException("AAA...AGHHH!!! You...you don't have sooo many tasks\n" +
                    "y...yyet!!!°(°ˊДˋ°) °");
        }
        tasks.markUndone(taskNum - 1);
        assert tasks.printTask(taskNum - 1) != null;
        return "Okk... this is not done yet ﾍ(;´Д｀ﾍ)\n" + tasks.printTask(taskNum - 1)
                + "\n";
    }

    /**
     * Handles the "deadline" command to add a deadline task.
     *
     * @param tasks The TaskList instance to add the deadline task to.
     * @param echo  The user's input command.
     * @return A response message indicating the deadline task has been added.
     * @throws DukeException If there's an issue with the command or date format.
     */
    public String deadline(TaskList tasks, String echo) throws DukeException{
        String removeDdl = echo.replace("deadline", "");
        if (removeDdl.equals("")) {
            throw new DukeException("AAA...AGHHH!!! The description of a deadline... " +
                    "c...cannot be empty!!!°(°ˊДˋ°) °");
        }
        String removeBy = removeDdl.replace("by", "");
        String[] ddl = removeBy.split("/");
        String[] split = ddl[1].split(" ");

        try {
            LocalDate by = LocalDate.parse(split[1]);
            Deadline x = new Deadline(ddl[0], by);
            tasks.addToList(x);
            assert tasks.numOfTask() != null;
            return "Okk... I've... I've added this task:\n" + x.toString() + "\n"
                    + tasks.numOfTask();
        } catch (DateTimeException e) {
            throw new DukeException("Th...th...the ddate you have inputed is invalid!!");
        }
    }

    /**
     * Handles the "event" command to add an event task.
     *
     * @param tasks The TaskList instance to add the event task to.
     * @param echo  The user's input command.
     * @return A response message indicating the event task has been added.
     * @throws DukeException If there's an issue with the command.
     */
    public String event(TaskList tasks, String echo) throws DukeException {
        String removeEvent = echo.replace("event", "");
        if (removeEvent.equals("")) {
            throw new DukeException("AAA...AGHHH!!! The description of an event... " +
                    "c...cannot be empty!!!°(°ˊДˋ°) °");
        }

            String removeFrom = removeEvent.replace("from", "");
            String removeTo = removeFrom.replace("to", "");
            String[] event = removeTo.split("/");
            Event x = new Event(event[0], event[1], event[2]);
            tasks.addToList(x);

            return "Okk... I've... I've added this task:\n" + x.toString() + "\n"
                    + tasks.numOfTask();
    }

    /**
     * Handles the "todo" command to add a todo task.
     *
     * @param tasks The TaskList instance to add the todo task to.
     * @param echo  The user's input command.
     * @return A response message indicating the todo task has been added.
     * @throws DukeException If there's an issue with the command.
     */
    public String todo(TaskList tasks, String echo) throws DukeException {
        String removeTodo = echo.replace("todo", "");
        if (removeTodo.equals("")) {
            throw new DukeException("AAA...AGHHH!!! The description of the todo... " +
                    "c...cannot be empty!!!°(°ˊДˋ°) °");
        }
        ToDo x = new ToDo(removeTodo);
        tasks.addToList(x);
        return "Okk... I've... I've added this task:\n" + x.toString()
                + "\n" + tasks.numOfTask();
    }

    /**
     * Handles the "delete" command to delete a task from the task list.
     *
     * @param tasks The TaskList instance to remove the task from.
     * @param parts An array of command parts, including the task number to be deleted.
     * @return A response message indicating the task has been deleted.
     * @throws DukeException If there's an issue with the command or task number.
     */
    public String delete(TaskList tasks, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("AAA...AGHHH!!! The number to be deleted... " +
                    "c...cannot be empty!!!°(°ˊДˋ°) °");
        }
        if (tasks.totalTaskNum() == 0) {
            throw new DukeException("AAA...AGHHH!!! The list... " +
                    "is already empty!!!°(°ˊДˋ°) °");
        }
        if (Integer.parseInt(parts[1]) > tasks.totalTaskNum() || Integer.parseInt(parts[1]) < 0) {
            throw new DukeException("AAA...AGHHH!!! You do not have so many tasks... " +
                    "in the list!!!°(°ˊДˋ°) °");
        }
        int taskNum = Integer.parseInt(parts[1]);
        tasks.deleteTask(taskNum - 1);
        assert tasks.numOfTask() != null;
        return "O...Okk... I've re...removed this task:\n" + tasks.numOfTask();
    }

    /**
     * Handles the "find" command to search for tasks containing a keyword.
     *
     * @param tasks The TaskList instance to search within.
     * @param parts An array of command parts, including the keyword to search for.
     * @return A response message listing matching tasks found in the task list.
     * @throws DukeException If there's an issue with the command or keyword.
     */
    public String find(TaskList tasks, String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("AAA...AGHHH!!! The task to find... " +
                    "c...cannot be empty!!!°(°ˊДˋ°) °");
        }
        String keyword = parts[1];
        TaskList store = new TaskList(new ArrayList<Task>());
        for (int i = 0; i < tasks.totalTaskNum(); i++) {
            String temp = tasks.printTask(i);
            List<String> words = Arrays.asList(temp.split(" "));
            if (words.contains(keyword)) {
                store.addToList(tasks.tasks.get(i));
            }
        }
        return "Here are the matching tasks in your list:\n" + store.printList();
    }

    /**
     * Generates a help message listing the available commands and their descriptions.
     *
     * @return A string containing the help message with command descriptions.
     */
    public String printHelp() {
        return "Here are the commands for Bocchi-chan:\n"
                + "help: Prints out the help page for various commands.\n"
                + "list: Prints out your list of tasks.\n"
                + "todo <task>: Adds the <task> into the list of tasks as a task to do.\n"
                + "event <task> /from <time> /to <time>: Adds the <task> into the list \n"
                + "of tasks as an event with a duration.\n"
                + "deadline /by <yyyy-mm-dd>: Adds the <task> into the list of tasks\n"
                + "with a specific deadline.\n"
                + "mark <number>: Marks task <number> in the list of tasks as done.\n"
                + "unmark <number>: Unmarks task <number> in the list of tasks and it becomes undone.\n"
                + "find <keyword>: Finds the task with the exact <keyword> inputted.\n"
                + "delete <index>: Deletes the task of task number <index> from the task list\n"
                + "bye: Bocchi chan says bye to you♥\n";
    }
}
