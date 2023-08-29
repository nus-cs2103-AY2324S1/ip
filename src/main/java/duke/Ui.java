package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return this.scanner.nextLine();
    }

    private void outputMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    public void showWelcomeMessage() {
        outputMessage(" Hello! I'm Pixel\n What can I do for you?\n");
    }

    public void showLoadedTasksMessage() {
        outputMessage(" Loaded tasks from database!\n");
    }

    public void closeAndGoodbyeMessage() {
        outputMessage(" Bye. Hope to see you again soon!\n");
        scanner.close();
    }

    public void showErrorMessage(DukeException e) {
        outputMessage(e.getMessage());
    }

    public void listTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            outputMessage(" There are no tasks in your list!\n");
        } else {
            StringBuilder tasksString = new StringBuilder();
            for (int i = 0; i < tasks.getSize(); i++) {
                tasksString.append(String.format("  %d. %s\n", i + 1, tasks.getTask(i).toString()));
            }
            outputMessage(String.format(" Here are the tasks in your list:\n%s", tasksString));
        }
    }

    public void showDoneMessage(Task task) {
        outputMessage(String.format(" Nice! I've marked this task as done:\n  %s\n", task));
    }

    public void showUndoneMessage(Task task) {
        outputMessage(String.format(" OK, I've marked this task as not done yet:\n  %s\n", task));
    }

    public void showAddMessage(int taskSize, Task task) {
        outputMessage(String.format(
                " Got it. I've added this task:\n  %s\n Now you have %d task%s in the list.\n",
                task, taskSize, taskSize == 1 ? "" : "s"));
    }

    public void showDeleteMessage(int taskSize, Task task) {
        outputMessage(String.format(
                " Noted. I've removed this task:\n  %s\n Now you have %d task%s in the list.\n",
                task, taskSize, taskSize == 1 ? "" : "s"));
    }

    public void showNoMatchingTasksMessage() {
        outputMessage(" There are no matching tasks in your list!\n");
    }

    public void showMatchingTasksMessage(TaskList matchingTasks) {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            tasksString.append(String.format("  %d. %s\n", i + 1, matchingTasks.getTask(i).toString()));
        }
        outputMessage(String.format(" Here are the matching tasks in your list:\n%s", tasksString));
    }
}
