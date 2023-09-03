package roo;

import roo.task.Task;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    Scanner scanner;
    TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void greet() {
        String logo = " ____                \n"
                + "|  _ \\  _____  _____ \n"
                + "| |/ / |  _  ||  _  |\n"
                + "| |\\ \\ | |_| || |_| |\n"
                + "|_| \\_\\|_____||_____|\n";
        String hello = "Hello! I am Roo!!\n" + "What can I do for you ah?\n";
        System.out.println("Hello from\n" + logo + hello);
        scanner = new Scanner(System.in);
    }

    public String read() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
        System.out.println("Ciao! Hope to see you soon yorr!!");
    }

    public void list() {
        System.out.println("Although I dunwan to list... But here is your list:");
        tasks.list();
    }

    public void clear() {
        tasks.clear();
        System.out.println("All tasks cleared\n");
    }

    public void listDateEvents(LocalDate date) {
        System.out.println("You need to do...");
        tasks.listDateEvents(date);
    }

    public void markDone(int i) {
        tasks.markDone(i);
        System.out.println("Yay! \"" + tasks.taskDetails(i) + "\" done liao!!\n");
    }

    public void markUndone(int i) {
        tasks.markUndone(i);
        System.out.println("Hmm... Why just now don't mark \"" + tasks.taskDetails(i) + "\" as done properly...\n");
    }

    public void delete(int i) {
        System.out.println("Okay!! Task \"" + tasks.taskDetails(i) + "\" removed :) ");
        tasks.delete(i);
        System.out.println("You still have " + tasks.size() + " tasks in the list\n");
    }

    public void add(Task task) {
        tasks.addNew(task);
        System.out.println("\"" + task.toString() + "\" added :)");
        System.out.println("Now got " + tasks.size() + " task liao T_T\n");
    }
}
