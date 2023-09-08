package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Ui {
    private final String HORIZONTAL_LINE = "    _______________________________________________________________";
    public void printList(TaskList taskList) {
        System.out.println("    Here are the tasks in your list:");
        taskList.printTaskList();
    }

    public void hello() {
        printHorizontalLine();
        System.out.println("    Hello! I'm Thinh's chatbot\n    What can I do for you?");
        printHorizontalLine();
    }

    public void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void addTask(Task task, int newSize) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + newSize + " tasks in the list.");
    }

    public void deleteTask(Task task, int newSize) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + newSize + " tasks in the list.");
    }

    public void markTaskDone(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task.toString());
    }

    public void markTaskNotDone(Task task) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task.toString());
    }

    public void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void printError(Exception e) {
        System.out.println("    " + e.getMessage());
    }

    /**
     * Prints the matching task to user.
     *
     * @param tasks Array contains matching tasks.
     */
    public void printMatchingTasks(ArrayList<Task> tasks) {
        System.out.println("    Here are the matching tasks in your list:");
        int index = 0;
        for (Task task: tasks) {
            System.out.println("    " + (++index) + "." + task.toString());
        }
    }
}
