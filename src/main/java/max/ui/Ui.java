package max.ui;

import max.tasks.Task;
import java.util.List;
import java.util.Scanner;

public class Ui {
    static String line = "____________________________________________________________";
    public Ui() {
    }
    public void showGreeting() {
        System.out.println("     Hello from");
        System.out.println("       /\\/\\   __ ___  __");
        System.out.println("      /    \\ / _` \\ \\/ /");
        System.out.println("     / /\\/\\ \\ (_| |>  <");
        System.out.println("     \\/    \\/\\__,_/_/\\_\\");
        System.out.println("     How may I assist you?");
        System.out.println(line);
    }
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public void exit() {
        System.out.println("     Bye! Please come again!");
    }
    public void showLine() {
        System.out.println(line);
    }
    public void showLoadingError() {
        System.out.println("No tasks were found! Making you a new list real quick xd");
    }
    public void showMark(Task task) {
        System.out.println("     Good job on completing your task!");
        System.out.println("       " + task);
    }
    public void showUnmark(Task task) {
        System.out.println("     Okay, I've marked this as not done yet!");
        System.out.println("       " + task);
    }
    public void showAdd(Task task, int size) {
        System.out.println("     I gotchu. I've added this task:");
        System.out.println("       " + task);
        System.out.println(String.format("     Now you have %d task(s) in the list.", size));
    }
    public void showDelete(Task task, int size) {
        System.out.println("     Aights mate. I've killed this task:");
        System.out.println("       " + task);
        System.out.println(String.format("     Now you have %d task(s) in the list.", size));
    }
    public void showList(List list) {
        System.out.println("     Here are all your tasks:");

        // Iterate through ArrayList of tasks and enumerate them
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("     " + index + ". " + list.get(i));
        }
    }
    public void showError(String msg) {
        System.out.println(msg);
    }
}
