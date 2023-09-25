package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    public void stopUserInput() {
        scanner.close();
    }

    public String displayWelcomeMessage() {
        return "Hello! I'm Sivraj\n" + "What can I do for you?";
    }
    String dashLine = "----------------------------------------\n";

    public String displayToDoMessage(TaskList taskList) {
        return dashLine + "     Got it. I've added this task:" + "\n" +
                "         " + taskList.getTask(taskList.listSize() - 1) + "\n" +
                "     Now you have " + taskList.listSize() + " tasks in the list.\n" +
                dashLine;
    }

    public String displayDeadlineMessage(TaskList taskList) {
        return dashLine + "     Got it. I've added this task:\n" +
                "       " + taskList.getTask(taskList.listSize() - 1) +
                "\n" + "     Now you have " + taskList.listSize() +
                " tasks in the list.\n" + dashLine;
    }

    public String displayEventMessage(TaskList taskList) {
        return dashLine + "     Got it. I've added this task:\n" +
                "       " + taskList.getTask(taskList.listSize() - 1) +
                "\n" + "     Now you have " + taskList.listSize() +
                " tasks in the list.\n" + dashLine;
    }

    public String displayMarkMessage(TaskList taskList, int taskIndex) {
        return dashLine + "    Nice! I've marked this task as done:\n" +
                "       " + taskList.getTask(taskIndex) + "\n" +
                dashLine;
    }

    public String displayUnmarkMessage(TaskList taskList, int taskIndex) {
        return dashLine + "     OK, I've marked this task as not done yet:\n" +
                "       " + taskList.getTask(taskIndex) + "\n" +
                dashLine;
    }

    public String displayListMessage(TaskList taskList) {
        String s = "";
        for (int i = 0; i < taskList.listSize(); i++) {
            s = s + "\n      " + (i + 1) + "." + taskList.getTask(i);
        }
        return dashLine +
                "    Here are the tasks in your list:" + "\n" +
                s + dashLine;
    }

    public String displayDeleteMessage(TaskList taskList, Task removedTask) {
        return dashLine + "     Noted. I've removed this task: \n" +
                "       " + removedTask + "\n" +
                "     Now you have " + taskList.listSize() + " tasks in the list.\n" +
                dashLine;
    }

    public String displayErrorMessage(String message) {
        return dashLine + "    OOPS " + message + "\n" + dashLine;
    }

    public String displayByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public void displayLoadErrorMessage(String errorMessage) {
        System.out.println("Error loading tasks: " + errorMessage);
    }
}

