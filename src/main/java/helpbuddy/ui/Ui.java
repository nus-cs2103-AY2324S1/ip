package helpbuddy.ui;

import helpbuddy.task.Task;
import helpbuddy.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private void printMessageBlock(String s) {
        System.out.println(HORIZONTAL_LINE + "\n\t" + s + HORIZONTAL_LINE + "\n");
    }
    public void printHelloMessage() {
        printMessageBlock("Hello! I'm HelpBuddy.\n" + "\tWhat can I do for you?\n");
    }

    public void printByeMessage() {
        printMessageBlock("Bye. Hope to see you again soon!\n");
    }

    public void printListMessage(TaskList taskList) {
        if (taskList.isEmpty()) {
            printMessageBlock("You have no task in your list.\n");
        } else {
            String messageOutput = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskIndex = i + 1;
                messageOutput += "\t" + taskIndex + ". " + taskList.getTask(i) + "\n";
            }
            printMessageBlock(messageOutput);
        }
    }
    public void printMarkMessage(Task task) {
        printMessageBlock("Nice! I've marked this task as done:\n\t  " + task + "\n");
    }

    public void printUnmarkMessage(Task task) {
        printMessageBlock("OK, I've marked this task as not done yet:\n\t  " + task + "\n");
    }
    private String stringifyNumOfTasks(TaskList taskList) {
        return "\n\tNow you have " + taskList.getSize() + " tasks in the list.\n";
    }

    public void printAddTaskMessage(Task task, TaskList taskList) {
        printMessageBlock("Got it. I've added this task:\n\t  " + task + stringifyNumOfTasks(taskList));
    }

    public void printDeleteTaskMessage(Task task, TaskList taskList) {
        printMessageBlock("Noted. I've removed this task:\n\t  " + task + stringifyNumOfTasks(taskList));
    }

    /**
     * Prints the taskList.
     * @param taskList the taskList that contains Task objects user is finding.
     */
    public void printFindTaskMessage(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            printMessageBlock("There is no such task in your list.\n");
        } else {
            String messageOutput = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                int taskIndex = i + 1;
                messageOutput += "\t" + taskIndex + ". " + taskList.get(i) + "\n";
            }
            printMessageBlock(messageOutput);
        }
    }
    public void printErrorMessage(String s) {
        printMessageBlock(s);
    }

    public void printDateTimeParseErrorMessage() {
        printErrorMessage("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!\n");
    }
    public String readInput() {
        return this.sc.nextLine();
    }

    public void closeScanner() {
        this.sc.close();
    }
}
