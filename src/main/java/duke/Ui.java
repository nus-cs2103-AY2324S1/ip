package duke;

import java.util.ArrayList;

public class Ui {

    public void welcomeMessage() {
        System.out.println("Hello! I'm ChatBot" + "\n" + "What can I do for you?" + "\n");
    }

    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTaskList(TaskList tasks) {
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task curr = tasks.getTask(i);
            System.out.println((i + 1) + ". " + curr);
        }
        return ;
    }
    public void printAddTask(Task curr, int taskSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(curr);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    public void printDelete(Task curr, int taskSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(curr);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    public void printMark(Task curr, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((index + 1) + ". " + curr);
    }
    public void printUnmark(Task curr, int index) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println((index + 1) + ". " + curr);
    }

    public void showLoadingError(String message) {
        System.out.println(message);
    }
}
