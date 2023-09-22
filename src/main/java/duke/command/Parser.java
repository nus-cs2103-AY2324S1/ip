package duke.command;

import duke.data.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Deals with the user command.
 */
public class Parser {
    /**
     * Processes the user's command.
     *
     * @param command The command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @param storage The storage of tasks.
     * @return String to be printed to the user.
     * @throws DukeException If the command is invalid.
     */
    public String processCommand(String command, Ui ui, TaskList taskList, Storage storage) {
        String ans = "";
        if (command.equals("bye")) {
            ans = processBye(ui);
        } else if (command.equals("list")) {
            ans = processList(ui, taskList);
        } else if (command.startsWith("mark")) {
            ans = processMark(command, ui, taskList);
        } else if (command.startsWith("unmark")) {
            ans = processUnmark(command, ui, taskList);
        } else if (command.startsWith("delete")) {
            ans = processDelete(command, ui, taskList);
        } else if (command.startsWith("find")) {
            ans = processFind(command, ui, taskList);
        } else {
            try {
                ans = addTask(command, ui, taskList);
            } catch (DukeException e) {
                ans = ui.printError(e);
            }
        }
        try {
            if (!command.equals("list")) {
                storage.writeFile(taskList);
            }
        } catch (IOException ioe) {
            ans += "\n" + ui.printError(ioe);
        }
        return ans;
    }

    /**
     * Processes command to add task to the task list.
     * @param task Task to be added to list.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @throws DukeException If the task is invalid.
     */
    public String addTask(String task, Ui ui, TaskList taskList) throws DukeException {
        String ans = "";
        if (task.startsWith("todo")) {
            ans = processTodo(task, ui, taskList);
        } else if (task.startsWith("deadline")) {
            ans = processDeadline(task, ui, taskList);
        } else if (task.startsWith("event")) {
            ans = processEvent(task, ui, taskList);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return ans;
    }

    /**
     * Processes the bye command.
     *
     * @param ui The chatbot's ui.
     * @return String of bye printed to the user.
     */
    public String processBye(Ui ui) {
        return ui.showByeMessage();
    }

    /**
     * Processes the list command.
     *
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return The task list printed to the user.
     */
    public String processList(Ui ui, TaskList taskList) {
        return ui.printList(taskList);
    }

    /**
     * Processes the mark command.
     *
     * @param command The mark command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return String represents the confirmation of marking task done printed to the user.
     */
    public String processMark(String command, Ui ui, TaskList taskList) {
        int index = 0;
        for (int i = 5; i < command.length(); ++i) {
            index *= 10;
            index += command.charAt(i) - 48;
        }
        assert index > 0 : "index should be positive integer";
        taskList.getTask(index - 1).markAsDone();
        return ui.markTaskDone(taskList.getTask(index - 1));
    }

    /**
     * Processes the unmark command.
     *
     * @param command The unmark command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return String represents the confirmation of unmarking task not done printed to the user.
     */
    public String processUnmark(String command, Ui ui, TaskList taskList) {
        int index = 0;
        for (int i = 7; i < command.length(); ++i) {
            index *= 10;
            index += command.charAt(i) - 48;
        }
        assert index > 0 : "index should be positive integer";
        taskList.getTask(index - 1).markAsNotDone();
        return ui.markTaskNotDone(taskList.getTask(index - 1));
    }

    /**
     * Processes the delete command.
     *
     * @param command The delete command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return String represents the confirmation of deleting task printed to the user.
     */
    public String processDelete(String command, Ui ui, TaskList taskList) {
        int index = 0;
        for (int i = 7; i < command.length(); ++i) {
            index *= 10;
            index += command.charAt(i) - 48;
        }
        assert index > 0 : "index should be positive integer";
        Task task = taskList.deleteTask(index - 1);
        return ui.deleteTask(task, taskList.getSize());
    }

    /**
     * Processes the find command.
     *
     * @param command The find command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return The matching tasks printed to the user.
     */
    public String processFind(String command, Ui ui, TaskList taskList) {
        String str = command.substring(5);
        ArrayList<Task> tasks = taskList.contains(str);
        return ui.printMatchingTasks(tasks);
    }

    /**
     * Processes the add todo command.
     *
     * @param task The add todo task command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return String represents the confirmation of adding task printed to the user.
     * @throws DukeException If the todo task is invalid.
     */
    public String processTodo(String task, Ui ui, TaskList taskList) throws DukeException {
        if (task.length() < 6) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(task.substring(5));
        taskList.addTask(todo);
        return ui.addTask(todo, taskList.getSize());
    }

    /**
     * Processes the add deadline command.
     *
     * @param task The add deadline task command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return String represents the confirmation of adding task printed to the user.
     * @throws DukeException If the deadline task is invalid.
     */
    public String processDeadline(String task, Ui ui, TaskList taskList) throws DukeException {
        if (task.length() < 10) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String description = "";
        String by = "";
        for (int i = 9; i < task.length(); ++i) {
            if (task.charAt(i) == '/') {
                description = task.substring(9, i - 1);
                by = task.substring(i + 4);
                break;
            }
        }
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        return ui.addTask(deadline, taskList.getSize());
    }

    /**
     * Processes the add event command.
     *
     * @param task The add event task command user types.
     * @param ui The chatbot's ui.
     * @param taskList The list of tasks.
     * @return String represents the confirmation of adding task printed to the user.
     * @throws DukeException If the event task is invalid.
     */
    public String processEvent(String task, Ui ui, TaskList taskList) throws DukeException {
        if (task.length() < 7) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        int slash1 = -1;
        int slash2 = -1;
        for (int i = 0; i < task.length(); ++i) {
            if (task.charAt(i) == '/') {
                if (slash1 == -1) {
                    slash1 = i;
                } else {
                    slash2 = i;
                }
            }
        }
        assert slash1 != -1 && slash2 != -1 : "Wrong event format";
        String description = task.substring(6, slash1 - 1);
        String from = task.substring(slash1 + 6, slash2 - 1);
        String to = task.substring(slash2 + 4);
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        return ui.addTask(event, taskList.getSize());
    }
}
