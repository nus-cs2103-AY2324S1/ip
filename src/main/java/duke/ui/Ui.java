package duke.ui;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the client facing UI to handle inputs and displays.
 */
public class Ui {
    /**
     * Adds task to TaskList and displays added task info to user.
     * @param tasks TaskList
     * @param task Task to be added
     */
    public void addTask(TaskList tasks, Task task) {
        tasks.add(task);
        print("Got it. I've added this task:");
        print(String.format("  %s\n", task.toString()));
        print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Displays bye message.
     */
    public void bye() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Displays all tasks from TaskList that exist on specified date.
     * @param tasks TaskList
     * @param date Date given by user
     * @throws DukeException if invalid date
     */
    public void date(TaskList tasks, String date) throws DukeException {
        int idx = 0;
        String dmy = Parser.convertToDmy(date);
        for (Task task: tasks) {
            if (task.isToday(date)) {
                if (idx == 0) {
                    print(String.format("Here are the tasks on %s:", dmy));
                }
                print(String.format("%d.%s", ++idx, task.toString()));
            }
        }
        if (idx == 0) {
            print(String.format("There is no task on %s.", dmy));
        }
    }

    /**
     * Removes task from TaskList and displays removed task info to user.
     * @param tasks TaskList
     * @param index Index of task removed
     * @throws DukeException if invalid task index
     */
    public void deleteTask(TaskList tasks, int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index");
        }
        Task task = tasks.remove(index);
        print("Noted. I've removed this task:");
        print(String.format("  %s\n", task.toString()));
        print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Displays all tasks in TaskList that matches specified keyword.
     * @param tasks TaskList
     * @param keyword String of specified keyword
     */
    public void find(TaskList tasks, String keyword) {
        int idx = 0;
        for (Task task: tasks) {
            if (task.contains(keyword)) {
                if (idx == 0) {
                    print("Here are the matching tasks in your list:");
                }
                print(String.format("%d.%s", ++idx, task.toString()));
            }
        }
        if (idx == 0) {
            print("There is no matching task with: " + keyword);
        }
    }

    /**
     * Displays hello message.
     */
    public void hello() {
        print("Hello! I'm AdaBot.\nWhat do you want to do today?");
    }

    /**
     * Displays all tasks in TaskList.
     * @param tasks TaskList
     */
    public void list(TaskList tasks) {
        if (tasks.size() == 0) {
            print("There is no task in your list.");
            return;
        }
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
    }

    /**
     * Marks specified task as done.
     * @param tasks TaskList
     * @param index Index of specified task
     * @throws DukeException if invalid index task
     */
    public void mark(TaskList tasks, int index) throws DukeException {
        Task task = tasks.markDone(index);
        print("Nice! I've marked this task as done:");
        print("  " + task.toString());
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void printError(String s) {
        System.out.println("OOPS!!! " + s);
    }

    /**
     * Marks specified task as undone.
     * @param tasks TaskList
     * @param index Index of specified task
     * @throws DukeException if invalid index task
     */
    public void unmark(TaskList tasks, int index) throws DukeException {
        Task task = tasks.unmarkDone(index);
        print("OK, I've marked this task as not done yet:");
        print("  " + task.toString());
    }
}
