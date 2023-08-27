package milbot;

import taskclasses.*;

public class Ui {
    private static final String INDENTATION = "     ";
    private static final String HORIZONTAL_LINE = "__________________________________________________________________________";

    public void printWelcomeMessage() {
        String logo = " ____     ____    _    _\n"
                + "|     \\__/    |  | |  | |\n"
                + "|  | \\ _ / |  |  | |  | |\n"
                + "|  |       |  |  | |  | |____\n"
                + "|__|       |__|  |_|  |______|\n";
        System.out.println(logo);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hi there, I'm Mil - your personal chatbot.\n     How can I help you today?");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    public void printGoodbyeMessage() {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Have a nice day and see you again soon!");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    public void printErrorMessage(String message) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + message);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    public void printNewTask(TaskList taskList, Task task) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + "  " + task);
        System.out.println(INDENTATION + "Now you have " + taskList.getSize() + " tasks in the list.");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    public void printTaskList(TaskList taskList) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        int i = 1;
        for (Task task : taskList.getTaskList()) {
            System.out.println(String.format("%s%d.%s",
                    INDENTATION, i, task.toString()));
            i++;
        }
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    public void printMarkTask(Task task) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    public void printUnmarkTask(Task task) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
    public void printRemoveTask(Task task, TaskList taskList) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + "  " + task);
        System.out.println(INDENTATION + "Now you have " + taskList.getSize() + " tasks in the list.");
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    public void printUnknownMessage() {
        System.out.println("☹ Oopsie! I'm sorry, but I don't know what that means.");
    }

}
