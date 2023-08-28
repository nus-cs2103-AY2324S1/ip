package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private final String HORIZONTAL_LINE = "____________________________________________________________";

    public void printWithLines(String s) {
        System.out.println(HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE);
    }

    public void printHello() {
        printWithLines("Hello, I'm Je-O" + "\n"
                + "What can I do for you?");
    }

    public void printAddTask(Task task, int countTasks) {
        printWithLines("Got it. I've added this task:" + "\n"
                + task.toString() + "\n"
                + "Now you have " + countTasks + " tasks in the list.");
    }

    public void printDeleteTask(Task task, int countTasks) {
        printWithLines("Noted. I've removed this task:" + "\n"
                + task.toString() + "\n"
                + "Now you have " + countTasks + " tasks in the list.");
    }

    public void printTaskList(TaskList tasks, int countTasks) {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < countTasks; i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printMarkAsDone(Task task) {
        printWithLines("Nice! I've marked this task as done:" + "\n" + task);
    }

    public void printUnmarkAsDone(Task task) {
        printWithLines("OK, I've marked this task as not done yet:" + "\n" + task);
    }

    public void printBye() {
        printWithLines("Bye. Hope to see you again soon!");
    }

    public void printFindTasks(TaskList findTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < findTasks.getCountTasks(); i++) {
            System.out.println((i + 1) + ". " + findTasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printErrorMessage(DukeException e) {
        printWithLines(e.toString());
    }
}
