package Ui;

import DukeException.DukeException;
import List.TaskList;
import Tasks.Task;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    final String NAME = "CathyTheChattyCat";
    String lineBreak = "\n__________________________________________\n";

    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }
    public void showLine() {
        System.out.println(lineBreak);
    }
    public void showLoadingError() {
        System.out.println(lineBreak + "Not detected in data. New List.TaskList created" + lineBreak);
    }
    public void showRequestForTextFile() {
        System.out.println(lineBreak + "Please Input the txt file you wish to access" + lineBreak);
    }
    public void showUnknownCommand() {
        System.out.println(lineBreak + UNKNOWN_COMMAND + lineBreak);
    }
    public void showError(String e) {
        System.out.println(e);
    }
    public void showDateFormatError(DukeException e) {
        System.out.println("☹ OOPS!!! The description of a time must be in yyyy-mm-dd");
    }
    public void showInvalidDescription(String taskType) {
        System.out.println("☹ OOPS!!! The description of a " + taskType + " is invalid.");
    }
    public void showTaskAdded(Task task, TaskList userList) {
        System.out.println("Got it. I've added this task: \n" + task);
        int size = userList.size();
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    public void showGreetings() {
        System.out.println(lineBreak + "Hello! I'm " + NAME);
        System.out.printf("What can I do for you?" + lineBreak);
    }
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done: \n\t" + task);
    }
    public void showUnMarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n\t" + task);
    }
    public void showDeleteTask(Task task, TaskList userList) {
        System.out.println("Noted. I've removed this task: \n" + task);
        System.out.println("Now you have " + userList.size() + " tasks in the list");
    }
    public void showClearTask() {
        System.out.println("Noted. I've removed all the tasks.");
    }
    public void showList(TaskList userList) {
        //System.out.println(lineBreak);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < userList.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + userList.get(i));
        }
        //System.out.println(lineBreak);
    }
    public void showGoodbye() {
        System.out.print("Bye. Hope to see you again soon!");
    }
    public void closeScanner() {
        scanner.close();
    }
    public void showFoundResults(TaskList foundList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundList.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + foundList.get(i));
        }
    }
}
