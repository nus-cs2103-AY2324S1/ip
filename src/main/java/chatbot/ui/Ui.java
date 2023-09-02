package chatbot.ui;

import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm ChatBot.\n" +
                "What can I do for you?\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void showList(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasklist.len(); i++) {
            System.out.println((i + 1) + ". " + tasklist.retrieveList().get(i));
        }
    }

    public void showAddedTask(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.retrieveList().get(taskList.len() - 1).toString());
        System.out.println("Now you have " + taskList.len() + " tasks in the list");
    }

    public void showRemovedTask(TaskList taskList, int num) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.retrieveList().get(num).toString());
        int total = taskList.len() - 1;
        System.out.println("Now you have " + total + " tasks in the list");
    }

    public void showFindResults(TaskList taskList , String input) {
        ArrayList<Task> list = taskList.getFilteredTasks(input);

        if(list.size() == 0) {
            System.out.println("There are no tasks with that keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + list.get(i - 1).toString());
            }
        }
    }

}
