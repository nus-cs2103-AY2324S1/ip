package main.java;

public class Ui {
    public void showGreeting() {
        System.out.println("Hello! I'm ChadBod.");
        System.out.println("What can I do for you?");
    }

    public void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printErrorMessage(String str) {
        System.out.println(str);
    }

    public void printStatusUpdate(Boolean done, Task task) {
        if (done) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.printf("%s\n", task);
    }

    public void printTaskAddedMessage(Task newTask, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    public void printTaskRemovedMessage(Task removedTask, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf("%s\n", removedTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    public void printTasks(TaskList tasks) {
        System.out.print(tasks);
    }
}