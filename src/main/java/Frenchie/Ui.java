package Frenchie;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcomeMessage() {
        String skeleton = "____________________________________________________________\n" +
                " Hello! I'm Frenchie.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(skeleton);
    }

    public String retrieveUserInput() {
        return scanner.nextLine().trim();
    }

    public void listTasks(TaskList tasks) {
        tasks.listTasks();
    }


    public void markTaskAsComplete(Task target_task) {
        System.out.println("____________________________________________________________\n" +
                " Nice! I've marked this task as done: \n" +
                target_task.toString() + "\n" +
                "____________________________________________________________");
    }

    public void markTaskAsIncompelte(Task target_task) {
        System.out.println("____________________________________________________________\n" +
                " OK, I've marked this task as not done yet: \n" +
                target_task.toString() + "\n" +
                "____________________________________________________________");
    }

    public void addTask(Task task, TaskList taskList) {
        System.out.println("____________________________________________________________\n" +
                " Got it! I've added this task: \n" +
                task + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n" +
                "____________________________________________________________");
    }
    public void deleteTask(Task target_task, TaskList taskList) {
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task: \n" +
                target_task.toString()   + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n" +
                "____________________________________________________________");
    }

    public void exitMessage() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    public void findMessage() {
        System.out.println("____________________________________________________________\n" +
                "Here are the matching tasks in your list: \n");
    }
    public void closeScanner() {
        scanner.close();
    }
}
