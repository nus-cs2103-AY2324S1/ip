package nexus.components;

import nexus.task.Task;
import nexus.task.TaskList;

public class Ui {
    private void printTask(Task task) {
        System.out.println(task);
    }

    public void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1);
            System.out.println("." + list.get(i));
        }
    }

    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        this.printTask(task);
    }

    public void printUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        this.printTask(task);
    }

    public void printDelete(Task task, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        this.printTask(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void printAdd(Task task, TaskList list) {
        System.out.println("Got it. I've added this task:");
        this.printTask(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void printFind(String keyword, TaskList list) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getDescription().contains(keyword)) {
                System.out.print(count);
                System.out.println("." + list.get(i));
                count++;
            }
        }
    }

    public void printWelcome() {
        System.out.println("Hello! I'm NEXUS");
        System.out.println("What can I do for you?");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
