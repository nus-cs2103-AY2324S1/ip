package teho.main;

import teho.main.Task;
import teho.main.TaskList;

public class Ui {
    public static void generateHelloMessage() {
        System.out.println("Hello! I'm TehO \nWhat can I do for you?");
    }

    public static void generateGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void generateList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public static void generateMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void generateUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void generateAddToDoMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public void generateAddDeadlineMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public void generateAddEventMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public void generateDeleteMessage(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public void showLoadingError() {
        System.out.println("OHNO! Loading error!");
    }
}
