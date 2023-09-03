package Jelly.main;

import Jelly.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
        System.out.println("Hello! I'm Jelly.main.Jelly");
        System.out.println("What can I do for you?");
    }

    public void displayErrorMessage(String message) {
        System.out.println(message);
    }

    public void printList(ArrayList<Task> storage) {
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + "." + storage.get(i).toString());
        }
    }
    public void deleteMessage(Task deletedTask, int noOfTasks) {
        System.out.println("Okay, I've removed this task: \n" + deletedTask);
        System.out.println("Now you have " + noOfTasks + " in the list.");
    }

    public void byeMessage() {
        System.out.println("Bye mate! Have a nice day :]");
    }
}
